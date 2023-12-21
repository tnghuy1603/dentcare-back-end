package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Tooth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToothRepository extends JpaRepository<Tooth, Integer> {

}
