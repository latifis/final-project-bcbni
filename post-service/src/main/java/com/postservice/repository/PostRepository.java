package com.postservice.repository;

import com.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author ragcrix
 */

// No need implementation, just one interface, and you have CRUD, thanks Spring Data!
public interface PostRepository extends MongoRepository<Post, String> {

    Post findByStudentNumber(long studentNumber);

    Post findByEmail(String email);

    List<Post> findAllByOrderByGpaDesc();

}
