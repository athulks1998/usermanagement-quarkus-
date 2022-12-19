package org.projects.quarkus.service.group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.dto.common.ResponseDTO;
import org.projects.quarkus.dto.group.CreateGroupDTO;
import org.projects.quarkus.dto.group.GroupDetailsDTO;
import org.projects.quarkus.model.UserGroup;
import org.projects.quarkus.model.Users;
import org.projects.quarkus.repositories.GroupRepository;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Athul K S INFO: Service Implementation class for group related
 *         services
 */
@Slf4j
@ApplicationScoped
public class GroupServiceImpl implements GroupService {

	/**
	 * Repository objects for group 
	 */
	@Inject
	GroupRepository groupRepo;
	
	/**
	 * Postgres client for general db operations
	 */
	@Inject
	PgPool pgClient;

	/**
	 * INFO : create group method to persist the group details 
	 */
	@Override
	public Uni<ResponseDTO> createGroup(CreateGroupDTO groupDetails) {
		log.info("Entry : Get the create group");
		var group = new UserGroup();
		group.setDescription(groupDetails.getDescription());
		group.setName(groupDetails.getGroupName());
		group.setUsers(groupDetails.getUserIds().stream().map(userId -> {
			var user = new Users();
			user.id = userId;
			return user;
		}).collect(Collectors.toList()));
		
       // with transaction is used to roleback transaction
		return  Panache.withTransaction(() -> groupRepo.persist(group).onItem().transform(persistedGroup -> {
				var response = new ResponseDTO();
				response.setMessage(ApplicationConstants.CREATE_GROUP_SUCCESS);
				response.setStatus(ApplicationConstants.SUCCESS);
				response.setStatusCode(ApplicationConstants.SUCCESS_CODE);
				log.info("Exit : Get the create group");
			 return response;
		 }).onFailure().recoverWithItem(error -> {
			 log.error("Failed to persist the data to the database :" + error.getMessage());
				ResponseDTO response = new ResponseDTO();
				response.setMessage(ApplicationConstants.REGISTER_USER_FAILURE);
				response.setStatus(ApplicationConstants.FAILURE);
				response.setStatusCode(ApplicationConstants.INTERNAL_SERVER_ERROR_CODE);
				log.info("Exit : Get the create group");
				return response;
		 }));
	}


	/**
	 * @return INFO : Method uses reactive pg client to fetch the list of groups
	 */
	@Override
	public Uni<List<GroupDetailsDTO>> listGroups() {
		return pgClient.query("Select * from user_group").execute().onItem().transform(rows -> {
			List<GroupDetailsDTO> groupList = new ArrayList<>();
			var iterator = rows.iterator();
			while (iterator.hasNext()) {
				var row = iterator.next();
				var group = new GroupDetailsDTO();
				group.setGroupName(row.getString("name"));
				group.setId(row.getLong("id"));
				group.setDescription(row.getString("description"));
				group.setUsers(new ArrayList<>());
				groupList.add(group);
			}
			return groupList;
		});
	}

}
