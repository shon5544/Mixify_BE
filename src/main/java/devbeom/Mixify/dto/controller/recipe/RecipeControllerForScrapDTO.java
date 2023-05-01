package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecipeControllerForScrapDTO extends RecipeControllerParentDTO {

    public void toDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.likeCnt = recipe.getLikeCnt();

        this.scrapCnt = recipe.getScrapCnt();
        this.commentCnt = recipe.getCommentCnt();
    }
}
