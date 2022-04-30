package com.example.group_hanu_spring2022.controller;

import com.example.group_hanu_spring2022.constants.Constants;
import com.example.group_hanu_spring2022.exception.ErrorMessage;
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

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping(value = "/resources")
public class ResourceController {

    @Autowired
    private S3Service s3Service;

    /**
     * @api {get} /resources/:id Get Resource
     * @apiName Get Resource
     * @apiGroup User
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 Resource not found.
     */
    @RequestMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getResource(@PathVariable String id) {
        byte[] data = s3Service.getFile(id);
        if(data == null){
            return ResponseEntity.status(400).body(new ErrorMessage("Resource not found!"));
        }

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

    /**
     * @api {get} /resources Upload Resource
     * @apiName UploadResource
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 [Can be error token or expire token, Password must contain at least 6 characters].
     */
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

    /**
     * @api {get} /accounts/:id Get Account By ID
     * @apiName DeleteUser
     * @apiGroup User
     * @apiPermission admin
     * @apiParam {String} access_token User access_token.
     * @apiSuccess (Success 200) 200 No Content.
     * @apiError 400 [Can be error token or expire token, Password must contain at least 6 characters].
     */
    @DeleteMapping("/delete/{fileName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFile(HttpServletRequest httpServletRequest, @PathVariable String fileName) {

        boolean isAdmin = httpServletRequest.isUserInRole("ADMIN");

        if(!isAdmin){
            return ResponseEntity.status(401).body(new ErrorMessage(Constants.PERMISSION_DENIED));
        }

        return ResponseEntity.ok(s3Service.deleteFile(fileName));
    }

}
