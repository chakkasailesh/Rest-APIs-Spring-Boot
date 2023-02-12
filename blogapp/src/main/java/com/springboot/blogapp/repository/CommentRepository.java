package com.springboot.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.blogapp.entity.Comment;
import com.springboot.blogapp.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostId(long postId);

	@Query("SELECT c FROM Comment c WHERE c.post = ?1 and c.id = ?2")
	Comment findComment(Post post, long commentId);

	@Query(value = "DELETE FROM COMMENTS C WHERE C.ID = ?1 and C.POST_ID = ?2", nativeQuery = true)
	Comment deleteComment(long postId, long commentId);
}
