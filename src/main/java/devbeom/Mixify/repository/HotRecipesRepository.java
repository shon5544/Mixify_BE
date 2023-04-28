package devbeom.Mixify.repository;

import devbeom.Mixify.domain.HotRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotRecipesRepository extends JpaRepository<HotRecipes, Long> {
}
