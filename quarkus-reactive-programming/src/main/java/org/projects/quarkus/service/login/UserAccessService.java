package org.projects.quarkus.service.login;

import javax.enterprise.context.ApplicationScoped;

import org.projects.quarkus.dto.login.LoginRequestDTO;
import org.projects.quarkus.dto.login.LoginResponseDTO;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public interface UserAccessService {
	
	Uni<LoginResponseDTO> loginUser( LoginRequestDTO loginRequest);
	
}
