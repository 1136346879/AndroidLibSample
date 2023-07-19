package org.mxdrawlibtest.cpp;

import java.io.File;

/**
 * Create by Li
 * on 2019/6/4
 * <p>
 * Describe
 */
public class NewPathUtil {



    /**
     * 获取新文件的绝对路径
     *
     * @param path  原始图片绝对路径
     * @return  新文件的绝对路径
     *
     */
    public static String getNewFilePath(String path) {

        File file = new File(path);

        if (file.exists()) {

            String parentPath = file.getParent();

            String name = file.getName();

            String newName = getNewFileName(name);

            return parentPath + File.separator + newName;

        }

        return "";

    }



    /**
     * 获取新文件的名称
     *
     * @param name  原始图片名称
     * @return  新文件的名称
     *
     */
    private static String getNewFileName(String name) {

        String pre = name.substring(0, name.lastIndexOf("."));

        String newPre = pre + "_1" ;

        return name.replace(pre,newPre);

    }


}
