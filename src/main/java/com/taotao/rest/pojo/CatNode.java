package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 作为 商品分类展示的返回值 使用 的实体类
* <p>Title: CatNode.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-07-23_18:01:42
* @version 1.0
 */
public class CatNode {
	
	@JsonProperty(value="u")
	private String url;

	@JsonProperty(value="n")
	private String name;

	@JsonProperty(value="i")
	private List items;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}
	
}
