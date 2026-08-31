package pl.cisowski.infrastructure.adapters.outgoing.external.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import pl.cisowski.domain.exceptions.EntityNotFoundException;

import java.io.InputStream;

@RequiredArgsConstructor
public class AbstractProvider {

    private final ObjectMapper objectMapper;

    protected <T> T mapJsonToObject(Class<T> objectType, String jsonPath) {
        try{
            try (InputStream inputStream = getClass().getResourceAsStream(jsonPath)) {
                if (inputStream == null) {
                    throw new IllegalArgumentException("File not found: " + jsonPath);
                }
                return objectMapper.readValue(inputStream, objectType);
            }
        } catch (Exception ex) {
            throw new EntityNotFoundException(String.format("Stub JSON object: %s", objectType), "");
        }
    }
}
