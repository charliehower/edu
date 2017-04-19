package org.platform.snail.model;

import java.util.ArrayList;

public class FusionCharts implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 图表横坐标设置
	 */
	private ArrayList<CategorySecondPro> categories;
	/**
	 * 图表数据设置
	 */
	private ArrayList<DatasetSecondPro> dataset;
	
	private ArrayList<DialsSecondPro> dials;
	
	public ArrayList<CategorySecondPro> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<CategorySecondPro> categories) {
		this.categories = categories;
	}
	public ArrayList<DatasetSecondPro> getDataset() {
		return dataset;
	}
	public void setDataset(ArrayList<DatasetSecondPro> dataset) {
		this.dataset = dataset;
	}
	public ArrayList<DialsSecondPro> getDials() {
		return dials;
	}
	public void setDials(ArrayList<DialsSecondPro> dials) {
		this.dials = dials;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
