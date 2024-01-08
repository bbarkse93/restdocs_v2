package com.example.restdocs.user;

import com.example.restdocs.MyWithRestDoc;
import com.example.restdocs.user.dto.UserReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest // 테스트할 때 모든걸 메모리에 띄울 때
public class UserControllerTest extends MyWithRestDoc {


    @Test
    public void join_test() throws Exception {
        // given
        UserReqDTO.JoinDTO requestDTO = new UserReqDTO.JoinDTO();
        requestDTO.setUsername("love");
        requestDTO.setPassword("1234");
        requestDTO.setEmail("love@nate.com");
        ObjectMapper om = new ObjectMapper();
        String requestBody = om.writeValueAsString(requestDTO);

        // when
        ResultActions ra = mockMvc.perform(
                MockMvcRequestBuilders.post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );


        String responseBody = ra.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

//        // then
//        // 잘못된 검증
////        ra
////                .andExpect(MockMvcResultMatchers.status().isCreated())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ssar"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("1234"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ssar@nate.com"));
////        // 제대로 된 검증
        ra
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("love"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("love@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void userInfo_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions ra = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users/"+id)

        );

        String responseBody = ra.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        //then
        ra
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("ssar@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void userAll_test() throws Exception {
        // given


        // when
        ResultActions ra = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/users")

        );

        String responseBody = ra.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);
        //then
        ra
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].username").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[0].email").value("ssar@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].username").value("cos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response[1].email").value("cos@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }
}
