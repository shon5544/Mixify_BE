package devbeom.Mixify.service;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.recipe.RecipeGeneralReqDTO;
import devbeom.Mixify.jwt.utils.SecurityUtil;
import devbeom.Mixify.repository.IngredientRepository;
import devbeom.Mixify.repository.RecipeRepository;
import devbeom.Mixify.repository.StepRepository;
import devbeom.Mixify.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final StepRepository stepRepository;
    private final UserRepository userRepository;

    @Transactional
    public Recipe createRecipe(RecipeGeneralReqDTO recipeGeneralReqDTO) {
        String currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증된 토큰이 없습니다."));

        User user = userRepository.findByUserId(currentUserId).orElseThrow(EntityNotFoundException::new);
        Recipe recipe = recipeGeneralReqDTO.toEntity(user);

        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe editRecipe(RecipeGeneralReqDTO recipeFromDTO, Long recipeId) {
        String currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증받은 토큰이 없습니다."));

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
        String recipeAuthorId = recipe.getUser().getUserId();

        if(currentUserId.equals(recipeAuthorId)) {
            // recipe 객체 세팅
            recipe.setTitle(recipeFromDTO.getTitle());
            recipe.setThumbnail(recipeFromDTO.getThumbnail());

            // IngredientList, StepList가 지연로딩으로 잡혀있기 때문에 get하는 시점에서 쿼리 발생.
            // 컬렉션 페치 조인은 하지 않는다. 성능과부화와 차후 페이징기능을 고려하고 있기 때문.
            recipe.getIngredientList().clear(); // 재료 삭제. ingredient에 orphanRemoval 적용되어있음.
            recipe.getStepList().clear(); // 단계 삭제. step에 orphanRemoval 적용되어있음.

            // ingredient 재등록
            List<Ingredient> ingredientList = recipeFromDTO.getIngredients().stream()
                    .map(ingredientGeneralReqDTO -> ingredientGeneralReqDTO.toEntity(recipe))
                    .collect(Collectors.toList());
            ingredientRepository.saveAll(ingredientList);

            log.info("ingredientList 저장 끝남");

            // step 재등록
            List<Step> stepList = recipeFromDTO.getStepList().stream()
                    .map(stepGeneralReqDTO -> stepGeneralReqDTO.toEntity(recipe))
                    .collect(Collectors.toList());
            stepRepository.saveAll(stepList);

            log.info("stepList 저장 끝남");

            return recipe;
        } else {
            throw new RuntimeException("다른 유저의 댓글에 대한 수정 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUserId);
        }
    }

    @Transactional
    public void deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("엔티티를 찾을 수 없음: RecipeService.deleteRecipe"));

        String currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증된 토큰 없음."));
        String recipeAuthor = recipe.getUser().getUserId();

        if(currentUserId.equals(recipeAuthor)) {
            recipeRepository.delete(recipe);
            log.info("id: " + recipeId + " recipe 삭제됨");
        } else {
            throw new RuntimeException("다른 유저의 댓글에 대한 수정 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUserId);
        }
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
    }
}
