/**
 * @Title: CxfClientService.java
 * @Package org.platform.snail.service
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2015年2月8日 上午10:47:55
 * @version V1.0
 */

package org.platform.snail.service;

import org.platform.snail.cxf.model.R01;
import org.platform.snail.cxf.model.Result;
import org.platform.snail.cxf.model.S01;

/**
 * @ClassName: CxfClientService
 * @Description: TODO
 * @author chenxiaoke
 * @date 2015年2月8日 上午10:47:55
 *
 */

public interface CxfClientService {
	public Result<R01> S01(S01 body);
	public void autoLogin();
}
