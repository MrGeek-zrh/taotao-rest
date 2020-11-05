package com.taotao.mapper;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.ItemParamItem;

@Repository
public interface ItemParamItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemParamItem record);

    int insertSelective(ItemParamItem record);

    ItemParamItem selectByPrimaryKey(Long id);
    
    ItemParamItem selectByItemId(Long itemId);

    int updateByPrimaryKeySelective(ItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(ItemParamItem record);

    int updateByPrimaryKey(ItemParamItem record);
}