package com.enigma.repository;

import com.enigma.repository.interfaces.IFileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Repository
public class FileRepository implements IFileRepository {
    private final Path root;

    public FileRepository(@Value("${FILE_PATH}") String rootPath) {
        this.root = Paths.get(rootPath);
    }

    @Override
    public void store(MultipartFile file) {
        try{
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        }catch (Exception e){
            throw new RuntimeException("Could not store the file, Error " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists()){
                return resource;
            }else{
                throw new RuntimeException("Could not read the file");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Error " + e.getMessage());
        }
    }
}
