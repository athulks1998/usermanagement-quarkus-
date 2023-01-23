package org.projects.quarkus.dto.login;

import org.projects.quarkus.dto.common.ResponseDTO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author athul1998
 * INFO : Specific response DTO for login
 */
@Getter
@Setter
public class LoginResponseDTO extends ResponseDTO{

	/**
	 * INFO : Stores the access token 
	 */
	private String accessToken;
	
	/**
	 * INFO : Stores the refresh token 
	 */
	private String refreshToken;
}
