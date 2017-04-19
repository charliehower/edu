package org.platform.snail.model;

public class CategoryThirdPro implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * FusionCharts下的dataSource属性下的三级目录属性
	 */
	private String label;
	
	public CategoryThirdPro(String label){
		this.label = label;
	}
	
	public CategoryThirdPro(){
		
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	

}
