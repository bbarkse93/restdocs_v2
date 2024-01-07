package com.example.restdocs.user;

import com.example.restdocs.user.dto.UserReqDTO;
import com.example.restdocs.utils.ApiUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserReqDTO.JoinDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            FieldError fieldError = errors.getFieldErrors().get(0);
            String key = fieldError.getField();
            String value = fieldError.getDefaultMessage();
            return new ResponseEntity<>(ApiUtil.error(value + ":" + key), HttpStatus.BAD_REQUEST);
        }

        User user = userService.회원가입(requestDTO);

        return ResponseEntity.ok().body(ApiUtil.success(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {

        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Integer id) {
       User user = userService.회원조회(id);
        return ResponseEntity.ok().body(ApiUtil.success(user));
    }
}
