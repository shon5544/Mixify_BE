package devbeom.Mixify.dto.response.step;

import devbeom.Mixify.domain.Step;
import lombok.Getter;

@Getter
public class StepGeneralResDTO {
    private final int stepIndex;

    private final String content;
    private final String image;

    public StepGeneralResDTO(Step step) {
        stepIndex = step.getStepIndex();
        content = step.getContent();
        image = step.getImage();
    }
}
