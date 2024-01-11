package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.ToothSurface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToothSurfaceRepository extends JpaRepository<ToothSurface, Integer> {
}
