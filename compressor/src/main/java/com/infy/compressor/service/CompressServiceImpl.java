package com.infy.compressor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.xerial.snappy.Snappy;

import com.google.gson.Gson;
import com.infy.compressor.exception.CompressorException;

@Service
public class CompressServiceImpl implements CompressorService<Object> {
	
	public byte[] compress(List<?> objectList) throws CompressorException{
		
		try {		
			Gson gson = new Gson();
			String json = gson.toJson(objectList);
			return Snappy.compress(json.getBytes());	
		}
		catch(Exception e) {
			throw new CompressorException(e.getMessage()) ;
		}
	}
	
	public byte[] compress(Object object) throws CompressorException{
		
		try {		
			Gson gson = new Gson();
			String json = gson.toJson(object);
			return Snappy.compress(json.getBytes());	
		}
		catch(Exception e) {
			throw new CompressorException(e.getMessage()) ;
		}
	}

}
