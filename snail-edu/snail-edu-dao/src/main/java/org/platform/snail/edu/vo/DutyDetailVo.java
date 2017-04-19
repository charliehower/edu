/**
 * @Title: DutyDetailVo.java
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

import java.util.Date;

import org.platform.snail.edu.model.DutyDetail;

/**
 * @ClassName: DutyDetailVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class DutyDetailVo extends DutyDetail{
	    private String name;
	    
	    private Date dutyStart;

	    private Date dutyEnd;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getDutyStart() {
			return dutyStart;
		}

		public void setDutyStart(Date dutyStart) {
			this.dutyStart = dutyStart;
		}

		public Date getDutyEnd() {
			return dutyEnd;
		}

		public void setDutyEnd(Date dutyEnd) {
			this.dutyEnd = dutyEnd;
		}
	    
	    
}
