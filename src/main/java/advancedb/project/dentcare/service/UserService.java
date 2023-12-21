package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Branch;
import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.dto.AddUserRequest;
import advancedb.project.dentcare.dto.CustomResponse;
import advancedb.project.dentcare.dto.UpdateUserRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.BranchRepository;
import advancedb.project.dentcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Object> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    public User addUser(AddUserRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("No branch with id = " + request.getBranchId()));

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
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already in use");
        }
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("No branch with id = " + request.getBranchId()));
        User userToUpdate = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No user with id"+ request.getUserId()));
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




}
