package org.platform.snail.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.platform.snail.dao.SystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
public class InvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource,FilterInvocationSecurityMetadataSourceNative {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	private Map<String, Collection<ConfigAttribute>> resource;
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	public void loadResourceDefine() throws Exception {
		logger.info("load resource define");
		this.resource = new HashMap<String, Collection<ConfigAttribute>>();
		List<Map<String,String>> list=systemDao.loadResourceDefine();
		for(Map<String,String> map:list){
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			if(map.get("ROLES")!=null){
				for(String role:map.get("ROLES").split(",")){
					ConfigAttribute ca = new SecurityConfig(role);
					atts.add(ca);
				}
			}
			logger.info(map.get("RESOURCES"));
			logger.info(map.get("ROLES"));
			resource.put(map.get("RESOURCES"), atts);
		}
		
	}
	// 查询一个用户的是否有权限访问 某一个URL
	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		//String url = ((FilterInvocation) object).getRequestUrl();
		String url=this.getRequestRi(((FilterInvocation) object).getFullRequestUrl());
		Iterator<String> ite = resource.keySet().iterator();
		logger.info("url  "+url);
		while (ite.hasNext()) {
			String resURL = ite.next();
			//logger.info("resURL  "+resURL);
			if (urlMatcher.pathMatchesUrl(url, resURL)) {
				Collection<ConfigAttribute> returnCollection = resource.get(resURL);
				for(ConfigAttribute ca:returnCollection){
					logger.info(ca.getAttribute());
				}
				
				return returnCollection;
			}
		}
		logger.info("permission false");
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resource.entrySet()) {
			for (ConfigAttribute attrs : entry.getValue()) {
				allAttributes.add(attrs);
				logger.info(attrs.getAttribute());
			}
		}
		return allAttributes;
	}
	String getRequestRi(String url){
		if(url.indexOf("?")!=-1){
			url=url.substring(0, url.indexOf("?"));
		}
		String a=(url.split("//"))[1];
		String b=a.split("/")[0];
		return a.substring(b.length(), a.length());
	}
	
	public static void main(String args[]){
		String url="http://127.0.0.1:8082/portal/index.jsp";
		String a=(url.split("//"))[1];
		String b=a.split("/")[0];
		System.out.println(a);
		System.out.println(b);
		System.out.println(a.substring(b.length(), a.length()));
	}
}
