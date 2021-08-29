package com.hacorp.shop.core.exception;

public class FileStorageException extends BaseException{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
