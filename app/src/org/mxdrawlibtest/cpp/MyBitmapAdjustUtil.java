package org.mxdrawlibtest.cpp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Create by Li
 * on 2019/5/29
 * <p>
 *  图片角度适配工具类
 */
public class MyBitmapAdjustUtil {




    /**
     * 矫正图片 - 相机（修改原始图片）
     *
     * @param path  图片绝对路径
     * @return  保存路径
     *
     */
    public static String adjustFromCamera(String path) {

        Bitmap bitmap = BitmapFactory.decodeFile(path);

        return adjustFromCamera(path,bitmap);

    }



    /**
     * 矫正图片 - 相机（修改原始图片）
     *
     * @param path  图片绝对路径
     * @param bitmap    原始图片
     * @return  保存路径
     *
     */
    public static String adjustFromCamera(String path, Bitmap bitmap) {

        return adjustBitmap(path,path,bitmap);

    }



    /**
     * 矫正图片 - 相册 （不修改原始图片）
     *
     * @param path  图片绝对路径
     * @return  保存路径
     *
     */
    public static String adjustFromPhoto(String path) {

        Bitmap bitmap = BitmapFactory.decodeFile(path);

        return adjustFromPhoto(path,bitmap);

    }



    /**
     * 矫正图片 - 相册 （不修改原始图片）
     *
     * @param path  图片绝对路径
     * @param bitmap    原始图片
     * @return  保存路径
     *
     */
    public static String adjustFromPhoto(String path, Bitmap bitmap) {

        String newPath = NewPathUtil.getNewFilePath(path);


        return adjustBitmap(path,newPath,bitmap);

    }



    /**
     * 矫正图片转角度
     *
     * @param oriPath   原始图片绝对路径
     * @param newPath   保存图片绝对路径
     * @param bitmap    原始图片
     *
     */
    private static String adjustBitmap(String oriPath, String newPath , Bitmap bitmap) {

        int bitmapDegree = getBitmapDegree(oriPath);

        if (bitmapDegree == 0) {
            return oriPath;
        }

        Bitmap newBitmap = rotateBitmap(bitmap, bitmapDegree);

        return resaveBitmap(newBitmap,newPath);

    }



    /**
     * 读取图片的旋转的角度
     *
     * @param path  图片绝对路径
     * @return      图片的旋转角度
     *
     */
    private static int getBitmapDegree(String path) {

        int degree = 0;

        try {

            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);

            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }



    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap    原始图片
     * @param degrees   原始图片的角度
     * @return Bitmap   旋转后的图片
     *
     */
    private static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {

        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }

        Matrix matrix = new Matrix();

        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);

        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);

        bitmap.recycle();

        return bmp;
    }



    /**
     * 重新保存Bitmap
     *
     * @param bitmap    原始图片
     * @param path      保存图片绝对路径
     *
     */
    private static String resaveBitmap(Bitmap bitmap, String path) {

        File file = new File(path);

        try {

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            bos.flush();

            bos.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return path;
    }


}
