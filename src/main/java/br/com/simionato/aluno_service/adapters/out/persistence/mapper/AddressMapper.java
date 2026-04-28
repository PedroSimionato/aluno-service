package br.com.simionato.aluno_service.adapters.out.persistence.mapper;

import br.com.simionato.aluno_service.adapters.out.persistence.entity.AddressEntity;
import br.com.simionato.aluno_service.domain.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity toEntity(Address address);

    Address toDomain(AddressEntity entity);
}
