package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StepRepositoryTest {
    @Autowired StepRepository stepRepository;
    @Autowired RecipeRepository recipeRepository;

    @Test
    @DisplayName("레시피로 스텝 찾아오기")
    void test1() {
        Recipe recipe1 = Recipe.builder()
                .title("recipe1")
                .build();
        recipeRepository.save(recipe1);

        Step step1 = Step.builder()
                .content("111")
                .stepIndex(1)
                .recipe(recipe1)
                .build();
        stepRepository.save(step1);

        Step step2 = Step.builder()
                .content("222")
                .stepIndex(2)
                .recipe(recipe1)
                .build();
        stepRepository.save(step2);

        Step step3 = Step.builder()
                .content("333")
                .stepIndex(3)
                .recipe(recipe1)
                .build();
        stepRepository.save(step3);

        recipeRepository.flush();
        stepRepository.flush();

        List<Step> steps = stepRepository.findAllByRecipe(recipe1).orElseThrow(EntityNotFoundException::new);
        steps.forEach((s) -> System.out.println(s.getContent()));
        assertEquals(steps.size(), 3);
    }
}