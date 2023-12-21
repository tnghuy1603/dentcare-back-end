package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.TreatmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentCategoryRepository extends JpaRepository<TreatmentCategory, Integer> {
}
