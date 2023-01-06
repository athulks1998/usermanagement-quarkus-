package org.projects.quarkus.service.user;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.projects.audit.AuditGrpc;
import org.projects.audit.AuditGrpc.AuditStub;
import org.projects.audit.MutinyAuditGrpc.MutinyAuditStub;
import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.dto.common.ResponseDTO;
import org.projects.quarkus.dto.user.UserDetails;
import org.projects.quarkus.model.UserGroup;
import org.projects.quarkus.model.Users;
import org.projects.quarkus.repositories.UserRepository;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Athul K S INFO: Service Implementation class for user related
 *         functionalities
 */
@Slf4j
@ApplicationScoped 
public class UserServiceImpl implements UserService {

	@Inject
	UserRepository userRepo;
	
	/**
	 * Stub for accessing audit service 
	 */
	@GrpcClient("audit")
	MutinyAuditStub auditStub;


	/**
	 * INFO : Implementation to get the user details from the db
	 */
	@Override
	public Uni<UserDetails> getUserDetails(Long id) {
		log.info("Entry : Get the user details ");
		return userRepo.findById(id).onItem().transform(user -> {

			// TO do : Convert the mapping using mapstruct
			// Map the user details to db
			var userDetail = new UserDetails();
			userDetail.setFirstName(user.getFirstName());
			userDetail.setEmailId(user.getEmailId());
			userDetail.setLastName(user.getLastName());
			userDetail.setGroupIds(user.getGroups().stream().map(group -> group.id).collect(Collectors.toList()));
			userDetail.setUsername(user.getUsername());
			userDetail.setMessage(ApplicationConstants.VIEW_USER_DETAILS_SUCCESS);
			log.info("Exit : Get the user details ");
			return userDetail;
		}).onFailure().recoverWithItem(error -> {

			log.error("Failed to fetch the data from the database :" + error.getMessage());
			var failResponse = new UserDetails();
			failResponse.setMessage("Failed to get the user details");
			log.info("Exit : Get the user details ");
			return failResponse;
		});
	}

	/**
	 * INFO : Implementation method to create a user entry in DB
	 */
	@Override
	public Uni<ResponseDTO> registerUser(UserDetails details) {
		log.info("Entry : Get the register user ");
		// Maping the group Ids to objects
		var groups = details.getGroupIds().stream().map(id -> {
			UserGroup group = new UserGroup();
			group.id = id;
			return group;
		}).collect(Collectors.toList());
		var user = new Users(details.getUsername(), details.getFirstName(), details.getLastName(), details.getEmailId(),
				details.getPassword(), groups);
		
		return Panache.withTransaction(() -> userRepo.persist(user).onItem().transform(userObject -> {
			var response = new ResponseDTO();
			response.setMessage(ApplicationConstants.REGISTER_USER_SUCCESS);
			response.setStatus(ApplicationConstants.SUCCESS);
			response.setStatusCode(ApplicationConstants.SUCCESS_CODE);
			log.info("Exit : Get the register user ");
			return response;
		}).onFailure().recoverWithItem(error -> {
			log.error("Failed to persist the data to the database :" + error.getMessage());
			ResponseDTO response = new ResponseDTO();
			response.setMessage(ApplicationConstants.REGISTER_USER_FAILURE);
			response.setStatus(ApplicationConstants.FAILURE);
			response.setStatusCode(ApplicationConstants.INTERNAL_SERVER_ERROR_CODE);
			log.info("Exit : Get the register user ");
			return response;
		}));
	}

}
