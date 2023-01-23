package org.projects.quarkus.service.login;

import javax.inject.Inject;

import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.dto.login.LoginRequestDTO;
import org.projects.quarkus.dto.login.LoginResponseDTO;
import org.projects.quarkus.utils.ErrorResponseUtils;
import org.projects.quarkus.utils.PasswordHashing;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

/**
 * @author athul1998
 * INFO : Class to handle implementation of user login and related services 
 */
public class UserAccessServiceImpl implements UserAccessService {

	@Inject
	PgPool pgClient;

	/**
	 * INFO : Login function to validate user credentials 
	 */
	@Override
	public Uni<LoginResponseDTO> loginUser(LoginRequestDTO loginRequest) {

		return pgClient.preparedQuery("Select password from users where username = $1 ")
				.execute(Tuple.of(loginRequest.getUsername())).onItem().transform(password -> {
					var row =password.iterator();
					var hashedPassword=row.next().getString("password");
					PasswordHashing.compareHashedString(hashedPassword, loginRequest.getPassword());
					return new LoginResponseDTO();
				}).onFailure().recoverWithItem( exception -> {
					var failedResponse = new LoginResponseDTO();
					failedResponse.setMessage(ApplicationConstants.USER_LOGIN_FAILURE);
					failedResponse.setStatusCode(ApplicationConstants.UNAUTHORIZED_CODE);
					return failedResponse;
				});

	}

}
