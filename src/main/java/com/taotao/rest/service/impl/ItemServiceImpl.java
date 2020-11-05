package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtil;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemParamItem;
import com.taotao.rest.jedis.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;

	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${ITEM_BASE_INFO_KEY}")
	private String ITEM_BASE_INFO_KEY;
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	@Value("${ITEM_DESC_KEY}")
	private String ITEM_DESC_KEY;
	@Value("${ITEM_PARAM_KEY}")
	private String ITEM_PARAM_KEY;
	
	/**
	 * 根据商品id获取商品信息
	 */
	@Override
	public Item getItemById(Long itemId) {
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				Item item = (Item) JsonUtil.jsonToObject(json, Item.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品id查询商品基本信息
		Item item = itemMapper.selectByPrimaryKey(itemId);
		
		//向redis中添加缓存。
		//添加缓存原则是不能影响正常的业务逻辑
		try {
			//向redis中添加缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId
					, JsonUtil.ObjectToJSON(item));
			//设置key的过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	/**
	 *  获取商品的描述信息
	* <p>Title: getItemDescById<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	@Override
	public ItemDesc getItemDescById(Long itemId) {
		//查询缓存
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				ItemDesc itemDesc = (ItemDesc) JsonUtil.jsonToObject(json, ItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据商品id查询商品详情
		ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//添加缓存
		try {
			//向redis中添加缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY
					, JsonUtil.ObjectToJSON(itemDesc));
			//设置key的过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
	
	/**
	 * 获取商品参数信息
	 */
	@Override
	public ItemParamItem getItemParamById(Long itemId) {
		//添加缓存逻辑
		//查询缓存
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				ItemParamItem itemParamitem = (ItemParamItem) JsonUtil.jsonToObject(json, ItemParamItem.class);
				return itemParamitem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询规格参数
		ItemParamItem itemParamItem = itemParamItemMapper.selectByItemId(itemId);
		//取规格参数
		if (itemParamItem != null ) {
			//添加缓存
			try {
				//向redis中添加缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY
						, JsonUtil.ObjectToJSON(itemParamItem));
				//设置key的过期时间
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return itemParamItem;
		}
		return null;
	}

}
