package com.CSMIS.CSMISKhaingFamily;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.CSMIS.CSMISKhaingFamily.DAO.PostRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.PostService;
import com.CSMIS.CSMISKhaingFamily.Entity.Post;

public class TestPostService {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() throws IOException {
        // Create sample data
        String text = "Sample post";
        MultipartFile image = new MockMultipartFile("image.jpg", new byte[0]);
        int durationDays = 7;

        // Mock the behavior of postRepository.save()
        Post savedPost = new Post();
//        savedPost.setId(UUID.randomUUID().toString());
        savedPost.setText(text);
        savedPost.setExpirationDate(LocalDateTime.now().plusDays(durationDays));
        when(postRepository.save(savedPost)).thenReturn(savedPost);

        // Call the service method
        Post result = postService.createPost(text, image, durationDays);

        // Verify the result
//        assertEquals(text, result.getText());
//        assertEquals(durationDays, LocalDateTime.now().until(result.getExpirationDate()).toDays());
    }

    @Test
    public void testGetAllActivePosts() {
        // Create sample data
        Post post1 = new Post();
//        post1.setId(UUID.randomUUID().toString());
        post1.setText("Post 1");
        Post post2 = new Post();
//        post2.setId(UUID.randomUUID().toString());
        post2.setText("Post 2");
        List<Post> activePosts = new ArrayList<>();
        activePosts.add(post1);
        activePosts.add(post2);

        // Mock the behavior of postRepository.getAllActivePost()
        when(postRepository.getAllActivePost()).thenReturn(activePosts);

        // Call the service method
        List<Post> result = postService.getAllActivePosts();

        // Verify the result
        assertEquals(activePosts.size(), result.size());
        assertEquals(activePosts.get(0).getText(), result.get(0).getText());
        assertEquals(activePosts.get(1).getText(), result.get(1).getText());
    }
}

