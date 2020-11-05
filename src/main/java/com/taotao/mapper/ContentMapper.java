package com.taotao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.Content;

@Repository
public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);
    
    List<Content>findContentListByCategoryId(Long categoryId);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
}