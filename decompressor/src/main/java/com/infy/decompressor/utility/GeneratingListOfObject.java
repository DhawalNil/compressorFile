package com.infy.decompressor.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infy.decompressor.exception.DecompressorException;

@Component
public class GeneratingListOfObject {
	
	@Autowired
	OutputFile outputFile ;
	
	public List<Object> getListOfObject(String decompressString) throws DecompressorException {
		
		decompressString = decompressString.trim() ;
		
		decompressString = decompressString.substring(1, decompressString.length() - 1 ) ;
		
		String[] objectString = decompressString.split("\\}, \\{") ;
		
		List<Object> objectList = new ArrayList<>() ;
		
		for(String object : objectString) {
	
			object = object.replaceAll("\\{", "").replaceAll("}", "") ;
			String[] keyValuePair = object.split(", ") ;
			
			Map<String , String> objectMap = new HashMap<>() ;
			for(String pair : keyValuePair) {
				String [] keyValue = pair.split("=") ;
				
				String key = keyValue[0].trim();
				String value = keyValue[1].trim();
				objectMap.put(key, value) ;
				
			}
			objectList.add(objectMap) ;
		}
		
		try {
			outputFile.objectToFile(objectList);			
		}
		catch(Exception e)
		{
			throw new DecompressorException(e.getMessage()) ;
		}
		return objectList ;
	}
}
