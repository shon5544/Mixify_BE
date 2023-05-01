package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, String> {
}
