package com.hacorp.shop.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.core.model.UserInf;
import com.hacorp.shop.core.utils.CommonUtil;
import com.hacorp.shop.core.utils.DtoConvert;
import com.hacorp.shop.repository.entity.Product;
import com.hacorp.shop.repository.entity.Role;
import com.hacorp.shop.repository.entity.SubCategory;
import com.hacorp.shop.repository.entity.User;

@Service("validationManagementService")
public class ValidationManagementService extends AbstractServiceClass {

	public Map<String, Object> validateRegisterUser(Map<String, Object> inputParams) throws BaseException{
		
		Map<String, Object> item = new HashedMap<>();
		
		
		UserInf userInf = (UserInf) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY), UserInf.class);
		inputParams.put(APIConstant.ROLE_KEY, userInf.getUserRoles());
		User user = DtoConvert.getUserFromUserInf(userInf,inputParams.get(APIConstant.OPTBY_KEY).toString());
		List<Role> roles = getRepositoryManagerService().getRoleRepositoryService().getListRoleByRoleCode(inputParams);
		if(userInf.getUserRoles().size() != roles.size()) {
			item.put(APIConstant.RESULT_KEY, false);
			item.put(APIConstant.MSGCODE_KEY, "MSG_007");
			return item;
		}
		if(isUserNameExisted(user.getUserName())) {
			item.put(APIConstant.RESULT_KEY, false);
			item.put(APIConstant.MSGCODE_KEY, "MSG_006");
			return item;
		}
		user.setUserRoles(DtoConvert.setListUserRole(user, roles, inputParams.get(APIConstant.OPTBY_KEY).toString()));
		user.setLedgerStatus(APIConstant.LEDGER_STATUS_NORM);
		user.setStatus(APIConstant.USER_ACTIVE);
		user.setPassword(encodePassword(userInf.getPassword()));
		item.put(APIConstant.DOCUMENT_KEY, user);
		item.put(APIConstant.RESULT_KEY, true);
		return item;
		
	}
	
	public Map<String, Object> validateEditProduct(Map<String, Object> inputParams) throws BaseException{
		
		Map<String, Object> item = new HashedMap<>();
		SubCategory subCate = null;
		
		ProductInfor prodInf = (ProductInfor) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), ProductInfor.class );
		Map<String, Object> inpt = new HashedMap<>();
		inpt.put(APIConstant.ID_KEY, prodInf.getId());
		Product prod = getRepositoryManagerService().getProductRepositoryService().getProduct(inpt);
		if(prod == null) {
			item.put(APIConstant.RESULT_MSG, "Input information is not valid Category : " + prodInf.getSubCateCode());
			item.put(APIConstant.RESULT_KEY, false);
			return item;
		}
		
		if(!StringUtils.isBlank(prodInf.getSubCateCode())) {
			inpt.put(APIConstant.ID_KEY, prodInf.getSubCateCode());
			subCate = getRepositoryManagerService().getSubCategoryRepositoryService().getOne(inpt);
			if(subCate == null) {
				item.put(APIConstant.RESULT_MSG, "Input information is not valid Category : " + prodInf.getSubCateCode());
				item.put(APIConstant.RESULT_KEY, false);
				return item;
			}
		}
		DtoConvert.mapEditProductInf(prodInf, prod, subCate, inputParams.get(APIConstant.USERNAME_KEY).toString());
		item.put(APIConstant.DOCUMENT_KEY, prod);
		item.put(APIConstant.RESULT_KEY, true);
		return item;
	}
	
	public Map<String, Object> validateCreateProduct(Map<String, Object> inputParams) throws BaseException{
		
		Map<String, Object> item = new HashedMap<>();
		SubCategory subCate = null;
		Product prod = new Product();
		
		ProductInfor prodInf = (ProductInfor) CommonUtil.toPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), ProductInfor.class );
		Map<String, Object> inpt = new HashedMap<>();
		inpt.put(APIConstant.ID_KEY, prodInf.getSubCateCode());
		subCate = getRepositoryManagerService().getSubCategoryRepositoryService().getOne(inpt);
		if(subCate == null) {
			item.put(APIConstant.RESULT_MSG, "Input information is not valid Category : " + prodInf.getSubCateCode());
			item.put(APIConstant.RESULT_KEY, false);
			return item;
		}
		DtoConvert.mapCreateProduct(prodInf, prod, subCate, inputParams.get(APIConstant.USERNAME_KEY).toString());
		item.put(APIConstant.RESULT_KEY, true);
		item.put(APIConstant.DOCUMENT_KEY, prod);
		return item;
	}
	
	public Map<String, Object> validateDeleteProduct(Map<String, Object> inputParams) throws BaseException{
		
		Map<String, Object> item = new HashedMap<>();
		List<Product> prods = new ArrayList<>();
		
		List<ProductInfor> prodInfs = CommonUtil.toListPojo(inputParams.get(APIConstant.DOCUMENT_KEY).toString(), ProductInfor.class );
		Map<String, Object> inpt = new HashedMap<>();
		for (ProductInfor productInfor : prodInfs) {
			inpt.put(APIConstant.ID_KEY, productInfor.getId());
			
			Product prod = getRepositoryManagerService().getProductRepositoryService().getProduct(inpt);
			if(prod == null) {
				item.put(APIConstant.RESULT_MSG,String.format(env.getProperty("MSG_122"), productInfor.getId() ));
				item.put(APIConstant.RESULT_KEY, false);
				return item;
			}
			prods.add(prod);
		}
		
		item.put(APIConstant.RESULT_KEY, true);
		item.put(APIConstant.DOCUMENT_KEY, prods);
		return item;
	}
	
}
