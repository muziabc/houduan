package com.deepexi.ceshi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepexi.ceshi.domain.ProductDO;
import com.deepexi.ceshi.domain.ProductDTO;
import com.deepexi.ceshi.domain.ProductQuery;
import com.deepexi.ceshi.enums.ResultEnum;
import com.deepexi.ceshi.mapper.ProductMapper;
import com.deepexi.ceshi.service.ProductService;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;

/**
 * 产品Service组件实现
 * @author yangxi
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public List<ProductDTO> listProducts(ProductQuery productQuery) {
    	if(productQuery == null) {
    		log.error("productQuery is null");
    		throw new ApplicationException(ResultEnum.QUERY_PARAM_NOT_FOUND);
    	}
    	
    	// page=-1是跟前端约定好的参数，如果传-1表示不需要分页
    	if(productQuery.getPage() != null && productQuery.getPage() != -1) {
    		PageHelper.startPage(productQuery.getPage(), productQuery.getSize());
    	}
        
        List<ProductDO> products = productMapper.listProducts(productQuery);
        List<ProductDTO> targetProducts = ObjectCloneUtils.convertList(products, ProductDTO.class);
        return targetProducts;
    }

    @Override
	public ProductDTO getProductById(Long id) {
    	if(id == null) {
    		return null;
    	}
		ProductDO productDO = productMapper.selectById(id);
		if(prod