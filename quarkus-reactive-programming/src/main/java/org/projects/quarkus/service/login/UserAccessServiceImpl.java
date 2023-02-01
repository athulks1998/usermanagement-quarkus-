package org.projects.quarkus.service.login;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.dto.login.LoginRequestDTO;
import org.projects.quarkus.dto.login.LoginResponseDTO;
import org.projects.quarkus.utils.ErrorResponseUtils;
import org.projects.quarkus.utils.PasswordHashing;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.extern.slf4j.Slf4j;

/**
 * @author athul1998 INFO : Class to handle implementation of user login and
 *         related services
 */
@Slf4j
@ApplicationScoped
public class UserAccessServiceImpl implements UserAccessService {

	@Inject
	PgPool pgClient;

	/**
	 * INFO : Login function to validate user credentials
	 */
	@Override
	public Uni<LoginResponseDTO> loginUser(LoginRequestDTO loginRequest) {
		var response = new LoginResponseDTO();
		try {

			log.info("Entry : User login Attempt !");
			
			return pgClient.preparedQuery("Select password from users where username = $1 ")
					.execute(Tuple.of(loginRequest.getUsername())).onItem().transform(password -> {
						var row = password.iterator();
						var hashedPassword = row.next().getString("password");
						var validationResult= PasswordHashing.compareHashedString(hashedPassword, loginRequest.getPassword());
						 if(validationResult)
						 {
							 response.setStatus(ApplicationConstants.SUCCESS);
							 response.setStatusCode(ApplicationConstants.SUCCESS_CODE);
							 response.setMessage(ApplicationConstants.USER_LOGIN_SUCCESS);
						 }
						return response;
					}).onFailure().recoverWithItem(exception -> {
						exception.printStackTrace();
						response.setMessage(ApplicationConstants.USER_LOGIN_FAILURE);
						response.setStatusCode(ApplicationConstants.UNAUTHORIZED_CODE);
						response.setStatus(ApplicationConstants.SUCCESS);
						response.setStatus(ApplicationConstants.FAILURE);
						return response;
					});
		} catch (Exception e) {
			e.getStackTrace();
			response.setMessage(ApplicationConstants.INTERNAL_SERVER_ERROR);
			response.setStatusCode(ApplicationConstants.INTERNAL_SERVER_ERROR_CODE);
			response.setStatus(ApplicationConstants.FAILURE);
			return Uni.createFrom().item(() -> response);
		} finally {
			log.info("Exit : User login Attempt !");
		}

	}

}
