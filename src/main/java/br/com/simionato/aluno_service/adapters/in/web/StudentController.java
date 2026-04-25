package br.com.simionato.aluno_service.adapters.in.web;

import br.com.simionato.aluno_service.adapters.in.dto.request.StudentRequestDto;
import br.com.simionato.aluno_service.adapters.in.dto.response.AddressResponse;
import br.com.simionato.aluno_service.adapters.in.dto.response.StudentResponseDto;
import br.com.simionato.aluno_service.adapters.in.utils.ApiResponse;
import br.com.simionato.aluno_service.domain.model.Student;
import br.com.simionato.aluno_service.domain.ports.in.CreateStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.DeleteStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.GetStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.UpdateStudentUseCase;
import br.com.simionato.aluno_service.domain.ports.in.command.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static br.com.simionato.aluno_service.domain.utils.Validations.parseStringToLocalDate;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private CreateStudentUseCase createStudentUseCase;
    private UpdateStudentUseCase updateStudentUseCase;
    private GetStudentUseCase getStudentUseCase;
    private DeleteStudentUseCase deleteStudentUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentRequestDto request){

        var addressCommand = Objects.isNull(request.address()) ? null : createAddressCommand(request);

        var command = new CreateStudentCommand(
                request.name(),
                request.documentNumber(),
                request.email(),
                request.academicLevel(),
                addressCommand,
                parseStringToLocalDate(request.birthDate())
        );

        var student = createStudentUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Student created successfully",
                toResponse(student)));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(@PathVariable UUID id, @RequestBody StudentRequestDto request){

        var addressCommand = Objects.isNull(request.address()) ? null : createAddressCommand(request);

        var command = new UpdateStudentCommand(
                id,
                request.name(),
                request.email(),
                request.academicLevel(),
                addressCommand,
                parseStringToLocalDate(request.birthDate())
        );

        var student = updateStudentUseCase.execute(command);
        return ResponseEntity.ok(ApiResponse.success("Student updated successfully",
                toResponse(student)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getById(@PathVariable UUID id) {
        var student = getStudentUseCase.findById(new FindStudentByIdCommand(id));

        return ResponseEntity.ok(ApiResponse.success("Student found", toResponse(student)));
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getByDocumentNumber(@PathVariable String documentNumber) {
        var student = getStudentUseCase.findByDocumentNumber(new FindStudentByDocumentNumberCommand(documentNumber));

        return ResponseEntity.ok(ApiResponse.success("Student found", toResponse(student)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> findAll() {
        var students = getStudentUseCase.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("Students found", students));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        deleteStudentUseCase.execute(new DeleteStudentCommand(id));
        return ResponseEntity.ok(ApiResponse.success("Student deleted successfully", null));
    }

    private CreateAddressCommand createAddressCommand(StudentRequestDto request) {
        return new CreateAddressCommand(
                request.address().street().isBlank() ? null : request.address().street(),
                request.address().number().isBlank() ? null : request.address().number(),
                request.address().complement().isBlank() ? null : request.address().complement(),
                request.address().neighborhood().isBlank() ? null : request.address().neighborhood(),
                request.address().city().isBlank() ? null : request.address().city(),
                request.address().state().isBlank() ? null : request.address().state(),
                request.address().zipcode().isBlank() ? null : request.address().zipcode()
        );
    }

    private StudentResponseDto toResponse(Student student){
        var address = student.getAddress();

        var addressResponse = new AddressResponse(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipcode()
        );

        var studentResponse = new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getDocumentNumber(),
                student.getEmail(),
                student.getAcademicLevel(),
                student.getBirthDate(),
                addressResponse,
                student.getCreatedAt(),
                student.getUpdatedAt()
        );

        return studentResponse;
    }
}
