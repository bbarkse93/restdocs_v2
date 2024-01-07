package com.example.restdocs.user.dto;

import com.example.restdocs.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserReqDTO {

    @Data
    public static class JoinDTO {

        @Size(min = 2, max = 20)
        @NotEmpty
        private String username;
        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;
        @Size(min = 10, max = 100)
        @NotEmpty
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {

        @Size(min = 2, max = 20)
        @NotEmpty
        private String username;
        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;

    }
}
