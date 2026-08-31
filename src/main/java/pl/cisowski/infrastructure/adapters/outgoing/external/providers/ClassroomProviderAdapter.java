package pl.cisowski.infrastructure.adapters.outgoing.external.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.cisowski.domain.model.external.Classroom;
import pl.cisowski.domain.ports.outgoing.providers.ClassroomProviderPort;
import pl.cisowski.infrastructure.adapters.outgoing.external.mappers.ClassroomExternalMapper;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.ClassroomDto;

@Component
public class ClassroomProviderAdapter extends AbstractProvider implements ClassroomProviderPort {

    private final ClassroomExternalMapper classroomExternalMapper;

    public ClassroomProviderAdapter(ObjectMapper objectMapper, ClassroomExternalMapper classroomExternalMapper) {
        super(objectMapper);
        this.classroomExternalMapper = classroomExternalMapper;
    }

    @Override
    public Classroom fetch(Integer classroomId) {
        String classroomExamplePath = "/provider-examples/classroom.json";
        ClassroomDto dto = mapJsonToObject(ClassroomDto.class, classroomExamplePath);
        return classroomExternalMapper.toClassroom(dto);
    }
}
