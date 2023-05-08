package devbeom.Mixify.dto.response.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.response.comment.CommentGeneralResDTO;
import devbeom.Mixify.dto.response.ingredient.IngredientGeneralResDTO;
import devbeom.Mixify.dto.response.step.StepGeneralResDTO;
import devbeom.Mixify.dto.response.user.UserGeneralResDTO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeGeneralResDTO {
    private final Long recipeId;

    private final UserGeneralResDTO user;

    private final String title;
    private final String thumbnail;

    private final int likeCnt;
    private final int scrapCnt;
    private final int commentCnt;

    private final float star;

    private final List<StepGeneralResDTO> steps;
    private final List<CommentGeneralResDTO> comments;
    private final List<IngredientGeneralResDTO> ingredients;

    public RecipeGeneralResDTO(Recipe recipe) {
        recipeId = recipe.getId();
        title = recipe.getTitle();
        thumbnail = recipe.getThumbnail();
        likeCnt = recipe.getLikeCnt();
        scrapCnt = recipe.getScrapCnt();
        commentCnt = recipe.getCommentCnt();
        star = recipe.getStar();

        steps = recipe.getSteps().stream()
                .map(StepGeneralResDTO::new)
                .collect(Collectors.toList());

        comments = recipe.getCommentList().stream()
                .map(CommentGeneralResDTO::new)
                .collect(Collectors.toList());

        ingredients = recipe.getIngredientList().stream()
                .map(IngredientGeneralResDTO::new)
                .collect(Collectors.toList());

        user = new UserGeneralResDTO(recipe.getUser());
    }
}
