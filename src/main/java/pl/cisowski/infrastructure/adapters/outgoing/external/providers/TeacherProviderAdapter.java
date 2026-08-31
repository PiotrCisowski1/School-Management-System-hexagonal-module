package pl.cisowski.infrastructure.adapters.outgoing.external.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.cisowski.domain.model.external.Teacher;
import pl.cisowski.domain.ports.outgoing.providers.TeacherProviderPort;
import pl.cisowski.infrastructure.adapters.outgoing.external.mappers.TeacherExternalMapper;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.TeacherDto;

@Component
public class TeacherProviderAdapter extends AbstractProvider implements TeacherProviderPort {

    private final TeacherExternalMapper teacherExternalMapper;

    public TeacherProviderAdapter(ObjectMapper objectMapper, TeacherExternalMapper teacherExternalMapper) {
        super(objectMapper);
        this.teacherExternalMapper = teacherExternalMapper;
    }

    @Override
    public Teacher fetch(Integer teacherId) {
        String teacherExamplePath = "/provider-examples/teacher.json";
        TeacherDto dto = mapJsonToObject(TeacherDto.class, teacherExamplePath);
        return teacherExternalMapper.toTeacher(dto);
    }
}
