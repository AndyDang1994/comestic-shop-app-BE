package com.hacorp.shop.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.FileNotFoundException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.repository.entity.Product;


@RestController
public class ServiceController extends BaseController{

	@RequestMapping(value = "/api/image", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(
			@RequestParam(required = true, defaultValue = "") String fileName, Locale locale,
			HttpServletRequest request) throws BaseException{
		Map<String,Object> inputParams = new HashedMap<>();
		inputParams.put(APIConstant.FILENAME_KEY, fileName);
        // Load file as Resource
        Resource resource = getProcessManagerService().getShopApiService().getProductImage(inputParams);

        // Try to determine file's content type
        String contentType = null;
        try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			throw new FileNotFoundException("File not found " + fileName);
		}

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	@RequestMapping(value = "/api/products",produces = "application/json;charset=utf-8", method = RequestMethod.GET )
	public ResponseEntity<Object> getProductList(
			@RequestParam(required = false, defaultValue = "") String _start,
			@RequestParam(required = false, defaultValue = "") String _number,
			@RequestParam(required = false, defaultValue = "") String _prodName,
			@RequestParam(required = false, defaultValue = "") String _subCate) throws BaseException {
		
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.START_KEY,_start);
		input.put(APIConstant.NUMBER_KEY,_number);
		input.put(APIConstant.PROD_NAME_KEY,_prodName);
		input.put(APIConstant.SUB_CATE_KEY,_subCate);
		
		List<ProductInfor> list = getRepositoryManagerService().getProductRepositoryService().getAllList(input);
		Long count = getRepositoryManagerService().getProductRepositoryService().getCountProduct(input);
		
		return triggerSuccessOutPut(list, count);
		
	}
	@RequestMapping(value = "/api/products",produces = "application/json;charset=utf-8", method = RequestMethod.PATCH )
	public ResponseEntity<Object> editProduc(
			@RequestBody String document, Locale locale) throws BaseException {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.DOCUMENT_KEY,document);
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		
		boolean item = getProcessManagerService().getShopApiService().editPproduct(input);
		return triggerSuccessOutPut(item, JsonObject.class, null);
	}
	
	@RequestMapping(value = "/api/products",produces = "application/json;charset=utf-8", method = RequestMethod.POST )
	public ResponseEntity<Object> createProduct(
			@RequestBody String document, Locale locale) throws BaseException {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.DOCUMENT_KEY,document);
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		
		Product item = getProcessManagerService().getShopApiService().createProduct(input);
		return triggerSuccessOutPut(item, JsonObject.class, null);
	}
	@RequestMapping(value = "/api/productsdel",produces = "application/json;charset=utf-8", method = RequestMethod.POST )
	public ResponseEntity<Object> deleteProduct(
			@RequestBody(required = true) String document, Locale locale) throws BaseException {
		httpServletRequest.setAttribute(APIConstant.HTTP_REQUEST_BODY_STR, document);
		Map<String,Object> input = new HashedMap<>();
		
		input.put(APIConstant.DOCUMENT_KEY,document);
		input.put(APIConstant.USERNAME_KEY, httpServletRequest.getAttribute(APIConstant.USERNAME_KEY).toString());
		
		boolean item = getProcessManagerService().getShopApiService().deleteProduct(input);
		if(!item) {
			throw new ServiceRuntimeException(env.getProperty("MSG_004"));
		}
		return triggerSuccessOutPut(item, JsonObject.class, null);
	}
}
