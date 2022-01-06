package com.latif.postservice.controller;

import com.latif.postservice.model.Post;
import com.latif.postservice.repository.PostRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceTest {

    private static EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private PostController postController;

    @Mock
    private PostRepository postRepository;
    @Spy
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_WillReturnListCommentOutput() {
        Iterable<Post> post = EASY_RANDOM.objects(Post.class, 4)
                .collect(Collectors.toList());
        when(postRepository.findAll()).thenReturn((List<Post>) post);

        var result = postController.getAllPosts();

        List<Post> outputs = new ArrayList<>();
        for (Post product: post) {
            outputs.add(mapper.map(product, Post.class));
        }
        verify(postRepository, times(1)).findAll();
        assertEquals(outputs, result);
    }
}

