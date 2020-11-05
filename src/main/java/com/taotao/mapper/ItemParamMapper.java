package com.taotao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.ItemParam;

@Repository
public interface ItemParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    ItemParam selectByPrimaryKey(Long id);
    
    List<ItemParam> selectByItemCatId(Long itemCatId);
    
    List<ItemParam>findItemParamList();

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKeyWithBLOBs(ItemParam record);

    int updateByPrimaryKey(ItemParam record);
}