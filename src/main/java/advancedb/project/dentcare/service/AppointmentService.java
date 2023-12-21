package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Appointment;
import advancedb.project.dentcare.domain.Patient;
import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.dto.AddAppointmentRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.AppointmentRepository;
import advancedb.project.dentcare.repository.PatientRepository;
import advancedb.project.dentcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    public List<Appointment> findByDate(LocalDate date){
        return appointmentRepository.findByDate(date);
    }
    public List<Appointment> findByPatientId(Integer patientId){
        return appointmentRepository.findByPatient_Id(patientId);
    }

    public List<Appointment> findByPatientNameAndDate(String patientName, LocalDate date){
        return appointmentRepository.findByPatientNameAndDate(patientName, date);
    }

    public List<Appointment> findByDentistAndDate(Integer dentistId, LocalDate date){
        return appointmentRepository.findByDateAndDentist_Id(date, dentistId);
    }

    public List<Appointment> findByRoomAndDate(String room, LocalDate date){
        return appointmentRepository.findByRoomAndDate(room, date);
    }


    public Appointment addAppointment(AddAppointmentRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id = " + request.getPatientId()));
        User dentist = userRepository.findByRoleAndId("ROLE_DENTIST", request.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id = " + request.getDentistId()));
        User assistant = null;
        if(request.getAssistantId() != null){
            assistant = userRepository.findByRoleAndId("ROLE_DENTIST", request.getAssistantId())
                    .orElseThrow(() -> new ResourceNotFoundException("No patient with id = " + request.getAssistantId()));
        }
        //todo: get more information for appointment
        Appointment appointment = Appointment.builder()
                .dentist(dentist)
                .assistant(assistant)
                .patient(patient)
                .createdAt(LocalDateTime.now())
                .status(request.getStatus())
                .room(request.getRoom())
                .date(request.getAppointmentDate())
                .time(request.getAppointmentTime())
                .note(request.getNote())
                .build();
        return appointmentRepository.save(appointment);
    }

    public String deleteAppointment(Integer appointmentId) {
        appointmentRepository.deleteById(appointmentId);
        return "Deleted";
    }

    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("No appointment with id = " + appointmentId));
    }


    public List<Appointment> findByPatientIdAndStatus(Integer patientId, String status) {
        return appointmentRepository.findByPatient_IdAndStatus(patientId, status);
    }

}
