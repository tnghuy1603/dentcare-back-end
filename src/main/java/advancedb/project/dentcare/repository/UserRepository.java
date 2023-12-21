package advancedb.project.dentcare.repository;


import advancedb.project.dentcare.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<Object> findByRole(String role);

    Optional<User> findByEmail(String email);

    Optional<User> findByRoleAndId(String role, Integer id);
}
