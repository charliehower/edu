package org.platform.snail.utils;

import java.lang.reflect.Field;

import org.platform.snail.model.Department;

public class ExtUtils {
	
	private static void getObjectProperties(Object obj) {
		Field fields[] = obj.getClass().getDeclaredFields();
		try {
			Field.setAccessible(fields, true);
			for (int i = 0; i <fields.length; i++) {
				String key=fields[i].getName();

				//String rs="<result column=\""+key+"\" property=\""+key+"\" jdbcType=\"VARCHAR\" />";
				String rs="{name:'"+key+"',type:'string', mapping:'"+key+"'},";
				System.out.println(rs);
			}
			System.out.println("---------------------------------------------------------");
			for (int i = 0; i <fields.length; i++) {
				String key=fields[i].getName();
				String rs="{dataIndex:'"+key+"',header:'"+key+"',width:'80',hidden:false},";
				System.out.println(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		ExtUtils.getObjectProperties(new Department());
	}
}
