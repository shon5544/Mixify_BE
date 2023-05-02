package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.controller.comment.CommentControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.ingredient.IngredientControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.step.StepControllerForRecipeDTO;
import devbeom.Mixify.dto.controller.user.UserControllerForRecipeDTO;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class RecipeControllerParentDTO {
    protected Long id;
    protected String title;
    protected String thumbnail;

    protected int likeCnt;
    protected int scrapCnt;
    protected int commentCnt;

    protected float star;

    protected List<StepControllerForRecipeDTO> stepList;
    protected List<CommentControllerForRecipeDTO> commentList;
    protected List<IngredientControllerForRecipeDTO> ingredientList;

    protected final UserControllerForRecipeDTO user = new UserControllerForRecipeDTO();
    public abstract void toDTO(Recipe recipe);
}
