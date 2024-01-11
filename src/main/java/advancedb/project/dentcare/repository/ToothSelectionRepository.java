package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.ToothSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToothSelectionRepository extends JpaRepository<ToothSelection, Integer> {
    ToothSelection findByTooth_IdAndToothSurface_Id(Integer toothId, Integer surfaceId);
}
