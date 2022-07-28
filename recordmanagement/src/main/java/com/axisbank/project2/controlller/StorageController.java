package com.axisbank.project2.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.axisbank.project2.service.amazons3.StorageService;

@RestController
@RequestMapping("/files3")
@CrossOrigin("http://localhost:3000")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping("/uploads3")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }
    
    @PostMapping("/encrypt/upload")
    public ResponseEntity<String> uploadEncryptFile(@RequestParam(value = "file") MultipartFile file){
    	return new ResponseEntity<>(service.upoadEncryptedFile(file),HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
    
    @GetMapping("/decrypt/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadDecryptFile(@PathVariable String fileName)
    {
    	byte[] data=service.downloadDecryptFile(fileName);
    	ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }
    
    
    @GetMapping("/list")
    public List<String> getAllfile(){
    	return service.listAllFiles();
    }
}