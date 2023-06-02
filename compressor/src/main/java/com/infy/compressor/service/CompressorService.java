package com.infy.compressor.service;

import java.util.List;

import com.infy.compressor.exception.CompressorException;

public interface CompressorService<T> {
	
	public byte[] compress(List<?> listObject) throws CompressorException ;
	public byte[] compress(T object) throws CompressorException ;
	
}
