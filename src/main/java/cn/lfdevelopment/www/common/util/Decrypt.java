/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.common.util;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Created by LiuFa on 2016/9/14.
 * cn.lfdevelopment.www.common.util
 * DevelopmentApp
 */
public class Decrypt {
    public static void main(String[] args){
        try {
            System.out.println(ConfigTools.decrypt("NOlmU6cyDMrUiN5SpsZM4uZoufcv/AZCdFyn4oCIrVwf+J73hEFlnos1/IotY86SX0nQtTuc0yvo+fVVxjScGA=="));
            System.out.println(ConfigTools.encrypt("jdbc:mysql://www.lfdevelopment.cn:3333/greateapp"));
            System.out.println("URL<prod>:" + ConfigTools.encrypt("jdbc:mysql://localhost:3333/greateapp"));
            System.out.println("username and password<prod>:" + ConfigTools.encrypt("root"));
            System.out.println(ConfigTools.encrypt("1001520.@WSX"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
