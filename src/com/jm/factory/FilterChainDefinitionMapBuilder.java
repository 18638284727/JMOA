package com.jm.factory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jm.entity.Jurisdiction;
import com.jm.service.JurisdictionService;

public class FilterChainDefinitionMapBuilder {

	@Autowired
	private JurisdictionService jurisdictionService;
	
	public Map<String, String> builderFilterChainDefinitionMapBuilder()
	{
		Map<String, String> map = new LinkedHashMap<String, String>();
		//访问数据库，读取权限，放入map
		List<Jurisdiction> list = jurisdictionService.getAll();
		for (Jurisdiction jurisdiction : list) {
			map.put(jurisdiction.getjUrl(), jurisdiction.getjInterceptorRule());
		}
		
		return map;
	}
	
}
