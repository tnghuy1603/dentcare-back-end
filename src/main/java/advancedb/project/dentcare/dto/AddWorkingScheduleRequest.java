package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter

public class AddWorkingScheduleRequest {
    private LocalDate date;
    private Integer dentistId;
    private Integer workShiftId;
}
