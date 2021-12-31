package com.postservice.controller;

import com.postservice.dto.PostDTO;
import com.postservice.model.Post;
import com.postservice.service.PostService;
import com.postservice.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ragcrix
 */
@RestController
@RequestMapping("/students")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/")
    public List<PostDTO> getAllStudents() {
        return ObjectMapperUtils.mapAll(postService.findAll(), PostDTO.class);
    }

    @GetMapping(value = "/byStudentNumber/{studentNumber}")
    public PostDTO getStudentByStudentNumber(@PathVariable("studentNumber") Long studentNumber) {
        return ObjectMapperUtils.map(postService.findByStudentNumber(studentNumber), PostDTO.class);
    }

    @GetMapping(value = "/byEmail/{email}")
    public PostDTO getStudentByEmail(@PathVariable("email") String email) {
        return ObjectMapperUtils.map(postService.findByEmail(email), PostDTO.class);
    }

    @GetMapping(value = "/orderByGpa")
    public List<PostDTO> findAllByOrderByGpaDesc() {
        return ObjectMapperUtils.mapAll(postService.findAllByOrderByGpaDesc(), PostDTO.class);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody PostDTO postDTO) {
        postService.saveOrUpdateStudent(ObjectMapperUtils.map(postDTO, Post.class));
        return new ResponseEntity("Student added successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{studentNumber}")
    public ResponseEntity<?> deleteStudentByStudentNumber(@PathVariable long studentNumber) {
        postService.deleteStudentById(postService.findByStudentNumber(studentNumber).getId());
        return new ResponseEntity("Student deleted successfully", HttpStatus.OK);
    }

}
