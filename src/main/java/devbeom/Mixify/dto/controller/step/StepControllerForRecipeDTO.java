package devbeom.Mixify.dto.controller.step;

import devbeom.Mixify.domain.Step;
import lombok.Getter;

@Getter
public class StepControllerForRecipeDTO extends StepControllerParentDTO{
    private int stepIndex;
    private String content;
    private String image;

    public void toDTO(Step step) {
        this.stepIndex = step.getStepIndex();
        this.content = step.getContent();
        this.image = step.getImage();
    }
}
