package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类展示功能的数据获取：实现类
* <p>Title: ItemCatServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-07-23_18:24:02
* @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public ItemCatResult getItemCatList() {
		//调用递归方法，获取分类数据
		List itemCatList = getItemCatList(0L);
		ItemCatResult itemCatResult = new ItemCatResult();
		itemCatResult.setData(itemCatList);
		return itemCatResult;
	}
	
	/**
	 * 从itemCat表中 以节点的形式获取到所有的数据
	 * 借助递归，对查询到的数据进行处理，使其变为指定格式的数据
	* <p>Title: getItemCatList<／p>
	* <p>Description: <／p>
	* @param parentId 父节点
	* @return 符合指定数据格式数据的List集合
	 */
	public List getItemCatList(Long parentId) {
		
		//查询itemCat表，获取到指定父节点的数据
		List<ItemCat> list = itemCatMapper.selectByParentId(parentId);
		
		//定义一个计数器，用于控制页面最多展示14 行
		int index=0;
		
		//遍历每一个节点
		List resultList = new ArrayList<>();
		for (ItemCat itemCat : list) {
			if (index>=14) {
				break;
			}
			//如果是父节点
			if (itemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				catNode.setUrl("/products/"+itemCat.getId()+".html");
				//如果当前节点的parentId 为0 ，则对 name 属性进行单独处理
				if (itemCat.getParentId()==0) {
					catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
					index++;
				}else {
					catNode.setName(itemCat.getName());
				}
				catNode.setItems(getItemCatList(itemCat.getId()));
				resultList.add(catNode);
			}else {
				//是叶子节点
				String items = "/products/"+itemCat.getId()+".html|"+itemCat.getName();
			}
		}
		return resultList;
		
	}

}
