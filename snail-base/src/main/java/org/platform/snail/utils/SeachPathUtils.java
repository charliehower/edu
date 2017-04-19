package org.platform.snail.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.platform.snail.utils.SnailUtils;
import org.platform.snail.model.Resources;

public class SeachPathUtils {
	public static List<Resources> getPath(Map<String,Resources> map,String id){
		List<Resources> list=new ArrayList<Resources>();
		if(SnailUtils.isBlankString(id)){
			return list;
		}
		if(map==null){
			return list;
		}
		Resources o=map.get(id);
		list.add(o);
		while(!o.getParentResourcesId().equals("0")){
			o=map.get(o.getParentResourcesId());
			list.add(o);
		}
		Collections.reverse(list);
		return list;
	}
}
