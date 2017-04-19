/**
 * @Title: CxfClient.java
 * @Package org.platform.snail.cxf.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年1月31日 下午1:48:51
 * @version V1.0
 */

package org.platform.snail.cxf.service;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.platform.snail.cxf.model.Head;
import org.platform.snail.cxf.model.R01;
import org.platform.snail.cxf.model.R02;
import org.platform.snail.cxf.model.Result;
import org.platform.snail.cxf.model.S01;
import org.platform.snail.cxf.model.S02;

import com.thoughtworks.xstream.XStream;

/**
 * @ClassName: CxfClient
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年1月31日 下午1:48:51
 *
 */

public class CxfClient {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param @param args    设定文件
	 * @return void    返回类型
	 * @throws
	 * @author chenxiaoke
	 * @date 2015年1月31日 下午1:48:51
	 * @version V1.0
	 */

	public static void main(String[] args) {
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.getInInterceptors().add(new LoggingInInterceptor());
		bean.getInFaultInterceptors().add(new LoggingOutInterceptor());
		bean.setServiceClass(WebCxfService.class);
		bean.setAddress("http://10.1.5.35/cxf/webCxfService");
		WebCxfService webCxfService = (WebCxfService) bean.create();
		XStream xstream = new XStream();
		try {
			Head h=new Head();
			h.setOperCode("S02");
			S02 s=new S02();
			s.setUserName("admin");
			s.setPassWord("123123");
			xstream.alias("head", Head.class);
			xstream.alias("body", S02.class);
			xstream.alias("data", R02.class);
			xstream.alias("result", Result.class);


			String r=webCxfService.accept(xstream.toXML(h), xstream.toXML(s));
			Result<R02> o = (Result<R02>) xstream.fromXML(r);
			String billCode=o.getData().getBillCode();
			System.out.println(billCode);
			/*xstream.alias("body", S01.class);
			xstream.alias("data", R01.class);
			S01 s01=new S01();
			s01.setUserId("1");
			s01.setTel("");
			s01.setMsg("");
			s01.setTid("repairs");
			h.setOperCode("S01");
			h.setBillCode(billCode);
			Map<String,Object> data=new HashMap<String,Object>();
			data.put("name", "jack");
			s01.setData(data);
			System.out.println(xstream.toXML(s01));
			String r2=webCxfService.accept(xstream.toXML(h), xstream.toXML(s01));
			System.out.println(r2);*/
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
