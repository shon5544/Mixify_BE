package devbeom.Mixify.dto.request.step;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class StepGeneralReqDTO {
    private int stepIndex;
    private String content;
    private String image;

    public Step toEntity(Recipe recipe) {
        return Step.builder()
                .stepIndex(stepIndex)
                .content(content)
                .image(image)
                .recipe(recipe)
                .build();
    }
}
