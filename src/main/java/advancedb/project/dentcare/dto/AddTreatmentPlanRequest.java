package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class AddTreatmentPlanRequest {
    private Integer patientId;
    private List<Integer> treatmentCodeId;
    private List<ToothSelectionDto> toothSelectionDtoList;
    private String note;
    private LocalDate startingDate;
    private String status;

}
