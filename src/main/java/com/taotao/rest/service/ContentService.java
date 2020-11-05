package com.taotao.rest.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Content;

/**
 * 商品内容Service
* <p>Title: ContentService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-13_16:50:59
* @version 1.0
 */
public interface ContentService {
	
	public List<Content> getContentListByContentId(Long id);
	
	public TaotaoResult syncContent(Long cid);
}
