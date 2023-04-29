package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByUser(User user);
}
