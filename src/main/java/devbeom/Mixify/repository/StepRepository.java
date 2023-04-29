package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    @Query("select s from Step s where s.recipe = :recipe order by s.stepIndex asc")
    Optional<List<Step>> findAllByRecipe(@Param("recipe") Recipe recipe);
}
