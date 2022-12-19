package org.projects.quarkus.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.projects.quarkus.model.Users;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

/**
 * @author Repository Class to get user details 
 *
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<Users> {

	
}
