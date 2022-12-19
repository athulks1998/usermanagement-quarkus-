package org.projects.quarkus.dto.group;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Athul K S
 * INFO : Data transfer class to store group details
 */
@Getter
@Setter
public class GroupDetailsDTO {
	
	/**
	 * Stores ID of the group
	 */
	private Long id;
	
	/**
	 * stores the name of the group
	 */
	@JsonProperty("group_name")
	private String groupName;
	
	/**
	 * Stores the description of the group
	 */
	private String  description;
	
    /**
     * Stores the users associated with the groups 
     */
    private  List<AssociatedUsersDTO> users ;

}
