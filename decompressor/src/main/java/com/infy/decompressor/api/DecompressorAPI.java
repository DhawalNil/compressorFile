package com.infy.decompressor.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.infy.decompressor.exception.DecompressorException;
import com.infy.decompressor.service.DecompressorService;

@RestController
@RequestMapping(value = "/decompressor")
public class DecompressorAPI {
	
	@Autowired
	DecompressorService<Object> decompressorService ;
	@Autowired
	Environment env ;
	
	@PostMapping(consumes= {"multipart/form-data"})
	public ResponseEntity<List<Object>> decompressor(@RequestParam("file") MultipartFile uploadFile) throws DecompressorException{
		
		try {
		return new ResponseEntity<>(decompressorService.decompress(uploadFile.getBytes()) , HttpStatus.OK) ;
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
		
	}
	
	@PostMapping(value = "/object" , consumes = {"multipart/form-data"})
	public ResponseEntity<Object> decompressorObject(@RequestParam("file") MultipartFile uploadFile) throws DecompressorException{
		try {
			Object obj = decompressorService.decompressObject(uploadFile.getBytes()) ;		
			return new ResponseEntity<>(obj , HttpStatus.OK) ;
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
	}
}
