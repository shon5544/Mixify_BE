package devbeom.Mixify.service;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.repository.StepRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StepService {
    private final StepRepository stepRepository;

    @Transactional
    public void createSteps(List<Step> stepList) {
        stepRepository.saveAll(stepList);
    }

    public List<Step> getStepByRecipe(Recipe recipe) {
        return stepRepository.findAllByRecipe(recipe).orElseThrow(EntityNotFoundException::new);
    }
}
