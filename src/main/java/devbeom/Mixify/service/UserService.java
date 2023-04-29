package devbeom.Mixify.service;

import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.service.user.UserServiceParentDTO;
import devbeom.Mixify.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserServiceParentDTO userServiceParentDTO) {
        User user = userServiceParentDTO.toEntity();
        userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
