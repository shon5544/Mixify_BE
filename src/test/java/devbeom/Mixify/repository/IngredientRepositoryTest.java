package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IngredientRepositoryTest {
    @Autowired IngredientRepository ingredientRepository;
    @Autowired RecipeRepository recipeRepository;

    @Test
    @DisplayName("레시피에 있는 재료 찾기")
    void test1() {
        Recipe recipe1 = Recipe.builder()
                .title("fff")
//                .user(User.builder().build())
                .build();
        recipeRepository.save(recipe1);

        Recipe recipe2 = Recipe.builder()
                .title("dddd")
//                .user(User.builder().build())
                .build();
        recipeRepository.save(recipe2);

        Ingredient ingredient = Ingredient.builder()
                .name("asdffff")
                .quantity("1oz")
                .recipe(recipe1)
                .build();
        ingredientRepository.save(ingredient);

        Ingredient ingredient2 = Ingredient.builder()
                .name("ffffff")
                .quantity("23123oz")
                .recipe(recipe1)
                .build();
        ingredientRepository.save(ingredient2);

        Ingredient ingredient3 = Ingredient.builder()
                .name("ffffff")
                .quantity("23123oz")
                .recipe(recipe2)
                .build();
        ingredientRepository.save(ingredient3);

        ingredientRepository.flush();
        List<Ingredient> allByRecipe = ingredientRepository.findAllByRecipe(recipe1);

        allByRecipe.forEach((i) -> System.out.println(i.getName()));
        assertEquals(allByRecipe.size(), 2);
    }
}