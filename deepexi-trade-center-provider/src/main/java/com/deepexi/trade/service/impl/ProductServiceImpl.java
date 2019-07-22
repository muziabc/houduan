package com.deepexi.trade.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepexi.trade.domain.ProductDO;
import com.deepexi.trade.domain.ProductDTO;
import com.deepexi.trade.domain.ProductQuery;
import com.deepexi.trade.e