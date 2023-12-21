package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPatient_Id(Integer patientId);
    List<Appointment> findByRoomAndDate(String room, LocalDate date);
    List<Appointment> findByDateAndDentist_Id(LocalDate date, Integer dentistId);
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByPatient_IdAndStatus(Integer patientId, String status);

    @Query("SELECT a FROM Appointment a WHERE a.patient.name LIKE %:patientName% AND a.date = :date")
    List<Appointment> findByPatientNameAndDate(@Param("patientName") String patientName, @Param("date") LocalDate date);

}
