package org.choongang.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc // MockMvc를 주입 받는다.
public class SecurityTest {
    
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1() throws Exception {
        mockMvc.perform(post("/member/join").with(csrf().asHeader()) // 스프링 시큐리티에서는 꼭 토큰을 넣어야 한다.
                .param("email", "test@choongang.com"))
                .andDo(print()); // 요청과 응답을 자세히 볼 때 쓴다
    }

    @Test
    void test2() throws Exception {
        mockMvc.perform(get("/mypage"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void test3() throws Exception {
        mockMvc.perform(get("/mypage"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user99@test.org", authorities = "ADMIN")
    void test4() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print());
    }

}
