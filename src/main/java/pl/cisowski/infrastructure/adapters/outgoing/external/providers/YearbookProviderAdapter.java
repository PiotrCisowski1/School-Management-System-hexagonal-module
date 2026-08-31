package pl.cisowski.infrastructure.adapters.outgoing.external.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.cisowski.domain.model.external.Yearbook;
import pl.cisowski.domain.ports.outgoing.providers.YearbookProviderPort;
import pl.cisowski.infrastructure.adapters.outgoing.external.mappers.YearbookExternalMapper;
import pl.cisowski.infrastructure.adapters.outgoing.external.models.YearbookDto;

@Component
public class YearbookProviderAdapter extends AbstractProvider implements YearbookProviderPort {

    private final YearbookExternalMapper yearbookExternalMapper;

    public YearbookProviderAdapter(ObjectMapper objectMapper, YearbookExternalMapper yearbookExternalMapper) {
        super(objectMapper);
        this.yearbookExternalMapper = yearbookExternalMapper;
    }

    public Yearbook fetchYearbook(Integer yearbookId) {
        String exampleYearbookPath = "/provider-examples/yearbook.json";
        YearbookDto yearbookDto = mapJsonToObject(YearbookDto.class, exampleYearbookPath);
        return yearbookExternalMapper.toYearbook(yearbookDto);
    }
}
