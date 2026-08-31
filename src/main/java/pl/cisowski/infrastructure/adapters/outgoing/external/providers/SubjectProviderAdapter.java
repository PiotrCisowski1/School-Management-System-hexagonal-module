package pl.cisowski.infrastructure.adapters.outgoing.external.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.cisowski.domain.model.external.Subject;
import pl.cisowski.domain.ports.outgoing.providers.SubjectProviderPort;
import pl.cisowski.infrastructure.adapters.outgoing.external.mappers.SubjectExternalMapper;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.SubjectDto;

@Component
public class SubjectProviderAdapter extends AbstractProvider implements SubjectProviderPort {

    private final SubjectExternalMapper subjectMapper;

    public SubjectProviderAdapter(ObjectMapper objectMapper, SubjectExternalMapper subjectMapper) {
        super(objectMapper);
        this.subjectMapper = subjectMapper;
    }

    @Override
    public Subject fetch(Integer subjectId) {
        String subjectExamplePath = "/provider-examples/subject.json";
        SubjectDto dto = mapJsonToObject(SubjectDto.class, subjectExamplePath);
        return subjectMapper.toSubject(dto);
    }
}
