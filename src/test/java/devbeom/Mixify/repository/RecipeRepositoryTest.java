//package devbeom.Mixify.repository;
//
//import devbeom.Mixify.domain.Recipe;
//import devbeom.Mixify.domain.Step;
//import devbeom.Mixify.domain.User;
//import jakarta.persistence.EntityNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class RecipeRepositoryTest {
//    @Autowired RecipeRepository recipeRepository;
//    @Autowired UserRepository userRepository;
//    @Autowired StepRepository stepRepository;
//
//    @Test
//    @DisplayName("레시피 DTO test")
//    void test1() {
//        User user = User.builder()
//                .userId("asdffffaa")
//                .userPw("userPw")
//                .userStar(0.0f)
//                .username("beomsu")
//                .build();
//        userRepository.save(user);
//        userRepository.flush();
//
//        Recipe recipe = Recipe.builder()
//                .title("ff")
//                .user(user)
//                .build();
//        recipeRepository.save(recipe);
//        recipeRepository.flush();
//
//        Step step1 = Step.builder()
//                .recipe(recipe)
//                .stepIndex(1)
//                .content("fadfsadf")
//                .build();
//        stepRepository.save(step1);
//        stepRepository.flush();
//
//        Recipe recipe1 = recipeRepository.findById(1L)
//                .orElseThrow(EntityNotFoundException::new);
//
//        RecipeControllerGetRecipeDTO recipeControllerGetRecipeDTO = new RecipeControllerGetRecipeDTO(recipe1);
//
//        System.out.println(recipeControllerGetRecipeDTO);
//        assertEquals(recipeControllerGetRecipeDTO.getUser().getUsername(), user.getUsername());
//        assertEquals(recipeControllerGetRecipeDTO.getStar(), recipe1.getStar());
//        assertEquals(recipeControllerGetRecipeDTO.getTitle(), recipe1.getTitle());
//        assertEquals(recipeControllerGetRecipeDTO.getLikeCnt(), recipe1.getLikeCnt());
//    }
//}