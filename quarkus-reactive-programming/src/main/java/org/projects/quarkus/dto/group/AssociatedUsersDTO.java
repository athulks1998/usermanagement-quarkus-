package org.projects.quarkus.dto.group;

import lombok.AllArgsConstructor;

import lombok.Setter;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Athul K S
 * INFO : Data transfer class to store details of the associated users in the group
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociatedUsersDTO {
	
	/**
	 * Stores the id of the user
	 */
	private Long id;
	
	/**
	 * Stores the username of the user
	 */
	private String username;
	
	/**
	 * Stores the email ID of the user 
	 */
	private String emailId;
	
	/**
	 * Stores the full name ie. first name + last name
	 */
	private String fullName;

}
