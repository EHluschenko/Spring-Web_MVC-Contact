package ed.hluschenko.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDtoRequest(
        Long id,
        String firstName,
        String lastName,
        String phone
) {
}
