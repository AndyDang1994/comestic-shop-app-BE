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
import com.hacorp.shop.core.model.PromotionInfor;
import com.hacorp.shop.core.utils.DateUtils;
import com.hacorp.shop.repository.dao.PromotionMasDAO;
import com.hacorp.shop.repository.entity.PromotionMas;
import com.hacorp.shop.repository.service.PromotionMasRepositoryService;


@Service("promotionMasRepositoryService")
public class PromotionMasRepositoryServiceImpl  extends AbstractServiceClass implements PromotionMasRepositoryService {

	@Autowired
	private PromotionMasDAO objectDAO;
	
	@Override
	public List<PromotionMas> getPromotionMasList(Map<String, Object> inputParams) throws BaseException {
		String sql = "select * promotion_mas "
					+"where :promoteName is null or :promoteName = promote_name "
					+ "and :promoteType is null or  :promoteType = promote_type "
					+ "and :ledgerStatus is null or :ledgerStatus = ledger_status "
					+ "and start_apl_time between :startDt and :endDt";
		Query query = entityManager.createNativeQuery(sql, PromotionMas.class);
		query.setParameter("promoteName", inputParams.get(APIConstant.PROMOTE_NAME_KEY).toString());
		query.setParameter("promoteType", inputParams.get(APIConstant.PROMOTE_TYPE_KEY).toString());
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		query.setParameter("startDt", DateUtils.convertDate(inputParams.get(APIConstant.START_DT_KEY).toString(), DateUtils.DATEFORMAT) );
		query.setParameter("endDt", DateUtils.convertDate(inputParams.get(APIConstant.END_DT_KEY).toString(), DateUtils.DATEFORMAT));
		return query.getResultList();
	}
	
	@Override
	public List<PromotionInfor> getPromotionInfors(Map<String, Object> inputParams) throws BaseException {
		String sql = "select new com.hacorp.shop.core.model.PromotionInfor(mas.id, mas.promoteName, mas.ledgerStatus,mas.startAplTime,"
					+"mas.endAplTime,mas.promoteType) "
					+"from PromotionMas mas"
					+"where :promoteName is null or :promoteName = mas.promoteName "
					+ "and :promoteType is null or  :promoteType = mas.promoteType "
					+ "and :ledgerStatus is null or :ledgerStatus = mas.ledgerStatus "
					+ "and mas.startAplTime between :startDt and :endDt";
		Query query = entityManager.createQuery(sql);
		query.setParameter("promoteName", inputParams.get(APIConstant.PROMOTE_NAME_KEY).toString());
		query.setParameter("promoteType", inputParams.get(APIConstant.PROMOTE_TYPE_KEY).toString());
		query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
		query.setParameter("startDt", DateUtils.convertDate(inputParams.get(APIConstant.START_DT_KEY).toString(), DateUtils.DATEFORMAT) );
		query.setParameter("endDt", DateUtils.convertDate(inputParams.get(APIConstant.END_DT_KEY).toString(), DateUtils.DATEFORMAT));
		return query.getResultList();
	}

	@Override
	public Long countPromotionMasList(Map<String, Object> inputParams) throws BaseException {
		try {
			String sql = "select count(*) from promotion_mas "
						+"where :promoteName is null or :promoteName = promote_name "
						+ "and :promoteType is null or  :promoteType = promote_type "
						+ "and :ledgerStatus is null or :ledgerStatus = ledger_status "
						+ "and start_apl_time between :startDt and :endDt";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("promoteName", inputParams.get(APIConstant.PROMOTE_NAME_KEY).toString());
			query.setParameter("promoteType", inputParams.get(APIConstant.PROMOTE_TYPE_KEY).toString());
			query.setParameter("ledgerStatus", inputParams.get(APIConstant.LEDGER_STATUS_KEY).toString());
			query.setParameter("startDt", DateUtils.convertDate(inputParams.get(APIConstant.START_DT_KEY).toString(), DateUtils.DATEFORMAT) );
			query.setParameter("endDt", DateUtils.convertDate(inputParams.get(APIConstant.END_DT_KEY).toString(), DateUtils.DATEFORMAT));
			return (Long) query.getSingleResult();
		} catch (Exception e) {
			throw new ServiceRuntimeException(env.getProperty("MSG_002"));
		}
	}

	@Override
	public PromotionMas getOne(Map<String, Object> inputParams) throws BaseException {
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
			PromotionMas item = (PromotionMas) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			List<PromotionMas> item =  (List<PromotionMas>) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			PromotionMas item = (PromotionMas) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			List<PromotionMas> item =  (List<PromotionMas>) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			PromotionMas item = (PromotionMas) inputParams.get(APIConstant.DOCUMENT_KEY);
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
			List<PromotionMas> item =  (List<PromotionMas>) inputParams.get(APIConstant.DOCUMENT_KEY);
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
