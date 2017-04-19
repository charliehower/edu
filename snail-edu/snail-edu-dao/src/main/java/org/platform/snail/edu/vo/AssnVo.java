/**
 * @Title: AssnVo.java
 * @Package org.platform.snail.edu.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package org.platform.snail.edu.vo;

import org.platform.snail.edu.model.Assn;

/**
 * @ClassName: AssnVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class AssnVo extends Assn{
	    private String adviserName;
	    
	    private String categoryName;
	    
	    private String chiefName;

		public String getAdviserName() {
			return adviserName;
		}

		public void setAdviserName(String adviserName) {
			this.adviserName = adviserName;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getChiefName() {
			return chiefName;
		}

		public void setChiefName(String chiefName) {
			this.chiefName = chiefName;
		}
	    
}
