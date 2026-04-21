package br.com.simionato.aluno_service.adapters.out.persistence.mapper;

import br.com.simionato.aluno_service.adapters.out.persistence.entity.StudentEntity;
import br.com.simionato.aluno_service.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface StudentMapper {

    StudentEntity toEntity(Student student);

    Student toDomain(StudentEntity entity);
}
