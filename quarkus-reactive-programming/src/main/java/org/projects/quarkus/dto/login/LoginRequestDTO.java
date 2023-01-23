package org.projects.quarkus.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author athul1998
 * INFO : DTO for login request 
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {

	/**
	 * INFO : username for the login
	 */
	private String username;
	
	/**
	 * INFO : hashed password for login
	 */
	private String password;
}
