package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class StatisticRequest {
    private Integer dentistId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
