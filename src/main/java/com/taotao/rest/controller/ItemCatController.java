package com.taotao.rest.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

import net.sf.json.JSONObject;

/**
 * 该控制器功能：返回JSON数据
 * 		返回之前，先判断callback参数是否为空，如果为空正常返回json数据，如果不为空，支持jsonp调用
* <p>Title: ItemCatController.java<／p>
* <p>Description: 商品分类展示 控制器<／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-07-23_19:10:42
* @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	//设置页面的Content-type 为application/json
	@RequestMapping(value="/list",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		ItemCatResult result = itemCatService.getItemCatList();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = null;
		//如果callback 为空，说明为普通请求，直接将Java对象转换成JSON之后，返回即可
		try {
			json = ow.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (StringUtils.isBlank(callback)) {
			return json;
		}
		//callback 不为空，说明要跨域，在将Java对象转换成json 字符串返回之前，需要用callback的值以及（）将json包裹一下
		return callback+"("+json+")"+";";
	}

}
