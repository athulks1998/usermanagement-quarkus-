package org.projects.audit.utils;

import java.util.UUID;

import org.projects.audit.AuditResponse;
import org.projects.audit.constants.AuditConstants;

/**
 * @author athul
 * 
 */
public class AuditUtils {
	
	AuditUtils()
	{}

	/**
	 * @param message
	 * @param errorCode
	 * @return AuditResponse
	 * INFO  : Create an error response dto and a unique uuid for error detection
	 */
	public static AuditResponse setErrorDetails(String message, Long errorCode) {
		return AuditResponse.newBuilder().setMessage(message).setResponseCode(errorCode)
				.setErrorCode(AuditConstants.AUDIT_SERVICE + UUID.randomUUID()).build();

	}

}
