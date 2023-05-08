package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByUser(User user);

    @Query("select s from Scrap s " +
            "   join fetch s.user u " +
            "   join fetch s.recipe r " +
            "       join fetch r.user " +
            "where u.id = :userId " +
            "order by s.scrappedDate desc ")
    Optional<List<Scrap>> findAllByUserId(@Param("userId") Long userId);
}
