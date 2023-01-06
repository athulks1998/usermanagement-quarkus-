package org.projects.audit.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.projects.audit.AddAuditDTO;
import org.projects.audit.Audit;
import org.projects.audit.AuditResponse;
import org.projects.audit.ListAudit;
import org.projects.audit.ViewAuditRequest;
import org.projects.audit.constants.AuditConstants;
import org.projects.audit.mapper.AuditMapper;
import org.projects.audit.repository.AuditRepo;
import org.projects.audit.utils.AuditUtils;

import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

/**
 * @author athul ks INFO : Audit related services
 */
@GrpcService
@Slf4j
public class AuditService implements Audit {

	@Inject
	AuditRepo auditRepo;

	/**
	 * INFO : Function to capture the audit details
	 */
	@Override
	@Transactional
	public Uni<AuditResponse> addAudit(AddAuditDTO request) {

		// Map the grpc dto to the entity
		var audit = AuditMapper.INSTANCE.mapToModel(request);

		SimpleDateFormat formatter6 = new SimpleDateFormat(AuditConstants.DATE_FORMAT);

		// Parse date String
		try {
			Date date1 = formatter6.parse(request.getUpdatedTime());
			audit.setUpdatedTime(date1);
		} catch (ParseException e) {

			return Uni.createFrom().item(
					() -> AuditUtils.setErrorDetails(AuditConstants.INVALID_DATE_FORMAT, AuditConstants.REQUEST_ERROR));

		}

		// Reactive transaction to db to capture audit

		return auditRepo.persistAndFlush(audit).onItem()
				.transform(data -> AuditResponse.newBuilder().setMessage(AuditConstants.AUDIT_SUCCESS_MESSAGE)
						.setResponseCode(AuditConstants.SUCCESS_CODE).setErrorCode("").build())
				.onFailure().recoverWithItem(
						error -> AuditUtils.setErrorDetails(error.getMessage(), AuditConstants.INTERNAL_SERVER_ERROR));
	}

	/**
	 * INFO: Find all the audit data based on filter and pagination
	 */
	@Override
	@Blocking
	public Uni<ListAudit> viewAudit(ViewAuditRequest request) {
		
		String query ="newValue like %:abc% ";
		Map<String, Object> paramMap= new HashMap<>();
		paramMap.put("abc", "test");
		auditRepo.find(query, paramMap).list().await().indefinitely();
	return Uni.createFrom().item(() -> null);
	}

}
