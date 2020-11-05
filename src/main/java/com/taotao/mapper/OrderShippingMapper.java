package com.taotao.mapper;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.OrderShipping;

@Repository
public interface OrderShippingMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderShipping record);

    int insertSelective(OrderShipping record);

    OrderShipping selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderShipping record);

    int updateByPrimaryKey(OrderShipping record);
}