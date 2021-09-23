package com.hacorp.shop.repository.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacorp.shop.common.AbstractServiceClass;
import com.hacorp.shop.core.constant.APIConstant;
import com.hacorp.shop.core.exception.BaseException;
import com.hacorp.shop.core.exception.ServiceRuntimeException;
import com.hacorp.shop.core.model.ProductPromoteInfor;
import com.hacorp.shop.repository.dao.PromotionInfDAO;
import com.hacorp.shop.repository.entity.PromotionInf;
import com.hacorp.shop.repository.service.PromotionInfRepositoryService;

@Service("promotionInfRepositoryService")
public class PromotionInfRepositoryServiceImpl extends AbstractServiceClass implements PromotionInfRepositoryService {

	@Autowired
	private PromotionInfDAO objectDAO;
	
	@Override
	public List<PromotionInf> getPromotionMasList(Map<String, Object> inputParams) throws BaseException {
		String productId = inputParams.get(APIConstant.PRODUCT_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PRODUCT_ID_KEY).toString();
		String promoteId = inputParams.get(APIConstant.PROMOTE_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PROMOTE_ID_KEY).toString();
		String sql = "select * from promote_inf "
					+"where :productId = 0 or :productId = product_id "
					+"and :promoteId = 0 or :promoteId = promote_id "
					+"and :ledgerStatus is null or :ledgerStatus = ledger_status ";
		Query query = entityManager.createNativeQuery(sql, PromotionInf.class);
		query.setParameter("productId", Long.valueOf(productId));
		query.setParameter("promoteId", Long.valueOf(promoteId));
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		return query.getResultList();
	}
	
	@Override
	public List<ProductPromoteInfor> getProductPromoteList(Map<String, Object> inputParams) throws BaseException {
		String productId = inputParams.get(APIConstant.PRODUCT_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PRODUCT_ID_KEY).toString();
		String promoteId = inputParams.get(APIConstant.PROMOTE_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PROMOTE_ID_KEY).toString();
		String sql = "select new com.hacorp.shop.core.model.ProductPromoteInfor(inf.id,pro.name,mas.promoteType,mas.volume,inf.ledgerStatus) "
					+"from PromotionInf inf, PromotionMas mas, Product pro"
					+"where :productId = 0 or :productId = inf.product.id "
					+"and :promoteId = 0 or :promoteId = inf.promotionMas.id "
					+"and inf.product.id = pro.id "
					+"and inf.promotionMas.id = mas.id "
					+"and :ledgerStatus is null or :ledgerStatus = inf.ledgerStatus ";
		Query query = entityManager.createQuery(sql);
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		query.setParameter("productId", Long.valueOf(productId));
		query.setParameter("promoteId", Long.valueOf(promoteId));
		return query.getResultList();
	}

	@Override
	public Long countPromotionMasList(Map<String, Object> inputParams) throws BaseException {
		try {
			String productId = inputParams.get(APIConstant.PRODUCT_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PRODUCT_ID_KEY).toString();
			String promoteId = inputParams.get(APIConstant.PROMOTE_ID_KEY) == null ? "0" : inputParams.get(APIConstant.PROMOTE_ID_KEY).toString();
			String sql = "select count(*) from promote_inf "
						+"where :productId = 0 or :productId = product_id "
						+"and :promoteId = 0 or :promoteId = promote_id "
						+"and :ledgerStatus is null or :ledgerStatus = ledger_status ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("productId", Long.valueOf(productId));
			query.setParameter("promoteId", Long.valueOf(promoteId));
			query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
			return (Long) query.getSingleResult();
		} catch (Exception e) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public PromotionInf getOne(Map<String, Object> inputParams) throws BaseException {
		Long id = Long.valueOf(inputParams.get(APIConstant.ID_KEY).toString());
		try {
			return objectDAO.findById(id).orElseGet(null);
		} catch (Exception e) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}
	@Override
	public boolean save(Map<String, Object> inputParams) throws BaseException {
		try {
			PromotionInf item = (PromotionInf) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.save(item);
				return true;
			}else {
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
			List<PromotionInf> item =  (List<PromotionInf>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.saveAll(item);
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
			PromotionInf item = (PromotionInf) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.save(item);
				return true;
			}else {
				throw new ServiceRuntimeException(env.getProperty("MSG_001"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public boolean updateAll(Map<String, Object> inputParams) throws BaseException {
		try {
			List<PromotionInf> item =  (List<PromotionInf>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.saveAll(item);
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
			PromotionInf item = (PromotionInf) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.delete(item);
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
			List<PromotionInf> item =  (List<PromotionInf>) inputParams.get(APIConstant.DOCUMENT_KEY);
			if(item != null) {
				objectDAO.deleteAll(item);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

}
