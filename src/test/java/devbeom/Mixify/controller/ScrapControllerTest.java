package devbeom.Mixify.controller;

import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.ScrapService;
import devbeom.Mixify.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(ScrapController.class)
@AutoConfigureMockMvc
class ScrapControllerTest {
    @Autowired
    MockMvc mockMvc;

//    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ScrapService scrapService;

    @MockBean
    private UserService userService;

    @MockBean
    private RecipeService recipeService;

    @Test
    @DisplayName("scrap 추가 기능 검증")
    void test1() throws Exception {
        //given
        String userId = "u=1&";
        String recipeId = "r=1";

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/scrap/add?" + userId + recipeId)
        );

        //then
        resultActions.andExpect(status().isOk());
    }
}