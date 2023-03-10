package org.projects.quarkus.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Athul K S
 * INFO : This is a common Response dto for create and update operations 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
	
	/**
	 * To store the success/failure message of the API call
	 */
	protected String message;
	
	/**
	 * To Store the success/failure status 
	 */
	protected String status;
	
	/**
	 * To store the response code based on the response 
	 */
	protected Long statusCode;
	
	/**
	 * Stores unique identifiers finding error logs 
	 */
	protected String errorCode;

}
