package org.platform.snail.autocode;

import org.platform.snail.portal.model.ExcelMain;

/**
 * @Title: AutoCode.java
 * @Package 
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:33:43
 * @version V1.0
 */

/**
 * @ClassName: AutoCode
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:33:43
 *
 */

public class AutoCode {

	/**
	 * @Title: main
	 * @Description: TODO
	 * @param @param args    设定文件
	 * @return void    返回类型
	 * @throws
	 * @author chenxiaoke
	 * @date 2014年12月14日 下午8:33:43
	 * @version V1.0
	 */

	public static void main(String[] args) {
		AutoCodeUtils autoCodeUtils=new AutoCodeUtils();
		Class c=ExcelMain.class;
		autoCodeUtils.init(c);
		try {
			autoCodeUtils.createService(c);
			autoCodeUtils.createServiceImpl(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
