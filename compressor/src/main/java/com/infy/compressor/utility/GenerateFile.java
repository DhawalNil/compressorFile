package com.infy.compressor.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.compressor.exception.CompressorException;

@Component
public class GenerateFile {

	@Autowired
	Environment environment ;
	
	public static ObjectMapper objectMapper = new ObjectMapper() ;
	
	public  void bytetoFile(byte[] jsonData ) throws CompressorException{
		
		String FILEPATH = environment.getProperty("compressedFile") ;
		
		try{			
			Path path = Paths.get(FILEPATH) ;
			Files.write(path, jsonData) ;
			
		}catch(Exception e )
		{
			throw new CompressorException(e.getMessage()) ;
		}
	}
	
	public void objectToFile(List<Object> listObject) throws CompressorException {
		
		String path = environment.getProperty("inputFile") ;
		try {			
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path) , listObject) ;
		}
		catch(Exception e)
		{
			throw new CompressorException(e.getMessage()) ;
		}
	}
}
