package com.hmq.framework.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.hmq.framework.model.IPkModel;

//告诉spring容器不要创建此bean
@NoRepositoryBean
public interface IGenDao<PO extends IPkModel<ID>, ID extends Serializable>
		extends JpaRepository<PO, ID>, JpaSpecificationExecutor<PO> {
}
