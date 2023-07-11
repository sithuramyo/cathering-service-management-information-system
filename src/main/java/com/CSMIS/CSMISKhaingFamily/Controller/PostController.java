package com.CSMIS.CSMISKhaingFamily.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import com.CSMIS.CSMISKhaingFamily.DAO.PostService;
import com.CSMIS.CSMISKhaingFamily.Entity.Post;





@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/admin/setupAnnoucement")
    public String gotoHome(Model model) {
    	
    	return "createpost";
    }
   
    
    @PostMapping("/admin/posts")
    public ResponseEntity<Post> createPost(@RequestParam("text") String text, @RequestParam(value = "image", required = false) MultipartFile imageFile,@RequestParam("duration") String duration) {
        try {
        	int durationDays = Integer.parseInt(duration);
            Post post = postService.createPost(text, imageFile,durationDays);
            return ResponseEntity.ok(post);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
