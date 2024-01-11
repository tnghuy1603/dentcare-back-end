package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.domain.WorkShift;
import advancedb.project.dentcare.domain.WorkingSchedule;
import advancedb.project.dentcare.dto.AddWorkingScheduleRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.UserRepository;
import advancedb.project.dentcare.repository.WorkShiftRepository;
import advancedb.project.dentcare.repository.WorkingScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkingScheduleService {
    private final WorkingScheduleRepository workingScheduleRepository;
    private final UserRepository userRepository;
    private final WorkShiftRepository workShiftRepository;

    public List<WorkingSchedule> findAllSchedule() {
        return workingScheduleRepository.findAll();
    }

    public WorkingSchedule addSchedule(AddWorkingScheduleRequest request) {
        User dentist = userRepository.findByRoleAndId("ROLE_DENTIST", request.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found any dentist with id = " + request.getDentistId()));
        WorkShift workShift = workShiftRepository.findById(request.getWorkShiftId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found any workshift with id " + request.getWorkShiftId()));
        WorkingSchedule workingSchedule = WorkingSchedule.builder()
                .date(request.getDate())
                .workShift(workShift)
                .dentist(dentist)
                .build();
        return workingScheduleRepository.save(workingSchedule);
    }
}
