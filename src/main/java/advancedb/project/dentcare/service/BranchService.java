package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Branch;
import advancedb.project.dentcare.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    public List<Branch> findAll(){
        return branchRepository.findAll();
    }
}
