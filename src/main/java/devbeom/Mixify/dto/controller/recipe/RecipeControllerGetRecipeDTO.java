package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.controller.comment.CommentControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.ingredient.IngredientControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.step.StepControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.user.UserControllerForRecipeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class RecipeControllerGetRecipeDTO extends RecipeControllerParentDTO{
    // 생성자로 바로 설정이 가능한 경우 생성자로도 DTO 초기화를 가능하게끔 했다.
    public RecipeControllerGetRecipeDTO(Recipe recipe) {
        this.toDTO(recipe);
    }

    public void toDTO(Recipe recipe) {
        this.title = recipe.getTitle();
        this.thumbnail = recipe.getThumbnail();
        this.likeCnt = recipe.getLikeCnt();
        this.scrapCnt = recipe.getScrapCnt();
        this.commentCnt = recipe.getCommentCnt();
        this.star = recipe.getStar();

        StepControllerForRecipeDTO stepControllerParentDTO = new StepControllerForRecipeDTO();
        this.stepList = recipe.getSteps().stream()
                .map(step -> {
                    stepControllerParentDTO.toDTO(step);
                    return stepControllerParentDTO;
                })
                .collect(Collectors.toList());

        CommentControllerForRecipeDTO controllerParentDTO = new CommentControllerForRecipeDTO();
        this.commentList = recipe.getCommentList().stream()
                .map(comment -> {
                    controllerParentDTO.toDTO(comment);
                    return controllerParentDTO;
                })
                .collect(Collectors.toList());

        IngredientControllerForRecipeDTO ingredientControllerParentDTO = new IngredientControllerForRecipeDTO();
        this.ingredientList = recipe.getIngredientList().stream()
                .map(ingredient -> {
                    ingredientControllerParentDTO.toDTO(ingredient);
                    return ingredientControllerParentDTO;
                })
                .collect(Collectors.toList());

        user.toDTO(recipe.getUser());
    }
}
