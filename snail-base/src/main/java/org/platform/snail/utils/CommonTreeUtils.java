package org.platform.snail.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.platform.snail.model.Resources;
import org.platform.snail.beans.Tree;

public class CommonTreeUtils {
	
	private List<Map<String,String>> resources;
	
	
	public CommonTreeUtils(List<Map<String,String>> resources){
		this.resources=resources;
	}
	public  List<Tree> getTreeList(String id){
		List<Tree> list=new ArrayList<Tree>();
		 List<Map<String,String>> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,String> row=temp.get(i);
				Tree tree=this.getChildTreeList(row);
				list.add(tree);
			}
		}
		return list;
	}
	public  List<Tree> getTreeListCaseSelf(String id){
		Map<String,String> resources =null;
		for(Map<String,String> temp:this.resources){
			if(temp.get("ID").equals(id)){
				resources=temp;
				break;
			}
		}
		Tree o=this.getChildTreeList(resources);
		
		List<Tree> list=new ArrayList<Tree>();
		 List<Map<String,String>> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,String> row=temp.get(i);
				Tree tree=this.getChildTreeList(row);
				list.add(tree);
			}
		}
		o.setChildren(list);
		List<Tree> rst=new ArrayList<Tree>();
		rst.add(o);
		return rst;
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
	private  Tree getChildTreeList(Map<String,String> resources){
		Tree tree=new Tree();
		tree.setIcon(resources.get("ICON"));
		tree.setHref(resources.get("HREF"));
		tree.setSrc(resources.get("SRC"));
		tree.setId(resources.get("ID"));
		tree.setText(resources.get("TEXT"));
		if(resources.get("STATE")==null){
			tree.setState("open");
		}else{
			tree.setState(resources.get("STATE"));
		}
		if(resources.get("CHILD_COUNT").equals("0")){
			tree.setCls("file");
			tree.setLeaf(true);
			tree.setState("open");
		}else{
			tree.setCls("folder");
			tree.setLeaf(false);
			List<Tree> children=new ArrayList<Tree>();
			List<Map<String,String>> list=this.getChildResourcesList(tree.getId());
			for(Map<String,String> childResources:list){
				children.add(getChildTreeList(childResources));
				
			}
			tree.setChildren(children);
		}
		return tree;
	}
}
