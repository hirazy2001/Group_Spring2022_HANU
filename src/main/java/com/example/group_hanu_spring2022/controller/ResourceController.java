package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/resources")
public class ResourceController {

    @Autowired
    private S3Service s3Service;

    @RequestMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getResource(@PathVariable String id) {
        byte[] data = s3Service.getFile(id);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + "name" + "\"")
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + id + "\"")
//                .body(resource);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadResource(
            @RequestParam(value = "file")
                    MultipartFile file
    ) {
        return ResponseEntity.ok(
                s3Service.uploadFile(file)
        );
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return ResponseEntity.ok(s3Service.deleteFile(fileName));
    }

}
