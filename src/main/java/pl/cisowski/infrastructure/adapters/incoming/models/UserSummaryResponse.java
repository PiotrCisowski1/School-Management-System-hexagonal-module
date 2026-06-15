package pl.cisowski.infrastructure.adapters.incoming.models;

import pl.cisowski.domain.model.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class UserSummaryResponse {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String phoneNumber;
}
