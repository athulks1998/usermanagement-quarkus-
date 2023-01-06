package org.projects.quarkus.utils;

import java.util.List;

import org.projects.audit.AddAuditDTO;
import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.constants.AuditConstants;
import org.projects.quarkus.model.Users;

public class AuditUtils {

	AuditUtils()
	{
		
	}
	
	public static List<AddAuditDTO> addUserAuditDto(Users user, String username) {

		return List.of(AddAuditDTO.newBuilder().setCreatedBy(username).setUpdatedBy(username)
				.setProperty(AuditConstants.USER_NAME_PROPERTY).setNewValue(user.getUsername())
				.setOldValue(ApplicationConstants.CREATE_GROUP_SUCCESS).setEntityName(AuditConstants.USER_ENTITY)
				.build());

	}

}
