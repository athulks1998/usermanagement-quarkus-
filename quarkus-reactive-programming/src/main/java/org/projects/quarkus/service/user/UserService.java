package org.projects.quarkus.service.user;

import javax.enterprise.context.ApplicationScoped;

import org.projects.quarkus.dto.common.ResponseDTO;
import org.projects.quarkus.dto.user.UserDetails;

import io.smallrye.mutiny.Uni;

/**
 * @author Athul K S 
 * INFO : Service class for the user related services
 */
@ApplicationScoped
public interface UserService {

	
	/**
	 * @param id
	 * @return UserDetails of the user with the id 
	 * INFO : Fetches the basic details of the user
	 */
	public Uni<UserDetails> getUserDetails(Long  id);
	
	
	/**
	 * @param details
	 * @return ResponseDTO with success or failure status
	 */
	public Uni<ResponseDTO> registerUser(UserDetails details);
	
	
}
