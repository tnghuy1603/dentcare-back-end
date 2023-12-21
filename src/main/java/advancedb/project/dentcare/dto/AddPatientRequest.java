package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddPatientRequest {
    private String name;
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    private String address;
}
