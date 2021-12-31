package com.postservice.service;

import com.postservice.model.Post;

import java.util.List;

/**
 * @author regcrix
 */
public interface PostService {

    List<Post> findAll();

    Post findByStudentNumber(long studentNumber);

    Post findByEmail(String email);

    List<Post> findAllByOrderByGpaDesc();

    Post saveOrUpdateStudent(Post student);

    void deleteStudentById(String id);

}
