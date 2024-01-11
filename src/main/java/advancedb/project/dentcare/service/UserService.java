package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Branch;
import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.domain.WorkingSchedule;
import advancedb.project.dentcare.dto.AddUserRequest;
import advancedb.project.dentcare.dto.CustomResponse;
import advancedb.project.dentcare.dto.UpdateUserRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.BranchRepository;
import advancedb.project.dentcare.repository.UserRepository;
import advancedb.project.dentcare.repository.WorkingScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkingScheduleRepository workingScheduleRepository;
    public Map<String, Object> findByRole(String role, int size, int page) {
        Pageable paging = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findByRole(role, paging);
        Map<String, Object> response = new HashMap<>();
        response.put("total", usersPage.getTotalElements());
        response.put("totalPages", usersPage.getTotalPages());
        response.put("currentPage", usersPage.getNumber());
        response.put("users", usersPage.getContent());
        return response;
    }
    public List<User> findByRole(String role){
        return userRepository.findByRole(role);
    }


    public User addUser(AddUserRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already in use");
        }
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("No branch with id = " + request.getBranchId()));
        log.info("Creating user");
        //todo: validate some constraints
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .dob(request.getDob())
                .role(request.getRole())
                .branch(branch)
                .build();
        return userRepository.save(user);
    }

    public User updateUser(UpdateUserRequest request) {

        if(userRepository.existsByEmailAndIdIsNot(request.getEmail(), request.getUserId())){
            throw new IllegalArgumentException("Email already in use");
        }
        User userToUpdate = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No user with id"+ request.getUserId()));
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("No branch with id = " + request.getBranchId()));

        userToUpdate.setName(request.getName());
        userToUpdate.setEmail(request.getEmail());
        userToUpdate.setAddress(request.getAddress());
        userToUpdate.setPhoneNumber(request.getPhoneNumber());
        userToUpdate.setDob(request.getDob());
        userToUpdate.setBranch(branch);
        return userRepository.save(userToUpdate);
    }
    public User findByRoleAndId(String role, Integer id){
        return userRepository.findByRoleAndId(role, id)
                .orElseThrow(() -> new ResourceNotFoundException("No user with role = " + role + "and id = " + id));
    }



    public List<User> findAvailableDentist(LocalDate date, Integer shiftId) {
        List<WorkingSchedule> schedules = workingScheduleRepository.findByDateAndWorkShift_Id(date, shiftId);
        return schedules.stream().map(WorkingSchedule::getDentist).toList();
    }
}
