package devbeom.Mixify.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

    private String title;

    @Column(name = "like_cnt")
    private int likeCnt;

    @Column(name = "scrap_cnt")
    private int scrapCnt;

    @Column(name = "comment_cnt")
    private int commentCnt;

    private String thumbnail;

    private float star;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private final List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private final List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Scrap scrap;

    @ManyToOne(fetch = FetchType.LAZY)
    private HotRecipes hotRecipes;

    @ManyToOne(fetch = FetchType.LAZY)
    private NewRecipes newRecipes;
}
