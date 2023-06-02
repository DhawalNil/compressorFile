package com.infy.decompressor.service;

import java.util.List;

import com.infy.decompressor.exception.DecompressorException;

public interface DecompressorService<T> {
	public List<Object> decompress(byte[] compressByte) throws DecompressorException;
	public Object decompressObject(byte[] compressByte) throws DecompressorException;
}
