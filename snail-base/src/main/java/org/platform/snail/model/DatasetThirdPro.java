package org.platform.snail.model;

public class DatasetThirdPro implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * FusionCharts下的dataSource属性下的三级目录属性
	*/
	private String value;
	
	public DatasetThirdPro(){
		
	}
	public DatasetThirdPro(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

}
