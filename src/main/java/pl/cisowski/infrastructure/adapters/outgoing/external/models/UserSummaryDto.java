package pl.cisowski.infrastructure.adapters.outgoing.external.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSummaryDto extends BaseExternalDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
