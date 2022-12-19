package org.projects.audit.repository;
import javax.enterprise.context.ApplicationScoped;

import org.projects.audit.model.Audit;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;

/**
 * @author athul ks
 * INFO : Class for  audit repository methods and functionalities 
 */
@ApplicationScoped
public class AuditRepo implements PanacheRepository<Audit>{

}
