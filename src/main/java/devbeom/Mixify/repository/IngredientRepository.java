package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("select i from Ingredient i where i.recipe = :recipe")
    List<Ingredient> findAllByRecipe(@Param("recipe") Recipe recipe);
}
