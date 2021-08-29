/**
 * 
 */
package com.hacorp.shop.core.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shds01
 *
 */
public class ResponseOutPut {

	private Object result;
	private String serverDate;
	private String exceptionMessage;
	private boolean status;
	private int statusCode;
	
	/**
	 * @param result
	 * @param exceptionMessage
	 * @param status
	 * @param statusCode
	 */
	public ResponseOutPut(Object result, String exceptionMessage, boolean status,
			int statusCode) {
		super();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateSystem = new Date();
		String dateText = df.format(dateSystem);
		this.serverDate = dateText;
		this.result = result;
		this.exceptionMessage = exceptionMessage;
		this.status = status;
		this.statusCode = statusCode;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	/**
	 * @return the serverDate
	 */
	public String getServerDate() {
		return serverDate;
	}
	/**
	 * @param serverDate the serverDate to set
	 */
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
