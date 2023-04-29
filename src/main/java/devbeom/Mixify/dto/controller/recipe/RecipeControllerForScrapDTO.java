package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;
import lombok.Getter;

@Getter
public class RecipeControllerForScrapDTO extends RecipeControllerParentDTO {
    private Long id;

    private String title;

    private int likeCnt;
    private int scrapCnt;
    private int commentCnt;

    public void toDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.likeCnt = recipe.getLikeCnt();

        this.scrapCnt = recipe.getScrapCnt();
        this.commentCnt = recipe.getCommentCnt();
    }
}
