package devbeom.Mixify.service;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.repository.ScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public Scrap getScrapByUser(User user) {
        return scrapRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);
    }
}
