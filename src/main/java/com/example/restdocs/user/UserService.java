package com.example.restdocs.user;

import com.example.restdocs.user.dto.UserReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User 회원가입(UserReqDTO.JoinDTO DTO) {
        return userRepository.save(DTO.toEntity());
    }

    public User 회원조회(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    public List<User> 회원전체조회() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
