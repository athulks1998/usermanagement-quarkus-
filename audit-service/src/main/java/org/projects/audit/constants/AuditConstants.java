package org.projects.audit.constants;

/**
 * @author athul
 * INFO : Service related constants 
 */
public class AuditConstants {
	
	AuditConstants()
	{
		
	}

	/////////////////////////////Service Messages///////////////////////////////
	
	public static final String AUDIT_SUCCESS_MESSAGE ="Audit captured succesfully!";
	
	public static final String AUDIT_FAILURE_MESSAGE ="Failed to capture audit!";
	
	public static final String INVALID_DATE_FORMAT ="Invalid date format!";
	
	////////////////////////////Service Constants////////////////////////////
	
	public static final String AUDIT_SERVICE="Audit_Service";
	
	public static final String DATE_FORMAT ="dd-MMM-yyyy HH:mm:ss";
	
	
	////////////////////////////Response codes//////////////////////////////
	
	public static final Long SUCCESS_CODE = 200l;
	
	public static final Long INTERNAL_SERVER_ERROR = 500l;
	
	public static final Long REQUEST_ERROR =400l;

}
