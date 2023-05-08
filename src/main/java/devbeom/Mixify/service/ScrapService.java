package devbeom.Mixify.service;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.dto.response.scrap.ScrapResDTO;
import devbeom.Mixify.repository.ScrapRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapService {
    private final ScrapRepository scrapRepository;

    @Transactional
    public Scrap addScrap(Scrap scrap) {
        return scrapRepository.save(scrap);
    }

    public List<ScrapResDTO> getScrapList(Long userId) {
        List<Scrap> scrapList = scrapRepository.findAllByUserId(userId).orElseThrow(EntityNotFoundException::new);

        return scrapList.stream()
                .map(ScrapResDTO::new)
                .collect(Collectors.toList());
    }
}
