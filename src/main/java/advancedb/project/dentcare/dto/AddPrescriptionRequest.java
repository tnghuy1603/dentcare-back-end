package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
public class AddPrescriptionRequest {
    private Integer quantity;
    private String frequency;
    private String dosage;
    private BigDecimal price;
    private Integer treatmentPlanId;
    private Integer medicineId;
}
