package devbeom.Mixify.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_star")
    private float userStar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Scrap> scrapsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Recipe> recipeList = new ArrayList<>();

    // Comment 의 경우 비즈니스 로직에서 Comment List 를 가져와야 한다고 판단되면 추가로 개발

    @Builder
    public User(String userId, String userPw, float userStar) {
        this.userId = userId;
        this.userPw = userPw;
        this.userStar = userStar;
    }
}
