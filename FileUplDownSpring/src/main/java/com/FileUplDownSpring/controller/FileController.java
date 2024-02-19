package com.FileUplDownSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FileUplDownSpring.model.FileEntity;
import com.FileUplDownSpring.repository.FileRepository;

@RestController
@RequestMapping("/api")
public class FileController {

	@Autowired
	private FileRepository fileRepo;
	
	
	//@RequestParam annotation enables spring to extract input data 
	//that may be passed as a query, form data, or any arbitrary custom data.
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			FileEntity fileEntity = new FileEntity();
			fileEntity.setFileName(file.getOriginalFilename());
			fileEntity.setContentType(file.getContentType());
			fileEntity.setData(file.getBytes());	
			fileRepo.save(fileEntity);
			String message = "file uploaded successfully";
			HttpStatus httpStatus =HttpStatus.CREATED;
			return new ResponseEntity<>(message,httpStatus);
		}catch (Exception e) {
			return  ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<?> downloadFile(@PathVariable Long id){
		FileEntity fileEntity = fileRepo.findById(id).orElse(null);
		if(fileEntity !=null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
			headers.setContentDisposition(ContentDisposition.attachment().filename(fileEntity.getFileName()).build());
			ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());
			
			return ResponseEntity.ok().headers(headers).body(resource);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
