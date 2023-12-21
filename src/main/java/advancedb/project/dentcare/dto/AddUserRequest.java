package advancedb.project.dentcare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    private LocalDate dob;
    private Integer branchId;
}
