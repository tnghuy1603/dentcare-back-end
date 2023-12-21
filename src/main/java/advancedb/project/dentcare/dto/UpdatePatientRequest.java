package advancedb.project.dentcare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class UpdatePatientRequest {
    private Integer patientId;
    private String name;
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private String oralHealth;
}
