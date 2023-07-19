package org.mxdrawlibtest.cpp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.MxDraw.MxDrawActivity;
import com.MxDraw.MxFunction;


public class MyImagePicker{

    public static final int 	NONE = 0;
    public static final int 	PHOTOHRAPH = 1;
    public static final int 	PHOTOZOOM = 2;
    public static final int 	PHOTORESOULT = 3;
    public static final String  IMAGE_UNSPECIFIED = "image/*";
    public static String   savepath = "";
    private static MyImagePicker instance = null;
    private static Activity    activity = null;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int MY_PERMISSIONS_REQUEST_CAMERA=2;

	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE
	};


	public static MyImagePicker getInstance(){
		if(instance == null){
			instance = new MyImagePicker();
		}
		return instance;
	}


    static public void init(Activity activity){
		MyImagePicker.activity = activity;
    }


	static public void openPhoto(){
		Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        activity.startActivityForResult(intent, PHOTOZOOM);
	}


	static public void onImageSaved(String sPath)
	{
		MxFunction.callImageSave(sPath);
	}


	static String mCurrentPhotoPath;


	static private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		//.getExternalFilesDir()方法可以获取�?SDCard/Android/data/你的应用的包�?files/ 目录，一般放一些长时间保存的数�?
		//File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures");
		//创建临时文件,文件前缀不能少于三个字符,后缀如果为空默认�?.tmp"
		File image = File.createTempFile(
				imageFileName,  /* 前缀 */
				".jpg",         /* 后缀 */
				storageDir      /* 文件�?*/
		);
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}


	static public void openCamera(){

		if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA},MY_PERMISSIONS_REQUEST_CAMERA);
			return;
		}

		/*获取当前系统的android版本*/
		int currentapiVersion = Build.VERSION.SDK_INT;
		if (currentapiVersion<24) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File pFile = null;
			try {
			   pFile = createImageFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			mCurrentPhotoPath = pFile.getAbsolutePath();

			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(pFile));
			activity.startActivityForResult(intent, PHOTOHRAPH);
		}
		else
		{


			if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
					!= PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(  activity,
						PERMISSIONS_STORAGE,
						REQUEST_EXTERNAL_STORAGE);
				return;
			}

			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			// Ensure that there's a camera activity to handle the intent
			if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null)
			{//判断是否有相机应�?
				// Create the File where the photo should go
				File photoFile = null;
				try {
					photoFile = createImageFile();//创建临时图片文件
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				// Continue only if the File was successfully created
				if (photoFile != null) {
					//FileProvider 是一个特殊的 ContentProvider 的子类，
					//它使�?content:// Uri 代替�?file:/// Uri. ，更便利而且安全的为另一个app分享文件
					String sPackageName =  MxDrawActivity.PackageName();
					Uri photoURI = FileProvider.getUriForFile(activity,
							sPackageName,
							photoFile);
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
					activity.startActivityForResult(takePictureIntent, PHOTOHRAPH);
				}
			}
			else {
				Toast.makeText(activity, "no camera", Toast.LENGTH_SHORT).show();
			}
		}
    }


	private String getImagePath(Uri uri, String selection) {
		String path = null;
		//通过Uri和selection老获取真实的图片路径
		Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			}
			cursor.close();
		}
		return path;
	}


	private String handleImageBeforeKitKat(Intent intent) {
		Uri imageUri = intent.getData();
//		String imagePath = getImagePath(imageUri, null)
//		MySaveImage.Scale2(imagePath);
//        imagePath = MyBitmapAdjustUtil.adjustFromPhoto(imagePath);
//		onImageSaved(imagePath);
		return getImagePath(imageUri, null);
	}


	@TargetApi(19)
	private String handleImageOnKitKat(Intent data) {
		String imagePath = null;
		Uri imageUri = data.getData();
		if (DocumentsContract.isDocumentUri(activity, imageUri)) {
			//如果是document类型的uri,则通过document id处理
			String docId = DocumentsContract.getDocumentId(imageUri);
			if ("com.android.providers.media.documents".equals(imageUri.getAuthority())) {
				String id = docId.split(":")[1];//解析出数字格式的id
				String selection = MediaStore.Images.Media._ID + "=" + id;
				imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
			} else if ("com.android.downloads.documents".equals(imageUri.getAuthority())) {
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
				imagePath = getImagePath(contentUri, null);
			}
		} else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			//如果是content类型的Uri，则使用普通方式处�?
			imagePath = getImagePath(imageUri, null);
		} else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			//如果是file类型的Uri,直接获取图片路径即可
			imagePath = imageUri.getPath();
		}

		return imagePath;
//		MySaveImage.Scale2(imagePath);
//        imagePath = MyBitmapAdjustUtil.adjustFromPhoto(imagePath);
//		onImageSaved(imagePath);
	}


	 public void onActivityResult(int requestCode, int resultCode, Intent data){

		if (resultCode == NONE)
            return;


		//	拍照
        if (requestCode == PHOTOHRAPH) {
//			  MySaveImage.Scale2(mCurrentPhotoPath);
//            MyBitmapAdjustUtil.adjustFromCamera(mCurrentPhotoPath);
//            onImageSaved(mCurrentPhotoPath);
			showImage(mCurrentPhotoPath,true);
        }

        if (data == null)
            return;


        //	相册
        if (requestCode == PHOTOZOOM) {
        	String path;
			if (Build.VERSION.SDK_INT >= 19) {
				path = handleImageOnKitKat(data);
			} else {
				path = handleImageBeforeKitKat(data);
			}
			showImage(path,false);
			return;
		}


        if (requestCode == PHOTORESOULT) {

        	Bundle extras = data.getExtras();

        	if (extras != null) {

                onImageSaved(savepath);
            }

        }

	}


	/**
	 * 展示图片
	 * @param path		图片路径
	 * @param camera	获取方式
	 */
	public void showImage(String path,boolean camera) {

		ProgressDialog progressDialog = new ProgressDialog(activity);

		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		progressDialog.show();

		String adjustPath = camera ? MyBitmapAdjustUtil.adjustFromCamera(path) :
				MyBitmapAdjustUtil.adjustFromPhoto(path);

		String scalePath = camera ? ImageScaleUtil.scale(adjustPath,adjustPath) : ImageScaleUtil.scale(adjustPath);

		progressDialog.dismiss();

		onImageSaved(scalePath);

	}

}
