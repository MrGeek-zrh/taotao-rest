package com.taotao.rest.service;

import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemParamItem;

/**
 * 商品管理Service
* <p>Title: ItemService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-19_01:03:43
* @version 1.0
 */
public interface ItemService {

	/**
	 * 根据商品id 获取商品信息
	* <p>Title: getItemById<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	public Item getItemById(Long itemId);

	/**
	 *  获取商品的描述信息
	* <p>Title: getItemDescById<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	ItemDesc getItemDescById(Long itemId);

	/**
	 *获取规格参数
	* <p>Title: getItemParamById<／p>
	* <p>Description: <／p>
	* @param itemId
	* @return
	 */
	ItemParamItem getItemParamById(Long itemId);
}
