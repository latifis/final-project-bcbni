package com.postservice.service.impl;

import com.postservice.model.Post;
import com.postservice.repository.PostRepository;
import com.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ragcrix
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findByStudentNumber(long studentNumber) {
        return postRepository.findByStudentNumber(studentNumber);
    }

    @Override
    public Post findByEmail(String email) {
        return postRepository.findByEmail(email);
    }

    @Override
    public List<Post> findAllByOrderByGpaDesc() {
        return postRepository.findAllByOrderByGpaDesc();
    }

    @Override
    public Post saveOrUpdateStudent(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteStudentById(String id) {
        postRepository.deleteById(id);
    }
}
