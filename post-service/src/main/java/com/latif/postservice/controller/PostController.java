package com.latif.postservice.controller;

import com.latif.postservice.payload.BaseResponse;
import com.latif.postservice.exception.ResourceNotFoundException;
import com.latif.postservice.model.Post;
import com.latif.postservice.repository.PostRepository;
import com.latif.postservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private LogService logService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<BaseResponse<Post>> getPostById(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));
        return ResponseEntity.ok().body(new BaseResponse<>(post));
    }

    @PostMapping("/posts")
    public ResponseEntity<BaseResponse<Post>> createPost(@Valid @RequestBody Post post) {
        logService.send("Post Created");
        final Post createdPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(post));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<BaseResponse<Post>> updatePost(@PathVariable(value = "id") Long postId,
                                           @Valid @RequestBody Post postDetails) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));
        post.setContent(postDetails.getContent());
        post.setUserId(postDetails.getUserId());
        final Post updatedPost = postRepository.save(post);
        logService.send("Post Updated");
        return ResponseEntity.ok(new BaseResponse<>(post));
    }

    @DeleteMapping("/posts/{id}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "id") Long postId)
            throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));

        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logService.send("Post Deleted");
        return response;
    }
}
