package com.taotao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.taotao.pojo.ContentCategory;

@Repository
public interface ContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ContentCategory record);

    int insertSelective(ContentCategory record);

    ContentCategory selectByPrimaryKey(Long id);
    
    List<ContentCategory> getContentCatList(Long parentId);

    int updateByPrimaryKeySelective(ContentCategory record);

    int updateByPrimaryKey(ContentCategory record);
}