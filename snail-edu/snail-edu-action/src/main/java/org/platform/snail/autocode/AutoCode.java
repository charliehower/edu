package org.platform.snail.autocode;

import org.platform.snail.edu.model.DocFlowTask;

/**
 * @Title: AutoCode.java
 * @Package
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:33:43
 * @version V1.0
 */

/**
 * @ClassName: AutoCode
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:33:43
 *
 */

public class AutoCode {

    /**
     *
     * @param args
     */

    public static void main(String[] args) {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils();
        Class c = DocFlowTask.class;
        autoCodeUtils.init(c);
        try {
            autoCodeUtils.createAction(c);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
