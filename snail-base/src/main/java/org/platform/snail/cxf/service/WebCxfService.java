/**
 * @Title: Cxf.java
 * @Package org.platform.snail.cxf.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年1月31日 下午12:43:31
 * @version V1.0
 */

package org.platform.snail.cxf.service;

/**
 * @ClassName: Cxf
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年1月31日 下午12:43:31
 *
 */

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebCxfService {
	public java.lang.String accept(
			@WebParam(name = "head") java.lang.String head,
			@WebParam(name = "body") java.lang.String body)
			throws java.rmi.RemoteException;
}
