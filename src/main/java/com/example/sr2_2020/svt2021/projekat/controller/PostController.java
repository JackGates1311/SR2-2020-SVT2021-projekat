package com.example.sr2_2020.svt2021.projekat.controller;

import com.example.sr2_2020.svt2021.projekat.dto.PostRequest;
import com.example.sr2_2020.svt2021.projekat.dto.PostResponse;
import com.example.sr2_2020.svt2021.projekat.repository.PostRepository;
import com.example.sr2_2020.svt2021.projekat.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody PostRequest postRequest, HttpServletRequest request) {

        postService.save(postRequest, request);

        return new ResponseEntity("Post is successfully created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllPosts")
    public ResponseEntity<List<PostResponse>> getAllPosts() {

        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {

        return new ResponseEntity(postService.getPost(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PostRequest> editPost(@RequestBody PostRequest postRequest, @PathVariable Long id,
            HttpServletRequest request) {

        return postService.editPost(postRequest, id, request);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable Long id) {

        return postService.deleteById(id);
    }

    @RequestMapping("/communityName={communityName}")
    public ResponseEntity<List<PostResponse>> getCommunityPosts(@PathVariable String communityName) {

        return new ResponseEntity(postService.getPostsByCommunityName(communityName), HttpStatus.OK);
    }

    //TODO implement getPostsByCommunityMethod (String communityId) and getPostsByUsername(String username)

}
