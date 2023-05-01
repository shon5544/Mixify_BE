package devbeom.Mixify.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    private String username;

    private boolean activated;

    @Column(name = "user_star")
    private float userStar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Scrap> scrapsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Recipe> recipeList = new ArrayList<>();

    // Comment 의 경우 비즈니스 로직에서 Comment List 를 가져와야 한다고 판단되면 추가로 개발

    // 딱히 양방향일 필요는 없을 것 같음.
    // 좋지 않음. jwt를 위한 다대다 연관관계인데 중간 테이블로 풀어서 적용해도 customUserDetailsService에서 사용할 수 있도록 로직을 짜자.
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public User(String userId, String userPw, String username, float userStar, boolean activated, Set<Authority> authorities) {
        this.userId = userId;
        this.userPw = userPw;
        this.username = username;
        this.userStar = userStar;
        this.activated = activated;
        this.authorities = authorities;
    }
}
