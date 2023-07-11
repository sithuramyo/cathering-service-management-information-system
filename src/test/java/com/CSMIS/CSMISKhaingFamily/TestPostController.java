package com.CSMIS.CSMISKhaingFamily;



import com.CSMIS.CSMISKhaingFamily.Controller.PostController;
import com.CSMIS.CSMISKhaingFamily.DAO.PostService;
import com.CSMIS.CSMISKhaingFamily.Entity.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestPostController {

    private PostService postService;
    private PostController postController;

    @BeforeEach
    public void setUp() {
        postService = mock(PostService.class);
        postController = new PostController(postService);
    }

    @Test
    public void testCreatePost() throws IOException {
        // Mock data
        String text = "Test post";
        int durationDays = 5;
        MultipartFile imageFile = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image".getBytes());

        // Mock service method
        Post createdPost = new Post();
        when(postService.createPost(text, imageFile, durationDays)).thenReturn(createdPost);

        // Perform the request
        ResponseEntity<Post> responseEntity = postController.createPost(text, imageFile, String.valueOf(durationDays));

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdPost, responseEntity.getBody());
    }

    @Test
    public void testCreatePost_withIOException() throws IOException {
        // Mock data
        String text = "Test post";
        int durationDays = 5;
        MultipartFile imageFile = new MockMultipartFile("image", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test image".getBytes());

        // Mock service method to throw IOException
        when(postService.createPost(text, imageFile, durationDays)).thenThrow(new IOException());

        // Perform the request
        ResponseEntity<Post> responseEntity = postController.createPost(text, imageFile, String.valueOf(durationDays));

        // Verify the response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}

