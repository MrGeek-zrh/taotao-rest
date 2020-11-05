package com.taotao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.taotao.pojo.Item;

@Repository
public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);
    
    List<Item> findAllItem();
    
    List<Item> findListByPageIndexAndPageSize(@Param("pageIndex") Long pageIndex ,@Param("pageSize") Long pageSize);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}