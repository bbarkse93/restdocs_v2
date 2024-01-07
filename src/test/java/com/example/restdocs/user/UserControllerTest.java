package com.example.restdocs.user;

import com.example.restdocs.user.dto.UserReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest // 테스트할 때 모든걸 메모리에 띄울 때
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void join_test() throws Exception {
        // given
        UserReqDTO.JoinDTO requestDTO = new UserReqDTO.JoinDTO();
        requestDTO.setUsername("cos");
        requestDTO.setPassword("1234");
        requestDTO.setEmail("cos@nate.com");
        ObjectMapper om = new ObjectMapper();
        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions ra = mvc.perform(
                MockMvcRequestBuilders
                        .post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        String responseBody = ra.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then

    }

    @Test
    public void userInfo_test() {

    }
}
