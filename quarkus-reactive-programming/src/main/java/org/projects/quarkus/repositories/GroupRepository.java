package org.projects.quarkus.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.projects.quarkus.model.UserGroup;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

/**
 * @author Athul K S
 * INFO : Repository class for user groups 
 */
@ApplicationScoped
public class GroupRepository implements PanacheRepository<UserGroup> {

}
