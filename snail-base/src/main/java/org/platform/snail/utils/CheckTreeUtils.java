package org.platform.snail.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.platform.snail.beans.CheckTree;

public class CheckTreeUtils {
	
	private List<Map<String,String>> resources;
	
	
	public CheckTreeUtils(List<Map<String,String>> resources){
		this.resources=resources;
	}
	public  List<CheckTree> getCheckTreeList(String id){
		List<CheckTree> list=new ArrayList<CheckTree>();
		 List<Map<String,String>> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,String> row=temp.get(i);
				CheckTree tree=this.getChildCheckTreeList(row);
				list.add(tree);
			}
		}
		return list;
	}
	public  List<Map<String,String>> getChildResourcesList(String id){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		if(this.resources!=null){
			for(int i=0;i<this.resources.size();i++){
				Map<String,String> row=(Map<String,String>)this.resources.get(i);
				if(row.get("PID").equals(id)){
						list.add(row);
				}
			}
		}
		return list;
	}
	private  CheckTree getChildCheckTreeList(Map<String,String> resources){
		CheckTree tree=new CheckTree();
		tree.setIcon(resources.get("ICON"));
		tree.setId(resources.get("ID"));
		tree.setText(resources.get("TEXT"));
		tree.setChecked(Boolean.valueOf(resources.get("CHECKED")));
		if(resources.get("CHILD_COUNT").equals("0")){
			tree.setCls("file");
			tree.setLeaf(true);
		}else{
			tree.setCls("folder");
			tree.setLeaf(false);
			if(tree.getChecked()){
				tree.setChecked(null);
			}
			List<CheckTree> children=new ArrayList<CheckTree>();
			List<Map<String,String>> list=this.getChildResourcesList(tree.getId());
			for(Map<String,String> childResources:list){
				children.add(getChildCheckTreeList(childResources));
				
			}
			tree.setChildren(children);
		}
		return tree;
	}
}
