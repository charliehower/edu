package org.platform.snail.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.platform.snail.beans.AceTree;
import org.platform.snail.beans.AdditionalParameters;

public class CommonAceTreeUtils {
	
	private List<Map<String,String>> resources;
	
	
	public CommonAceTreeUtils(List<Map<String,String>> resources){
		this.resources=resources;
	}
	public  List<AceTree> getAceTreeList(String id){
		List<AceTree> list=new ArrayList<AceTree>();
		 List<Map<String,String>> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,String> row=temp.get(i);
				AceTree tree=this.getChildAceTreeList(row);
				list.add(tree);
			}
		}
		return list;
	}
	public  List<AceTree> getAceTreeListCaseSelf(String id){
		Map<String,String> resources =null;
		for(Map<String,String> temp:this.resources){
			if(temp.get("ID").equals(id)){
				resources=temp;
				break;
			}
		}
		AceTree o=this.getChildAceTreeList(resources);
		
		List<AceTree> list=new ArrayList<AceTree>();
		 List<Map<String,String>> temp=this.getChildResourcesList(id);
		if(temp!=null){
			for(int i=0;i<temp.size();i++){
				Map<String,String> row=temp.get(i);
				AceTree tree=this.getChildAceTreeList(row);
				list.add(tree);
			}
		}
		List<AceTree> rst=new ArrayList<AceTree>();
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
	private  AceTree getChildAceTreeList(Map<String,String> resources){
		AceTree tree=new AceTree();
		tree.setId(resources.get("ID"));
		tree.setName(resources.get("TEXT"));
		if(resources.get("CHILD_COUNT").equals("0")){
			tree.setType("item");
			AdditionalParameters additionalParameters=new AdditionalParameters();
			additionalParameters.setId(tree.getId());
			additionalParameters.setItemSelected(false);
			additionalParameters.setChildren(false);
			additionalParameters.setHref(resources.get("HREF"));
			additionalParameters.setSrc(resources.get("SRC"));
			tree.setAdditionalParameters(additionalParameters);
	
		}else{
			tree.setType("folder");
			AdditionalParameters additionalParameters=new AdditionalParameters();
			additionalParameters.setId(tree.getId());
			additionalParameters.setItemSelected(false);
			additionalParameters.setChildren(true);
			additionalParameters.setHref(resources.get("HREF"));
			additionalParameters.setSrc(resources.get("SRC"));
			tree.setAdditionalParameters(additionalParameters);
			
		}
		return tree;
	}
}
