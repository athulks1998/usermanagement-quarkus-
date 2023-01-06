package org.projects.quarkus.service.group;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.projects.quarkus.dto.common.ResponseDTO;
import org.projects.quarkus.dto.group.CreateGroupDTO;
import org.projects.quarkus.dto.group.GroupDetailsDTO;

import io.smallrye.mutiny.Uni;

/**
 * @author Athul K S
 * INFO : Service class for Group related functionalities
 */
@ApplicationScoped
public interface GroupService {

	
	/**
	 * @param groupDetails
	 * @return ResponseDTO Returns the success/failure response 
	 */
	Uni<ResponseDTO> createGroup(CreateGroupDTO groupDetails);
	
	/**
	 * @return GroupDetailsDTO returns the list of groups
	 * 
	 */
	Uni<List<GroupDetailsDTO>> listGroups();
}
