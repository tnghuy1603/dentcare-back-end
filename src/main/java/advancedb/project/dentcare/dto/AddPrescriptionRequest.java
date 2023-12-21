package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class AddPrescriptionRequest {
    private Integer patientId;
    private Integer quantity;
    private String unit;
    private Integer medicineId;

}
