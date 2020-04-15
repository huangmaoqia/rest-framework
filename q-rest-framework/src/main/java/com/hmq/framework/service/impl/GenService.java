package com.hmq.framework.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.alibaba.fastjson.JSON;
import com.hmq.framework.dao.IGenDao;
import com.hmq.framework.model.ICreateInfoModel;
import com.hmq.framework.model.IPkModel;
import com.hmq.framework.model.page.PageModel;
import com.hmq.framework.model.token.ITokenVO;
import com.hmq.framework.service.IGenService;
import com.hmq.framework.utils.query.DataRelation;
import com.hmq.framework.utils.query.DataRelationAction;
import com.hmq.framework.utils.query.ExpressionUtil;
import com.hmq.framework.utils.query.PageUtil;
import com.hmq.framework.utis.IdUtil;
import com.hmq.framework.utis.TokenUtil;

public class GenService<PO extends IPkModel<ID>, ID extends Serializable, Dao extends IGenDao<PO, ID>>
		implements IGenService<PO, ID> {

	@Autowired
	private Dao dao;

	protected Dao getDao() {
		return this.dao;
	}

	@Override
	public PO getById(ID id) {
		PO model = this.getDao().getOne(id);
		return model;
	}

	@Override
	public void deleteById(ID id) {
		this.getDao().delete(id);
	}

	@Override
	public PO saveOne(PO po) {
		handleData(po);
		return this.getDao().save(po);
	}

	@Override
	public List<PO> saveAll(List<PO> poList) {
		handleData(poList);
		return this.getDao().save(poList);
	}

	private void handleData(PO po) {
		ITokenVO<ID> tokenVO = TokenUtil.getTokenVO();

		if (po.getId() == null) {
			//新增,设置主键
			po.setId(IdUtil.genID());
			if (po instanceof ICreateInfoModel) {
				@SuppressWarnings("unchecked")
				ICreateInfoModel<ID> cpo = (ICreateInfoModel<ID>) po;
				//新增,设置创建人和创建时间和修改人和修改时间
				cpo.setCreateTime(new Date());
				cpo.setCreaterId(tokenVO.getUserid());
				cpo.setUpdateTime(new Date());
				cpo.setModifierId(tokenVO.getUserid());
			}
		} else {
			
			if (po instanceof ICreateInfoModel) {
				//更新,设置修改人和修改时间
				@SuppressWarnings("unchecked")
				ICreateInfoModel<ID> cpo = (ICreateInfoModel<ID>) po;
				cpo.setUpdateTime(new Date());
				cpo.setModifierId(tokenVO.getUserid());
			}
		}
	}

	private void handleData(List<PO> poList) {
		for (PO po : poList) {
			handleData(po);
		}
	}

	@Override
	public long countBySpec(Specification<PO> spec) {
		return this.getDao().count(spec);
	}

	@Override
	public List<PO> findBySpec(Specification<PO> spec) {
		return this.findBySpec(spec, null, null, null, null);
	}

	@Override
	public List<PO> findBySpec(Specification<PO> spec, String sortBy, String order) {
		return this.findBySpec(spec, null, null, sortBy, order);
	}

	@Override
	public List<PO> findBySpec(Specification<PO> spec, Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		List<PO> modelList = null;
		if (pageIndex != null) {
			Pageable pageable = PageUtil.buildPageable(pageIndex, pageSize, sortBy, order);
			modelList = this.getDao().findAll(spec, pageable).getContent();
		} else if (sortBy != null) {
			Sort pageSort = PageUtil.buildSort(sortBy, order);
			modelList = this.getDao().findAll(spec, pageSort);
		} else {
			modelList = this.getDao().findAll(spec);
		}
		return modelList;
	}

	@Override
	public PageModel<PO> findBySpecWithPage(Specification<PO> spec, Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		Pageable pageable = PageUtil.buildPageable(pageIndex, pageSize, sortBy, order);
		Page<PO> pageData = this.getDao().findAll(spec, pageable);
		return new PageModel<PO>(pageData);
	}

	@Override
	public long countByFilter(Map<String, Object> filter) {
		Specification<PO> spec = ExpressionUtil.genExpressionByFilter(filter);
		long count = this.countBySpec(spec);
		return count;
	}

	@Override
	public List<PO> findByFilter(Map<String, Object> filter) {
		return this.findByFilter(filter, null, null, null, null);
	}

	@Override
	public List<PO> findByFilter(Map<String, Object> filter, String sortBy, String order) {
		return this.findByFilter(filter, null, null, sortBy, order);
	}

	@Override
	public List<PO> findByFilter(Map<String, Object> filter, Integer pageIndex, Integer pageSize, String sortBy,
			String order) {
		Specification<PO> spec = ExpressionUtil.genExpressionByFilter(filter);
		List<PO> modelList = this.findBySpec(spec, pageIndex, pageSize, sortBy, order);
		return modelList;
	}

	@Override
	public PageModel<PO> findByFilterWithPage(Map<String, Object> filter, Integer pageIndex, Integer pageSize,
			String sortBy, String order) {
		Specification<PO> spec = ExpressionUtil.genExpressionByFilter(filter);
		PageModel<PO> pageData = this.findBySpecWithPage(spec, pageIndex, pageSize, sortBy, order);
		return pageData;
	}
	
	public <VO> List<VO> findVOByFilter(Map<String, Object> filter, Integer pageIndex, Integer pageSize, String sortBy,
			String order, List<DataRelation<VO, ?>> relations,Class<VO> voClass) {
		Specification<VO> spec = ExpressionUtil.genExpressionByFilter(filter);
		return this.findVOBySpec(spec, pageIndex, pageSize, sortBy, order, relations,voClass);
	}
	
	private <VO> List<VO> findVOBySpec(Specification<VO> spec, Integer pageIndex, Integer pageSize, String sortBy,
			String order, List<DataRelation<VO, ?>> relations,Class<VO> voClass) {
		List<DataRelationAction<VO, ?>> relationActionList=new ArrayList<>();
		for (DataRelation<VO, ?> relation : relations) {
			relationActionList.add(new DataRelationAction<>(relation));
		}

		Specification<PO> poSpec = null;
		for (DataRelationAction<VO, ?> relationAction : relationActionList) {
			poSpec = relationAction.rebuildSpec(spec);
		}

		List<PO> poList = this.findBySpec(poSpec, pageIndex, pageSize, sortBy, order);
		List<VO> voList = JSON.parseArray(JSON.toJSONString(poList), voClass);
		
		for (DataRelationAction<VO, ?> relationAction : relationActionList) {
			relationAction.relate(voList);
		}

		return voList;
	}
	

}
