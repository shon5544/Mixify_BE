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

    private String user_id;
    private String user_pw;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Scrap> scrapsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private final List<Recipe> recipeList = new ArrayList<>();

    // Comment 의 경우 비즈니스 로직에서 Comment List 를 가져와야 한다고 판단되면 추가로 개발

    @Builder
    public User(Long id, String user_id, String user_pw) {
        this.id = id;
        this.user_id = user_id;
        this.user_pw = user_pw;
    }
}
