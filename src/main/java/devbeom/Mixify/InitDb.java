package devbeom.Mixify;

import devbeom.Mixify.domain.Authority;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.repository.AuthRepository;
import devbeom.Mixify.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @PostConstruct
    @Transactional
    public void init() {
        Authority authority1 = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Authority authority2 = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority1);
        authorities.add(authority2);

        User user = User.builder()
                .userId("beomsu3206")
                .userPw("")
                .username("beomsu")
                .authorities(authorities)
                .build();

        authRepository.save(authority1);
        authRepository.save(authority2);
        userRepository.save(user);
    }
}
