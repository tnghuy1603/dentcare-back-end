package advancedb.project.dentcare.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
public class AddAppointmentRequest {
    private Integer patientId;
    private Integer dentistId;
    private Integer assistantId;
    private String note;
    private String status;
    private String room;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
