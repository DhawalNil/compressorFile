package com.infy.compressor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.compressor.service.CompressServiceImpl;

@SpringBootTest
class CompressorApplicationTests {

	@Autowired
	CompressServiceImpl compressService ;
	@Autowired
	Environment environment;
	
	public static RestTemplate restTemplate = new RestTemplate();
	@Test
	void contextLoads() {
	}
	
	@Test
	void testData() {
		
		List<Object> listObject = new ArrayList<>() ;

		 List<Object> listResult = new ArrayList<>() ;
		try {
			String path = environment.getProperty("inputFile") ;
			String json = new String(Files.readAllBytes(Paths.get(path))) ;
			ObjectMapper objectMapper = new ObjectMapper() ;
			listObject = objectMapper.readValue(json, new TypeReference<List<Object>>() {});
			
			String url = environment.getProperty("decompressorUrl") ;
			byte[] checkByte = compressService.compress(listObject) ;
			
			
			@SuppressWarnings("unchecked")
			List<Object>resultList = restTemplate.postForObject(url, checkByte, List.class) ;
			listResult = resultList ;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()) ;
			
		}
		
			Assertions.assertEquals(listResult , listObject);
	}
	

}
