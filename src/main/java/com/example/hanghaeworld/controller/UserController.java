package com.example.hanghaeworld.controller;

import com.example.hanghaeworld.dto.*;
import com.example.hanghaeworld.security.UserDetailsImpl;
import com.example.hanghaeworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @PostMapping("/user/signup")
    public ModelAndView signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping("/user/login")
    public void login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
    }

    //전체 회원 조회
    @GetMapping("/home/{page}")
    public List<UserResponseDto> getUsers(@PathVariable int page){
        return userService.getUsers(page);
    }


    //특정 회원 검색 조회
    @GetMapping("/user/search")
    public UserSearchResponseDto search(@RequestBody UserSearchRequestDto userSearchRequestDto){
        return userService.search(userSearchRequestDto);
    }

    @PostMapping("user/like/{likedUserid}")
    public UserResponseDto likeUser(@PathVariable Long likedUserid, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.likeUser(likedUserid, userDetails);
    }

}
