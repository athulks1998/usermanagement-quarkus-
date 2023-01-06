package org.projects.quarkus;

import static org.junit.jupiter.api.Assertions.assertEquals;


import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;
import org.projects.audit.AddAuditDTO;
import org.projects.audit.MutinyAuditGrpc.MutinyAuditStub;
import org.projects.audit.constants.AuditConstants;

/**
 * @author athul ks
 *
 */
@QuarkusTest
public class AuditTest {

	@GrpcClient
	MutinyAuditStub auditStub;

	/**
	 * INFO: Test Success case for add audit
	 */
	@Test
	public void addAudit() {

		var response = auditStub.addAudit(AddAuditDTO.newBuilder().setCreatedBy("Athul").setUpdatedBy("Athul")
				.setEntityName("Users").setOldValue("old").setNewValue("new").setUpdatedTime("18-dec-1998 12:18:26")
				.setProperty("name").build()).await().indefinitely();

		assertEquals(AuditConstants.AUDIT_SUCCESS_MESSAGE, response.getMessage());
		assertEquals(AuditConstants.SUCCESS_CODE, response.getResponseCode());
	}

	/**
	 * INFO: Test invalid date format
	 */
	@Test
	public void addAuditInvalidDate() {

		var response = auditStub.addAudit(AddAuditDTO.newBuilder().setCreatedBy("Athul").setUpdatedBy("Athul")
				.setEntityName("Users").setOldValue("old").setNewValue("new").setUpdatedTime("18-12-1998 12:18:26")
				.setProperty("name").build()).await().indefinitely();

		assertEquals(AuditConstants.INVALID_DATE_FORMAT, response.getMessage());
		assertEquals(AuditConstants.REQUEST_ERROR, response.getResponseCode());
	}

}
