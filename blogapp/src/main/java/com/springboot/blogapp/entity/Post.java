package com.springboot.blogapp.entity;

import java.util.LinkedList;
import java.util.List;

import com.springboot.blogapp.dto.PostDTO;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Nonnull
	private String title;
	@Nonnull
	private String description;
	@Nonnull
	private String content;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new LinkedList<>();

	public static Post createPostEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		return post;
	}
}
