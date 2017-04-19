package org.platform.snail.model;

import java.util.ArrayList;

public class CategorySecondPro implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * FusionCharts下的category属性下的二级目录属性
	*/
	private ArrayList<CategoryThirdPro> category;
	
	public ArrayList<CategoryThirdPro> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<CategoryThirdPro> category) {
		this.category = category;
	}
	
}
