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
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 초기 생성 시 필요
    private String title;

    @Column(name = "like_cnt")
    private int likeCnt;

    @Column(name = "scrap_cnt")
    private int scrapCnt;

    @Column(name = "comment_cnt")
    private int commentCnt;

    // 초기 생성 시 필요
    private String thumbnail;

    private float star;

    // 초기 생성 시 필요: Entity 에 직접 추가해야한 다는 것은 아님
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private final List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private final List<Comment> commentList = new ArrayList<>();

    // 초기 생성 시 필요: Entity 에 직접 추가해야한 다는 것은 아님
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private final List<Ingredient> ingredientList = new ArrayList<>();

    // 초기 생성 시 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Recipe(String title,
                  int likeCnt,
                  int scrapCnt,
                  int commentCnt,
                  String thumbnail,
                  float star,
                  User user) {
        this.title = title;
        this.likeCnt = likeCnt;
        this.scrapCnt = scrapCnt;
        this.commentCnt = commentCnt;
        this.thumbnail = thumbnail;
        this.star = star;
        this.user = user;
    }
}
