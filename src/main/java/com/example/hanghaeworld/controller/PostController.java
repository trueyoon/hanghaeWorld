package com.example.hanghaeworld.controller;

import com.example.hanghaeworld.dto.*;
import com.example.hanghaeworld.security.UserDetailsImpl;
import com.example.hanghaeworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    @GetMapping("/post/mypost/{username}")
    public MyPostDto getMyPost(@PathVariable String username,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getMyPost(username, userDetails.getUser());
    }

    @GetMapping("/post/visitpost/{username}")
    public VisitPostDto getVisitPost(@PathVariable String username,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getVisitPost(username, userDetails.getUser());
    }

    @PostMapping("/post/{username}")
    public PostResponseDto writePost(@PathVariable String username,
                                     @Valid @RequestBody PostRequestDto postRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.writePost(username, postRequestDto, userDetails.getUser());
    }

    @PutMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId,
                                      @Valid @RequestBody PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
    }

    @PostMapping("/like/{postId}")
    public LikeResponseDto like(@RequestBody LikeRequestDto likeRequestDto, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.like(likeRequestDto, postId, userDetails);
    }

    @PostMapping("/like/{commentId}")
    public LikesResponseDto likes(@RequestBody LikeRequestDto likeRequestDto, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.likes(likeRequestDto, commentId, userDetails);
    }
}
