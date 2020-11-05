package com.taotao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.ItemCat;

@Repository
public interface ItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    ItemCat selectByPrimaryKey(Long id);
    
    List<ItemCat> selectByParentId(Long parentId);

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);
}