package devbeom.Mixify.service;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.repository.ScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public Scrap getScrapByUser(User user) {
        return scrapRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);
    }
}
