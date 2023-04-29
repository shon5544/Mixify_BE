package devbeom.Mixify.dto.controller.step;

import devbeom.Mixify.domain.Step;

public abstract class StepControllerParentDTO {
    abstract public void toDTO(Step step);
}
