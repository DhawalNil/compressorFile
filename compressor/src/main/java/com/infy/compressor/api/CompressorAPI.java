package com.infy.compressor.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import com.infy.compressor.exception.CompressorException;
import com.infy.compressor.service.CompressorService;
import com.infy.compressor.utility.GenerateFile;

@RestController
@RequestMapping(value = "/compressor")
public class CompressorAPI {
	@Autowired
	Environment environment ;
	@Autowired
	CompressorService<Object> compressorService ;	
	@Autowired
	GenerateFile generateFile ;
	
	
	public static RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping
	public ResponseEntity<byte[]> compressor(@RequestBody List<Object> listObject ) throws CompressorException{

		byte[] compressedByte = compressorService.compress(listObject) ;
			HttpHeaders header = new HttpHeaders() ;
			ContentDisposition c=ContentDisposition.builder("inline").filename("compressor.txt").build();
			header.setContentDisposition(c);	
			return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(compressedByte);
				
	}
	
//	@GetMapping
//	public ResponseEntity<ByteArrayResource> compressor() throws CompressorException{
//		
//		String path = environment.getProperty("inputFile") ;
//		try {
//			
//			String json = new String(Files.readAllBytes(Paths.get(path))) ;	
//			 byte[] b = Snappy.compress(json.getBytes());
//			 
////			 
////			ObjectMapper objectMapper = new ObjectMapper() ;
////			List<Object> listObject = objectMapper.readValue(json, new TypeReference<List<Object>>() {});
////						
////			byte[] compressedByte = compressorService.compress(listObject) ;
//			 
//			 ByteArrayResource resource=new ByteArrayResource(b);
//				HttpHeaders header = new HttpHeaders() ;
//				ContentDisposition c=ContentDisposition.builder("inline").filename("compressor.txt").build();
//				header.setContentDisposition(c);
//					
//				return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
//			
//
//		}
//		catch(Exception e)
//		{
//			throw new CompressorException(e.getMessage()) ;
//		}	
//	}
	
	@PostMapping(value="/object")
	public ResponseEntity<ByteArrayResource> compressorForObject(@RequestBody Object obj) throws CompressorException{
				
		byte[] compressedByte = compressorService.compress(obj) ;	
		ByteArrayResource resource=new ByteArrayResource(compressedByte);
		HttpHeaders header = new HttpHeaders() ;
		ContentDisposition c=ContentDisposition.builder("inline").filename("compressor.txt").build();
		header.setContentDisposition(c);
			
		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	
	}
	
	
}
