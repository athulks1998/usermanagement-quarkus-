package org.projects.quarkus.dto.group;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Athul K S 
 * INFO : DTO for creating the group
 */
@Getter
@Setter
public class CreateGroupDTO {

	/**
	 * Stores the group name 
	 */
	@JsonProperty("group_name")
	private String groupName;
	
	/**
	 * Stores the description of the group
	 */
	private String description;
	
	/**
	 * Associated ids of users added 
	 */
	@JsonProperty("user_id")
	private List<Long> userIds;
}
