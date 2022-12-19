package org.projects.audit.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.projects.audit.AddAuditDTO;
import org.projects.audit.model.Audit;

/**
 * @author athul ks INFO : Mapper class using mapstruct to map audit data
 *         objects
 *
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuditMapper {

	AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

	/**
	 * @param auditDto
	 * @return
	 * INFO: mapper method for mapping from grpc proto to model
	 */
	@Mapping( target = "updatedTime", ignore = true)
	@Mapping(target="propertyUpdated", source="property")
	Audit mapToModel(AddAuditDTO auditDto);
}
