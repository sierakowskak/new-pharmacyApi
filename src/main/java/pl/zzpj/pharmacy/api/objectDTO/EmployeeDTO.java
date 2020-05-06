package pl.zzpj.pharmacy.api.objectDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
}
