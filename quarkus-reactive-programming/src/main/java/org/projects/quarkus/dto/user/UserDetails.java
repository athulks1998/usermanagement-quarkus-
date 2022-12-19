package org.projects.quarkus.dto.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Athul K S 
 * INFO : Stores the basic details of the user for data transfer
 */
@Getter
@Setter
public class UserDetails {

	
	/**
	 * Stores username of the account 
	 */
	@JsonProperty("username")
	private String username;
	
	/**
	 * Stores the first name of the user
	 */
	@JsonProperty("first_name")
	private String firstName;
	
	/**
	 * Stores the email id of the user
	 */
	@JsonProperty("email_id")
	private String emailId;
	
	/**
	 * Stores the last name of the user
	 */
	@JsonProperty("last_name")
	private String lastName;
	
	/**
	 * Stores the encrypted password 
	 */
	@JsonProperty("password")
	private String password;
	
	/**
	 * Stores the list of groups user is assigned to the user
	 */
	@JsonProperty("group_id")
	private List<Long> groupIds;
	
	/**
	 * To store the Success and failure message 
	 */
	private String message;
	
}
