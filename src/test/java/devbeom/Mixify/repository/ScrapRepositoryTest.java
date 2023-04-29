package devbeom.Mixify.repository;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.domain.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScrapRepositoryTest {
    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private StepRepository stepRepository;

    @Test
    @DisplayName("유저 엔티티로 스크랩 게시물 가져오기")
    void test1() {
        User user = User.builder()
                .userId("beomsu")
                .userPw("sbs")
                .build();
        userRepository.save(user);

        Recipe recipe = Recipe.builder()
                .user(user)
                .title("adf")
                .build();
        recipeRepository.save(recipe);

        Scrap scrap = Scrap.builder()
                .user(user)
                .recipe(recipe)
                .build();
        scrapRepository.save(scrap);

        Step step1 = Step.builder()
                .stepIndex(1)
                .content("step1")
                .recipe(recipe)
                .build();
        stepRepository.save(step1);

        Scrap foundScrapByUser = scrapRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);

        assertEquals(foundScrapByUser, scrap);
    }
}