/**
 * @Title: CxfClientServiceImpl.java
 * @Package org.platform.snail.service.impl
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年2月8日 上午10:50:51
 * @version V1.0
 */

package org.platform.snail.service.impl;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.platform.snail.cxf.model.Head;
import org.platform.snail.cxf.model.R01;
import org.platform.snail.cxf.model.R02;
import org.platform.snail.cxf.model.Result;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.cxf.model.S02;
import org.platform.snail.cxf.service.WebCxfService;
import org.platform.snail.service.CxfClientService;
import org.platform.snail.utils.Config;
import org.platform.snail.utils.SnailUtils;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;

/**
 * @ClassName: CxfClientServiceImpl
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年2月8日 上午10:50:51
 *
 */
@Service("cxfClientService")
public class CxfClientServiceImpl implements CxfClientService {

	private WebCxfService webCxfService;
	private String billCode;
	private Logger logger = Logger.getLogger(this.getClass());
	private XStream xstreamS01;
	private XStream xstreamS02;
	public Result<R01> S01(org.platform.snail.cxf.model.S01 body) {
		this.logger.info("==============>S01");
		if(billCode==null){
			this.autoLogin();
		}
		Head h=new Head();
		h.setOperCode("S01");
		h.setBillCode(billCode);
		try {
			
			this.logger.info(xstreamS01.toXML(h));
			this.logger.info(xstreamS01.toXML(body));
			String xml=webCxfService.accept(xstreamS01.toXML(h), xstreamS01.toXML(body));
			this.logger.info(xml);
			@SuppressWarnings("unchecked")
			Result<R01> o = (Result<R01>) xstreamS01.fromXML(xml);
			this.logger.info(o);
			return o;
		}catch(Exception e){
			this.logger.error(e);
		}
		return null;
	}
	public void init(){
		if(this.webCxfService==null){
			this.logger.info("init webCxfService");
			String url=Config.getProperty("cxf.url");
			if(SnailUtils.isBlankString(url)){
				this.logger.error("cxf.url in properties is not setting");
				return;
			}
			this.logger.info("=====> init webCxfService client "+url);
			JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
			bean.getInInterceptors().add(new LoggingInInterceptor());
			bean.getInFaultInterceptors().add(new LoggingOutInterceptor());
			bean.setServiceClass(WebCxfService.class);
			bean.setAddress(url);
			webCxfService = (WebCxfService) bean.create();
		}
		if(this.xstreamS02==null){
			this.logger.info("init xstreamS02");
			xstreamS02 = new XStream();
			xstreamS02.alias("head", Head.class);
			xstreamS02.alias("body", S02.class);
			xstreamS02.alias("data", R02.class);
			xstreamS02.alias("result", Result.class);
		}
		if(this.xstreamS01==null){
			this.logger.info("init xstreamS01");
			xstreamS01 = new XStream();
			xstreamS01.alias("head", Head.class);
			xstreamS01.alias("body", S01.class);
			xstreamS01.alias("data", R01.class);
			xstreamS01.alias("result", Result.class);
		}
		
	}
	public void autoLogin(){
		this.logger.info("=====> autoLogin start");
		this.init();
		
		String username=Config.getProperty("cxf.username");
		String passwd=Config.getProperty("cxf.passwd");
		if(SnailUtils.isBlankString(username)){
			this.logger.error("cxf.username in properties is not setting");
			return;
		}
		if(SnailUtils.isBlankString(passwd)){
			this.logger.error("cxf.passwd in properties is not setting");
			return;
		}
		Head h=new Head();
		h.setOperCode("S02");
		S02 s=new S02();
		s.setUserName(username);
		s.setPassWord(passwd);
		
		
		try {
			String xml=webCxfService.accept(xstreamS02.toXML(h), xstreamS02.toXML(s));
			@SuppressWarnings("unchecked")
			Result<R02> o = (Result<R02>) xstreamS02.fromXML(xml);
			this.logger.info(o);
			if(o.isState()){
				billCode=o.getData().getBillCode();
			}
		}catch(Exception e){
			this.logger.error(e);
		}
	}

}
