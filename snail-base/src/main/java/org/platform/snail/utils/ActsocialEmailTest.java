package org.platform.snail.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class ActsocialEmailTest {
    public static void main(String[] args) {
    	VelocityEngine velocityEngine = new VelocityEngine();
    	velocityEngine.setProperty("resource.loader","class");
    	velocityEngine.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("userName","xiaochen.liu");
        model.put("emailAddress", "xiaochen.liu@xingxinghuo.com");
        
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"email.vm", "UTF-8", model);
        System.out.println(text);
       // ActsocialMailSenderImpl.sendEmail(model,"欢迎加入", "welcome.vm",new String[]{"****@gmail.com"},new String[]{});

    }
}