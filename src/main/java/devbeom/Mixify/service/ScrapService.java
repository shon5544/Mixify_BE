package devbeom.Mixify.service;

import devbeom.Mixify.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScrapService {
    private final ScrapRepository scrapRepository;

}
