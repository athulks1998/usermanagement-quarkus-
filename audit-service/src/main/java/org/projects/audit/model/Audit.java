package org.projects.audit.model;

import java.util.Date;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author athul
 * INFO : Entity for storing audit history 
 */
@Entity
@Getter
@Setter
public class Audit  extends PanacheEntity{


    /**
     * Store the previous value of the property and record 
     */
    public String oldValue;
    
    /**
     * Store the new value of the  property and record 
     */
    public  String newValue;
    
    /**
     * Time at which the record was updated 
     */
    public Date updatedTime;
    
    /**
     * Name of the user who updated the details 
     */
    public String updatedBy;
    
    /**
     *  Name of the user who created the record 
     */
    public String createdBy;
    
    /**
     * Property of the record being updated 
     */
    public String propertyUpdated;
    
    /**
     * Name of the entity being updated 
     */
    public String entityName;
}
