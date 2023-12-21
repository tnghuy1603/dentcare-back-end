package advancedb.project.dentcare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class UpdateUserRequest {
    public Integer userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dob;
    private Integer branchId;
}
