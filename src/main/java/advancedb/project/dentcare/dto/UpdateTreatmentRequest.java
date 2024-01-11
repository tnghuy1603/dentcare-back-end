package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class UpdateTreatmentRequest {
    private Integer treatmentId;
    private String note;
    private LocalDate performAt;
    private BigDecimal fee;
}
