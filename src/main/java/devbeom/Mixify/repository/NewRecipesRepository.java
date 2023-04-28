package devbeom.Mixify.repository;

import devbeom.Mixify.domain.NewRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRecipesRepository extends JpaRepository<NewRecipes, Long> {
}
