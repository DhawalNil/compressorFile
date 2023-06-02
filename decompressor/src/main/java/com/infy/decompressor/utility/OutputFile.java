package com.infy.decompressor.utility;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.decompressor.exception.DecompressorException;

@Component
public class OutputFile {

	@Autowired
	Environment environment ;
	
	public static ObjectMapper objectMapper = new ObjectMapper() ;
	
	public void objectToFile(List<Object> listObject) throws DecompressorException {
		
		String path = environment.getProperty("outputFile") ;
		try {			
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path) , listObject) ;
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
	}
	
	public void objToFile(Object object) throws DecompressorException {
		
		String path = environment.getProperty("outputFile") ;
		try {			
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path) ,object) ;
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
	}
}
