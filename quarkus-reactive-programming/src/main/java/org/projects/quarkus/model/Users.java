package org.projects.quarkus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * @author Athul K S INFO: Entity class to store user details
 *
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends PanacheEntity {

	/**
	 * Stores the username of the user
	 */
	@Column(unique = true)
	public String username;

	/**
	 * Stores the first name of the user
	 */
	public String firstName;

	/**
	 * Stores the last name of the user
	 */
	public String lastName;

	/**
	 * Stores the unique email Id of the user
	 */
	@Column(unique = true)
	public String emailId;

	/**
	 * Stores the encrypted Password of the user
	 */
	public String password;

	/**
	 * Stores the List of groups user is assigned to
	 */
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_group_user", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_group_id") })
	public List<UserGroup> groups;
}
