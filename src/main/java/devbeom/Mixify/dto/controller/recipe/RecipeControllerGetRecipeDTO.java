package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.controller.comment.CommentControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.comment.CommentControllerParentDTO;
import devbeom.Mixify.dto.controller.ingredient.IngredientControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.ingredient.IngredientControllerParentDTO;
import devbeom.Mixify.dto.controller.step.StepControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.step.StepControllerParentDTO;
import devbeom.Mixify.dto.controller.user.UserControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeControllerGetRecipeDTO extends RecipeControllerParentDTO{
    private String title;
    private String thumbnail;

    private int likeCnt;
    private int scrapCnt;
    private int commentCnt;

    private float star;

    private List<StepControllerParentDTO> stepList;
    private List<CommentControllerParentDTO> commentList;
    private List<IngredientControllerParentDTO> ingredientList;

    private final UserControllerParentDTO user = new UserControllerForRecipeDTO();

    public void toDTO(Recipe recipe) {
        this.title = recipe.getTitle();
        this.thumbnail = recipe.getThumbnail();
        this.likeCnt = recipe.getLikeCnt();
        this.scrapCnt = recipe.getScrapCnt();
        this.commentCnt = recipe.getCommentCnt();
        this.star = recipe.getStar();

        StepControllerParentDTO stepControllerParentDTO = new StepControllerForRecipeDTO();
        this.stepList = recipe.getSteps().stream()
                .map(step -> {
                    stepControllerParentDTO.toDTO(step);
                    return stepControllerParentDTO;
                })
                .collect(Collectors.toList());

        CommentControllerParentDTO controllerParentDTO = new CommentControllerForRecipeDTO();
        this.commentList = recipe.getCommentList().stream()
                .map(comment -> {
                    controllerParentDTO.toDTO(comment);
                    return controllerParentDTO;
                })
                .collect(Collectors.toList());

        IngredientControllerParentDTO ingredientControllerParentDTO = new IngredientControllerForRecipeDTO();
        this.ingredientList = recipe.getIngredientList().stream()
                .map(ingredient -> {
                    ingredientControllerParentDTO.toDTO(ingredient);
                    return ingredientControllerParentDTO;
                })
                .collect(Collectors.toList());

        user.toDTO(recipe.getUser());
    }
}
