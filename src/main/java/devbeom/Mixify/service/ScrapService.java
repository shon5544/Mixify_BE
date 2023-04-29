package devbeom.Mixify.service;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.service.user.UserServiceParentDTO;
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

    // 의존성 문제로 인하여 추후 개발
//    public Scrap getScrapByUser(UserServiceParentDTO userServiceParentDTO) {
//        userServiceParentDTO.toEntity();
//        return scrapRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);
//    }
}
