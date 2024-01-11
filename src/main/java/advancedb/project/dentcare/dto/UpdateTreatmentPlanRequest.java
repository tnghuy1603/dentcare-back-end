package advancedb.project.dentcare.dto;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
public class UpdateTreatmentPlanRequest {
    private Integer treatmentPlanId;
    private String status;
}
