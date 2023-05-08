package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select r from Recipe r join fetch r.user u where r.id = :id")
    Optional<Recipe> findById(@Param("id") Long id);
}
