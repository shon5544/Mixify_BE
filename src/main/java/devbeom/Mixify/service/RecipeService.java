package devbeom.Mixify.service;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.recipe.RecipeGeneralReqDTO;
import devbeom.Mixify.dto.response.recipe.RecipeGeneralResDTO;
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
        String recipeAuthorId = recipeGeneralReqDTO.getUserId();

        if(currentUserId.equals(recipeAuthorId)){
            User user = userRepository.findByUserId(recipeAuthorId).orElseThrow(EntityNotFoundException::new);
            Recipe recipe = recipeGeneralReqDTO.toEntity(user);

            return recipeRepository.save(recipe);
        } else {
            throw new IllegalStateException("다른 유저의 명의로 게시글 작성하려는 움직임 감지. 잘못된 접근을 시도한 유저 ID: "
                    + currentUserId);
        }


    }

    @Transactional
    public Recipe editRecipe(RecipeGeneralReqDTO recipeFromDTO, Long recipeId) {
        String currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증받은 토큰이 없습니다."));
        String recipeAuthorId = recipeFromDTO.getUserId();

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

        if(currentUserId.equals(recipeAuthorId) && recipe.getUser().getUserId().equals(recipeAuthorId)) {
            // recipe 객체 세팅
            recipe.setTitle(recipeFromDTO.getTitle());
            recipe.setThumbnail(recipeFromDTO.getThumbnail());
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
            throw new IllegalStateException("다른 유저의 댓글에 대한 수정 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUserId);
        }
    }

    public RecipeGeneralResDTO getRecipeResById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

        return new RecipeGeneralResDTO(recipe);
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
    }
}
