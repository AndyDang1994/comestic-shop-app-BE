package com.hacorp.shop.repository.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.ProductInfor;
import com.hacorp.shop.core.utils.DateUtils;
import com.hacorp.shop.core.utils.DtoConvert;
import com.hacorp.shop.repository.dao.ProductDAO;
import com.hacorp.shop.repository.entity.Product;
import com.hacorp.shop.repository.service.ProductRepositoryService;

@Service("productRepositoryService")
public class ProductRepositoryServiceImpl extends AbstractServiceClass implements ProductRepositoryService {

	private ProductDAO objectEntity;

	@Autowired
	public ProductRepositoryServiceImpl(ProductDAO objectEntity) {
		super();
		this.objectEntity = objectEntity;
	}

	@Override
	public List<Product> getListBySubCategoryCode(Map<String, Object> inputParams) throws BaseException {
		long id = inputParams.get(APIConstant.ID_KEY) == null ? 0
				: Long.parseLong(inputParams.get(APIConstant.ID_KEY).toString());
		String sql = "select pr.* from Product pr " + "where pr.subCategory.ledgerStatus = :ledgerStatus "
				+ "and pr.subCategory.subCategoryCode = :subCategoryCode " + "and (:id = 0 or pr.id = :id)";
		Query query = entityManager.createQuery(sql, Product.class);
		query.setParameter("ledgerStatus", APIConstant.LEDGER_STATUS_NORM);
		query.setParameter("subCategoryCode", inputParams.get(APIConstant.CATE_CODE_KEY).toString());
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ProductInfor> getAllList(Map<String, Object> inputParams) throws BaseException {

		int pageNumber = inputParams.get(APIConstant.START_KEY) == null
				|| StringUtils.isBlank(inputParams.get(APIConstant.START_KEY) + "")
						? Integer.valueOf(env.getProperty(APIConstant.START_KEY))
						: Integer.valueOf(inputParams.get(APIConstant.START_KEY).toString());
		int pageSize = inputParams.get(APIConstant.NUMBER_KEY) == null
				|| StringUtils.isBlank(inputParams.get(APIConstant.NUMBER_KEY) + "")
						? Integer.valueOf(env.getProperty(APIConstant.NUMBER_KEY))
						: Integer.valueOf(inputParams.get(APIConstant.NUMBER_KEY).toString());

//		String sql = "select new com.hacorp.shop.core.model.ProductInfor(pr.id as id, pr.name as name, pr.quantity as quantity,"
//					+ "pr.thumnail as thumnail, pr.description as description, pr.price as price, pr.vote as vote, pr.commentID as commentID) "
//					+ "from Product pr "
//					+ "where  pr.ledgerStatus = :ledgerStatus "
//					+ "and (:name is null or  pr.name like '%' || :name || '%' ) ";
		// + "and ( :subCategoryCode is null or pr.subCategory.subCategoryCode =
		// :subCategoryCode )";
		String sql = "select * from Product " + "where ledger_status = :ledgerStatus "
				+ "and (:name is null or name like '%' || :name || '%' ) ";

		Query query = entityManager.createNativeQuery(sql, Product.class);
		query.setParameter("ledgerStatus", APIConstant.LEDGER_STATUS_NORM);
		query.setParameter("name", inputParams.get(APIConstant.PROD_NAME_KEY).toString());
		// query.setParameter("subCategoryCode",
		// inputParams.get(APIConstant.SUB_CATE_KEY).toString());

		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Product> prod = query.getResultList();

		List<ProductInfor> rs = DtoConvert.getListProdInfor(query.getResultList());

		return rs;

	}

	@Override
	public Long getCountProduct(Map<String, Object> inputParams) throws BaseException {

		String sql = "select count(*) " + "from Product pr " + "where  pr.ledgerStatus = :ledgerStatus "
				+ "and (:name is null or  pr.name like '%' || :name || '%' ) ";
		// + "and ( :subCategoryCode is null or pr.subCategory.subCategoryCode =
		// :subCategoryCode )";
		Query query = entityManager.createQuery(sql);
		query.setParameter("ledgerStatus", APIConstant.LEDGER_STATUS_NORM);
		query.setParameter("name", inputParams.get(APIConstant.PROD_NAME_KEY).toString());
		// query.setParameter("subCategoryCode",
		// inputParams.get(APIConstant.SUB_CATE_KEY).toString());

		Long rs = (Long) query.getSingleResult();
		return rs;
	}

	@Override
	public Product getProduct(Map<String, Object> inputParams) throws BaseException {
		Long id = (Long) inputParams.get(APIConstant.ID_KEY);
		return objectEntity.findById(id).orElse(null);
	}

	@Override
	public Product save(Map<String, Object> inputParams) throws BaseException {
		try {
			Product item = (Product) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				return objectEntity.save(item);
			} else {
				throw new ServiceRuntimeException(env.getProperty("MSG_001"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean saveAll(Map<String, Object> inputParams) throws BaseException {
		try {
			List<Product> item = (List<Product>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				objectEntity.saveAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean update(Map<String, Object> inputParams) throws BaseException {
		try {
			Product item = (Product) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				objectEntity.save(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException {
		try {
			List<Product> item = (List<Product>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				objectEntity.saveAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean delete(Map<String, Object> inputParams) throws BaseException {
		try {
			Product item = (Product) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				objectEntity.delete(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean deleteAll(Map<String, Object> inputParams) throws BaseException {
		try {
			List<Product> item = (List<Product>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if (item != null) {
				objectEntity.deleteAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public List<ProductInfor> getListByParams(Map<String, Object> inputParams) throws BaseException {
		int pageNumber = inputParams.get(APIConstant.START_KEY) == null
				|| StringUtils.isBlank(inputParams.get(APIConstant.START_KEY) + "")
						? Integer.valueOf(env.getProperty(APIConstant.START_KEY))
						: Integer.valueOf(inputParams.get(APIConstant.START_KEY).toString());
		int pageSize = inputParams.get(APIConstant.NUMBER_KEY) == null
				|| StringUtils.isBlank(inputParams.get(APIConstant.NUMBER_KEY) + "")
						? Integer.valueOf(env.getProperty(APIConstant.NUMBER_KEY))
						: Integer.valueOf(inputParams.get(APIConstant.NUMBER_KEY).toString());
		
		LocalDateTime stDt = LocalDate.parse( DateUtils.getSystemDateStr(DateUtils.yyyy_MM_dd) ).atTime(0, 0);
		
		String sql = "with tmp as( "
				+ "select pmf.product_id  as product_id, pm.end_apl_time as end_apl_time, pm.start_apl_time as start_apl_time, "
				+ "pm.promote_name as promote_name , pm.promote_type as promote_type, pm.volume as volume "
				+ "from promotion_mas pm , promote_inf pmf "
				+ "where pm.id = pmf.promote_id " 
				+ "and :startDt between pm.start_apl_time and pm.end_apl_time "
				+ "and pm.ledger_status  = 'NORM' " 
				+ "and pmf.ledger_status  = 'NORM' " 
				+ ") " 
				+ "select sub.* from ( "
				+ "select p.id as id, p.name as name ,p.quantity as quantity,p.thumbnail as thumbnail, p.description as description, p.price as price, p.vote as vote, "
				+ "p.comment_id as comment_id, tmp.promote_name as promote_name, tmp.promote_type as promote_type, tmp.volume as volume, "
				+ "row_number() over(partition by p.id order by case when tmp.promote_type = 'PER' then p.price*tmp.volume/100 else tmp.volume end) as cnt "
				+ "from product p left join tmp " 
				+ "on p.id  = tmp.product_id " 
				+ "where "
				+ "length(:ledgerStatus) = 0 or p.ledger_status  = :ledgerStatus "
				+ "and length(:subCateCode) = 0 or :subCateCode = p.sub_category_code "
				+ "and length(:name) = 0 or p.name like '%'|| :name || '%')sub " 
				+ "where sub.cnt = 1 ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		query.setParameter("subCateCode", inputParams.get(APIConstant.SUB_CATE_KEY).toString());
		query.setParameter("name", inputParams.get(APIConstant.PROD_NAME_KEY).toString());
		query.setParameter("startDt",stDt);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return DtoConvert.getProdInfors(query.getResultList());
	}
	
	@Override
	public BigInteger countProductByParams(Map<String, Object> inputParams) throws BaseException {

		String sql = "select count(*) " 
				+ "from product p " 
				+ "where  "
				+ "length(:ledgerStatus) = 0 or p.ledger_status  = :ledgerStatus "
				+ "and length(:subCateCode) = 0 or :subCateCode = p.sub_category_code "
				+ "and length(:name) = 0 or p.name like '%'|| :name || '%' ";
		// + "and ( :subCategoryCode is null or pr.subCategory.subCategoryCode =
		// :subCategoryCode )";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		query.setParameter("subCateCode", inputParams.get(APIConstant.SUB_CATE_KEY).toString());
		query.setParameter("name", inputParams.get(APIConstant.PROD_NAME_KEY).toString());

		BigInteger rs = (BigInteger) query.getSingleResult();
		return rs;
	}

}
