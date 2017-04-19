/**
 * @Title: PublicClassVo.java
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

import org.platform.snail.edu.model.PublicClassWithBLOBs;


/**
 * @ClassName: PublicClassVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class PublicClassVo extends PublicClassWithBLOBs{
	    private Integer total=0;
	    private float scoreAvg=0;
	    private String disciplineName;
	    private String teacherName;
	    private String auditorName;
	    private String keName;
	    private String auditorNameSec;
	    private String auditorNameThi;
	    private String auditorNameFor;
		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public String getDisciplineName() {
			return disciplineName;
		}

		public void setDisciplineName(String disciplineName) {
			this.disciplineName = disciplineName;
		}

		public String getTeacherName() {
			return teacherName;
		}

		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}

		public String getAuditorName() {
			return auditorName;
		}

		public void setAuditorName(String auditorName) {
			this.auditorName = auditorName;
		}

		public String getKeName() {
			return keName;
		}

		public void setKeName(String keName) {
			this.keName = keName;
		}

		public float getScoreAvg() {
			return scoreAvg;
		}

		public void setScoreAvg(float scoreAvg) {
			this.scoreAvg = scoreAvg;
		}

		public String getAuditorNameSec() {
			return auditorNameSec;
		}

		public void setAuditorNameSec(String auditorNameSec) {
			this.auditorNameSec = auditorNameSec;
		}

		public String getAuditorNameThi() {
			return auditorNameThi;
		}

		public void setAuditorNameThi(String auditorNameThi) {
			this.auditorNameThi = auditorNameThi;
		}

		public String getAuditorNameFor() {
			return auditorNameFor;
		}

		public void setAuditorNameFor(String auditorNameFor) {
			this.auditorNameFor = auditorNameFor;
		}

		
	    
}
