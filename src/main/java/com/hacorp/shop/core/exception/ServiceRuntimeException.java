/**
 * 
 */
package com.hacorp.shop.core.exception;

/**
 * @author shds01
 *
 */
public class ServiceRuntimeException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6891612682262201057L;

	/**
	 * 
	 */
	public ServiceRuntimeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ServiceRuntimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceRuntimeException(String message, String errorDescription) {
		super(message, errorDescription);
	}

	/**
	 * @param message
	 */
	public ServiceRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceRuntimeException(Throwable cause) {
		super(cause);
	}

}
