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
            System.out.println("url : "+ConfigTools.decrypt("G11Jor+OrLz9MFztdkOfqRnrJKVrFCDdBbYJFmB0qGjUARxPr2tiyRzUn4xbnk/XqPgM8PMjdIJ/pO8UF4aeVg=="));
            System.out.println("username : "+ConfigTools.decrypt("bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA=="));
            System.out.println("password : "+ConfigTools.decrypt("l65GeQaXVXxx2ogcQeZLAFM7VcPwgzc9202vxql4hjCbjM8dVm/sD4osdvaBdVkC+BiYdnYL2EzpaCysXAZ5Gw=="));


            System.out.println("password : "+ConfigTools.encrypt("jdbc:mysql://joylau.cn:3333/greateapp"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
