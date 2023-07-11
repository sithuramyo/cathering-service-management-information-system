package com.CSMIS.CSMISKhaingFamily.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.CSMIS.CSMISKhaingFamily.Entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT p FROM Post p WHERE p.expirationDate > NOW() ORDER BY p.id DESC")
	List<Post> getAllActivePost();
}
