package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtil;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.rest.jedis.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 商品内容Service实现类
* <p>Title: ContentServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-13_16:52:44
* @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	//定义一个用于保存内容的key，所有的内容都保存在同一个key中，并且通过item 进行区分
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	/*
	 * 根据contentId 获取content 信息
	 */
	@Override
	public List<Content> getContentListByContentId(Long id) {
		/*添加缓存:
		 * 		查询数据库之前需要先查询缓存
		 * 			如果有，直接从缓存中获取并返回;
		 * 			如果没有，则需要向数据库中查询,并需要在返回结果之前将查询到的数据存入缓存中
		 */
		try {
			String result = jedisClient.hget(REDIS_CONTENT_KEY, id+"");
			//判断缓存中是否有需要的数据
			if (!StringUtils.isBlank(result)) {
				//缓存中有需要的数据，直接从缓存中获取并返回即可
				//由于存在缓存中的数据的格式为json，需要先将数据的格式进行转换
				List<Content> list = (List<Content>)JsonUtil.jsonToObject(result, List.class);
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//缓存中没有需要的数据，需要区数据库进行查询
		List<Content>list = contentMapper.findContentListByCategoryId(id);
		
		try {
			//返回结果之前，将查询到的结果存入缓存中
			jedisClient.hset(REDIS_CONTENT_KEY, id+"", JsonUtil.ObjectToJSON(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回结果
		return list;
	}

	/**
	 * 同步缓存
	 */
	@Override
	public TaotaoResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return TaotaoResult.ok();
	}

}
