package org.projects.quarkus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Athul K S INFO : Stores the group details
 */
@Entity
@Table(name = "user_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup extends PanacheEntity {

	/**
	 * Stores the name of the User Group
	 */
	public String name;

	/**
	 * Stores description of the group and other details
	 */
	public String description;

	/**
	 * Many to many mapping to users
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_group_user", joinColumns = { @JoinColumn(name = "user_group_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	public List<Users> users;

}
