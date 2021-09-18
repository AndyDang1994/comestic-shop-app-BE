/**
 * 
 */
package com.hacorp.shop.core.constant;

/**
 * @author shds01
 *
 */

public class APIConstant {

	
	/** User Account Status **/
	public static final String USER_ACTIVE = "ACT";
	public static final String USER_FROZEN = "FRO";
	
	/** HTTP Description **/
	public static final String HTTP_REQUEST_BODY_STR = "HttpRequestBody";
	public static final String METHOD_NOT_ALLOWED = "Method is not allowed";
	public static final String UNSUPPORTED_MEDIA_TYPE = "Media type is unsupported";
	public static final String NOT_ACCEPTABLE = "Not Acceptable";

	public static final String GET_METHOD_STR = "GET";
	public static final String POST_METHOD_STR = "POST";
	public static final String PATCH_METHOD_STR = "PATCH";
	public static final String DELETE_METHOD_STR = "DELETE";
	public static final String OPTIONS_METHOD_STR = "OPTIONS";
	public static final String ANONYMOUS_USER = "Anonymous";

	public static final String TEXT_HTML_CHARSET_UTF8_CHARSET_TYPE = "text/html;charset=UTF-8";
	public static final String UTF_8_CHARSET_TYPE = "UTF-8";
	public static final String CONTENT_TYPE_REQUEST_HEADER = "Content-Type";

	public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
	public static final String CONTENT_TYPE_IMAGE_JPG = "image/jpeg";

	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML = "application/xml";
	public static final String CONTEXT_FILTER_PATH = "shinhan/service/";
	
	public static final String REQUEST_URI_STR = "requestURI";
	
	/** Exception Constant **/
	public static final String SERVICE_RUNTIME_EXCEPTION_ERROR = "Some error is happened";
	public static final String THE_TOKEN_IS_INVALID_ERROR = "The Token is invalid";
	public static final String THE_TOKEN_IS_INCORRECT_FORMAT_ERROR = "The Token is incorrect format";
	public static final String THE_TOKEN_IS_BLANK_ERROR = "The Token is blank";
	public static final String THE_TOKEN_UNPERMISSION_ERROR = "The Token can not execute this operation";
	public static final String NO_RECORD_FOUND_KEY = "No record found";
	public static final String THE_TOKEN_IS_EXPIRED_ERROR = "The Token is expired";
	public static final String THE_DATA_INCORRECT_FORMAT_ERROR = "The Data is incorrect format";
	public static final String EXPIRY_DATE = "expiryDate";
	public static final String TRACKING_LOG_INSTANCE = "trackingLogInstance";
	/** SECURE Constant **/
	public static final String SYSADMIN_STR = "SYSADMIN";
	public static final String ACCESS_TOKEN_KEY = "access-token";
	//public static final String ACCESS_TOKEN_KEY = "Authorization";
	
	public static final String PUBLIC_KEY = "public_key";
	public static final String AES_KEY = "AES_key";
	public static final String EXECUTION_TIME_KEY = "executionTime";
	public static final String TOKEN_EXPIRY_MINUTES = "Token_expiry_minutes";
	
	/** Other Constant **/
	public static final String YES_KEY = "Y";
	public static final String NO_KEY = "N";
	public static final String NULL_KEY = "NULL";
	public static final String BLANK_VALUE = "";
	public static final String EMPTY_KEY = "EMPTY";
	public static final String SUCCESS_KEY = "SUCCESS";
	public static final String SUCCESS_RESPONSE_KEY = "200";
	public static final String ERROR_100_RESPONSE_KEY = "100";
	public static final String ERROR_KEY = "ERROR";
	public static final String ROLE_USER_PUBLIC = "USER_PUBLIC";
	public static final String LEDGER_STATUS_NORM = "NORM";
	public static final String LEDGER_STATUS_DELE = "DELE";
	public static final String DATA = "data";
	public static final String COUNT = "count";
	public static final String PATH_IMAGES_STORAGE = "PATH_IMAGES_STORAGE";
	
	
	
	/** parameters key **/
	public static final String USERNAME_KEY = "userName";
	public static final String STATUS_KEY = "status";
	public static final String LEDGER_STATUS_KEY = "ledgerStatus";
	public static final String DOCUMENT_KEY = "document";
	public static final String ROLE_KEY = "role";
	public static final String USER_KEY = "user";
	public static final String OPTBY_KEY = "optBy";
	public static final String MSGCODE_KEY = "MSGCODE";
	public static final String RESULT_KEY = "result";
	public static final String ID_KEY = "id";
	public static final String FILENAME_KEY = "fileName";
	public static final String CATE_CODE_KEY = "categoryCode";
	public static final String START_KEY = "_start";
	public static final String NUMBER_KEY = "_number";
	public static final String PROD_NAME_KEY = "_prodName";
	public static final String SUB_CATE_KEY = "_subCate";
	
	
	/** System Key **/
	public static final String RESULT_MSG = "RESULT_MSG";
}
