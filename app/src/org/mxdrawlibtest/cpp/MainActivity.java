package org.mxdrawlibtest.cpp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.MxDraw.MxFunction;
import com.MxDraw.MxJNI;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ParcelFileDescriptor{


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String TAG = "MainActivity";
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    void openPdf() {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        ParcelFileDescriptor fd = new ParcelFileDescriptor();
        int pageNum = 0;
        PdfiumCore pdfiumCore = new PdfiumCore(this);
        try {
            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);

            pdfiumCore.openPage(pdfDocument, pageNum);

            int width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNum);
            int height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNum);

            // ARGB_8888 - best quality, high memory usage, higher possibility of OutOfMemoryError
            // RGB_565 - little worse quality, twice less memory usage
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.RGB_565);
            pdfiumCore.renderPageBitmap(pdfDocument, bitmap, pageNum, 0, 0,
                    width, height);
            //if you need to render annotations and form fields, you can use
            //the same method above adding 'true' as last param

            iv.setImageBitmap(bitmap);

            printInfo(pdfiumCore, pdfDocument);

            pdfiumCore.closeDocument(pdfDocument); // important!
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printInfo(PdfiumCore core, PdfDocument doc) {
        PdfDocument.Meta meta = core.getDocumentMeta(doc);
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(core.getTableOfContents(doc), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e("打开pdf", String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int write_permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (write_permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            //MxHelper.FileListUpData();
        }
        int read_permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (read_permission != PackageManager.PERMISSION_GRANTED) {

            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            //MxHelper.FileListUpData();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.fromParts("package", this.getPackageName(), null));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
            }
        }


        Button btnOpenCAD = (Button)findViewById(R.id.opencad);
        btnOpenCAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MxFunction.loadNativeLibraries();
                //MxFunction.initWorkDir(MxCADAppActivity.getDefaultWorkDir());
                // MxFunction.startSearchDwgFileTask(true,"t1");
                //MxFunction.startSearchAllDwgFilTask(true);
                //String sBufFile = MxFunction.getBufferFileName("xxxx.dwg");

                Intent myIntent = new Intent(getApplicationContext(), MxCADAppActivity.class);

                if(true)
                {
                    String dirMxDraw = getFilesDir().getAbsolutePath();
                    myIntent.putExtra("file",dirMxDraw +  "/sample.dwg");
                }
                startActivityForResult(myIntent, 0);
            }
        });


    }

}
