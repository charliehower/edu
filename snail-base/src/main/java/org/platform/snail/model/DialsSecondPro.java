package org.platform.snail.model;

import java.util.ArrayList;

public class DialsSecondPro implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<DialsThirdPro> data;

	public ArrayList<DialsThirdPro> getData() {
		return data;
	}

	public void setData(ArrayList<DialsThirdPro> data) {
		this.data = data;
	}
	
	
}
