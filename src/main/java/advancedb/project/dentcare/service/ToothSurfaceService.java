package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.ToothSurface;
import advancedb.project.dentcare.repository.ToothSurfaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToothSurfaceService {
    private final ToothSurfaceRepository toothSurfaceRepository;

    public List<ToothSurface> findAll() {
        return toothSurfaceRepository.findAll();
    }
}
