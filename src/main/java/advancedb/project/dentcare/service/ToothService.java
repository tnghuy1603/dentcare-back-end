package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Tooth;
import advancedb.project.dentcare.repository.ToothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToothService {
    private final ToothRepository toothRepository;
    public List<Tooth> findAll(){
        return toothRepository.findAll();
    }
}
