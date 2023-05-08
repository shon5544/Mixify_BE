package devbeom.Mixify.service;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapService {
    private final ScrapRepository scrapRepository;

    @Transactional
    public Scrap addScrap(Scrap scrap) {
        return scrapRepository.save(scrap);
    }
}
