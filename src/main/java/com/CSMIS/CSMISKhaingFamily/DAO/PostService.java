package com.CSMIS.CSMISKhaingFamily.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.CSMIS.CSMISKhaingFamily.Entity.Post;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


@Service
public class PostService {
    private final PostRepository postRepository;
    
    private String imageUploadDirectory;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String text, MultipartFile image,int durationDays) throws IOException {
        Post post = new Post();
        post.setText(text);
        
        if (image != null && !image.isEmpty()) {
//            String imageName = UUID.randomUUID().toString(); // Generate a unique name for the image
        	System.out.println(image + " annoucement image");
        	System.out.println(image.getName() + " annoucement image get name");
          saveImage(image, image.getOriginalFilename());
            post.setImagePath(image.getOriginalFilename());
            
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plus(durationDays, ChronoUnit.DAYS);
        post.setExpirationDate(expirationDate);
        return postRepository.save(post);
    }
   
    public List<Post> getAllActivePosts() {
        return postRepository.getAllActivePost();
    }

    private void saveImage(MultipartFile image, String imageName) throws IOException {
    	 String destinationFolder = "static/image/";
    	 Path destinationPath = Paths.get("src", "main", "resources", destinationFolder);
    	 Path destinationFile = destinationPath.resolve(imageName);
    	 Files.copy(image.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        String imageFilePath = destinationPath + imageName;
        Path imagePath = Path.of(imageFilePath);
        Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

       
    }
}