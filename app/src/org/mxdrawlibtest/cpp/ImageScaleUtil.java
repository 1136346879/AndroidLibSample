package org.mxdrawlibtest.cpp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Create by Li
 * on 2019/6/4
 * <p>
 * Describe
 */
public class ImageScaleUtil {


    public static final int IMAGE_MIN_SIZE = 200;


    /**
     * 缩放
     * @param path  旧图片路径
     * @return      新图片路径
     */
    public static String scale(String path) {

        String newPath = NewPathUtil.getNewFilePath(path);

        return scale(path,newPath);
    }


    /**
     * 缩放
     * @param oldPath    旧图片路径
     * @param newPath    新图片路径
     * @return          新图片路径
     */
    public static String scale(String oldPath, String newPath) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(oldPath, options);

        if (options.outHeight <= IMAGE_MIN_SIZE && options.outWidth <= IMAGE_MIN_SIZE) {
            return oldPath;
        }

        options.inSampleSize = calculateInSampleSize(options, IMAGE_MIN_SIZE,
                IMAGE_MIN_SIZE * options.outHeight / options.outWidth);

        options.inJustDecodeBounds = false;

        Bitmap mBitmap = BitmapFactory.decodeFile(oldPath, options);

        File file = new File(newPath);

        try {

            FileOutputStream out = new FileOutputStream(file);

            if (mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {

            }

        } catch (Exception var5) {

            var5.printStackTrace();

            return "";
        }

        return newPath;
    }



    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                             int reqHeight) {

        int height = options.outHeight;

        int width = options.outWidth;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            int heightRatio = Math.round((float)height / (float)reqHeight);

            int widthRatio = Math.round((float)width / (float)reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

        }

        return inSampleSize;

    }

}
