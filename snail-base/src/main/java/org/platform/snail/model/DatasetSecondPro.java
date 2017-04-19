package org.platform.snail.model;

import java.util.ArrayList;

public class DatasetSecondPro implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * FusionCharts下的dataSource属性下的二级目录属性
	 */
	private String seriesname;
	/**
	 * FusionCharts下的dataSource属性下的二级目录属性
	*/
	private ArrayList<DatasetThirdPro> data;
	
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public ArrayList<DatasetThirdPro> getData() {
		return data;
	}
	public void setData(ArrayList<DatasetThirdPro> data) {
		this.data = data;
	}
	
}
