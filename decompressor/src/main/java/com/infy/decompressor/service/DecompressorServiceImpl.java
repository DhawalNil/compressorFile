package com.infy.decompressor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerial.snappy.Snappy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.decompressor.exception.DecompressorException;
import com.infy.decompressor.utility.GeneratingListOfObject;
import com.infy.decompressor.utility.OutputFile;

@Service
public class DecompressorServiceImpl implements DecompressorService<Object> {
	
	@Autowired
	GeneratingListOfObject listOfObject ;
	@Autowired
	OutputFile outputFile ;
	
	public List<Object> decompress(byte[] compressByte) throws DecompressorException{
		try {
			
			ObjectMapper objectMapper = new ObjectMapper() ;			
			byte[] decompressByte = Snappy.uncompress(compressByte) ;			
			String decompressString = new String(decompressByte) ;
			List<Object> obj = objectMapper.readValue(decompressString, new TypeReference<List<Object>>() {});
			outputFile.objectToFile(obj);
			return obj ;						
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
	}
	
	public Object decompressObject(byte[] compressByte) throws DecompressorException{
		try {
			
			ObjectMapper objectMapper = new ObjectMapper() ;			
			byte[] decompressByte = Snappy.uncompress(compressByte) ;			
			String decompressString = new String(decompressByte) ;
			System.out.println(decompressString) ;
			Object obj= objectMapper.readValue(decompressString, new TypeReference<Object>() {});
			outputFile.objToFile(obj);
			return obj ;
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
	}
}
