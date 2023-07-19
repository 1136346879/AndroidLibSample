/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.mxdrawlibtest.cpp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaCodec;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MxDraw.McDbArc;
import com.MxDraw.McDbAttribute;
import com.MxDraw.McDbBlockReference;
import com.MxDraw.McDbBlockTable;
import com.MxDraw.McDbBlockTableRecord;
import com.MxDraw.McDbCircle;
import com.MxDraw.McDbCurve;
import com.MxDraw.McDbDatabase;
import com.MxDraw.McDbDictionary;
import com.MxDraw.McDbEllipse;
import com.MxDraw.McDbEntity;
import com.MxDraw.McDbLayerTable;
import com.MxDraw.McDbLayerTableRecord;
import com.MxDraw.McDbLine;
import com.MxDraw.McDbMText;
import com.MxDraw.McDbMxImageMark;
import com.MxDraw.McDbObject;
import com.MxDraw.McDbPoint;
import com.MxDraw.McDbPolyline;
import com.MxDraw.McDbRasterImage;
import com.MxDraw.McDbSpline;
import com.MxDraw.McDbText;
import com.MxDraw.McDbTextStyleTable;
import com.MxDraw.McDbTextStyleTableRecord;
import com.MxDraw.McDbXrecord;
import com.MxDraw.McGeMatrix3d;
import com.MxDraw.McGePoint3d;
import com.MxDraw.McGeVector3d;
import com.MxDraw.MrxDbgSelSet;
import com.MxDraw.MrxDbgUiPrPoint;
import com.MxDraw.MrxDbgUtils;
import com.MxDraw.MxDrawActivity;
import com.MxDraw.MxDrawDragEntity;
import com.MxDraw.MxDrawHandle;
import com.MxDraw.MxDrawWorldDraw;
import com.MxDraw.MxFunction;
import com.MxDraw.MxJNI;
import com.MxDraw.MxLibDraw;
import com.MxDraw.MxModifyTheColor;
import com.MxDraw.MxResbuf;
import com.MxDraw.MxTestJava;

import org.cocos2dx.lib.Cocos2dxEditBox;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxRenderer;
import org.cocos2dx.lib.ResizeLayout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MxCADAppActivity extends MxDrawActivity {
    //private MxJNIEventInstance m_mxJniInstance = null;
    private String  m_sFastDrawJsonFile;
    private Timer mUpdateTimer = new Timer();
    public int iAngle = 0;

    public  void FasetDraw(String sJsonFile)
    {
        m_sFastDrawJsonFile = sJsonFile;
        MxFunction.startIdleTime();
    }

    public int iTest = 0;
    // 界面上的测试按钮
    public void TestMxDraw()
    {
        //MxDrawTmpTest.TestSelect();
        //MxFunction.zoomAll();

        int[] color = MxFunction.getViewColor();

        /*
        {
            long lId = MxFunction.getCurrentDatabase().getLayerTable().getAt("0");
            McDbLayerTableRecord layRec = new McDbLayerTableRecord(lId);
            layRec.setColor(233, 255, 55);
        }

        {
            long lId = MxFunction.getCurrentDatabase().getLayerTable().getAt("0");
            McDbLayerTableRecord layRec = new McDbLayerTableRecord(lId);
            int[] ret = layRec.getColor();
            Log.d("Test","Test");
        }
       */
        /*
        if(iTest == 0){
            MxFunction.zoomRotation(45.0 / 180.0  * 3.14159265 );
            iTest++;
        }
        else{
            MxFunction.zoomRotation(-MxFunction.getViewAngle());
            iTest=0;
        }
        */
        //Intent intent = new Intent(MxCADAppActivity.this,Test_Activity.class);
        //startActivity(intent);

        //MxDrawTmpTest.TestGetViewDocBound();
        //MxDrawTmpTest.TestMxView(this);
        //boolean isRun = MxFunction.isRuningCommand();
        //String sNam = MxFunction.getRuningCommandName();
        //Log.e("Name",sNam);
        //MxFunction.clearPreviousInputPoint();
        //MxFunction.setPreviousInputPoint(10,10);

        //MxDrawTmpTest.SetPointMode();
        //MxDrawTmpTest.TestDrawImage();
        //MxDrawTmpTest.TestSave();
        //MxDrawTmpTest.DrawText();
        //MxDrawTmpTest.TestSaveBuffer();
        //MxDrawTmpTest.GetAllEntity();

        //FasetDraw("")

        //String sJsonFile = MxFunction.getWorkDir() + "/draw.json";
        //FasetDraw(sJsonFile);
        //MxDrawTmpTest.TestSave();
        //MxDrawTmpTest.GetAllEntity();
        //MxDrawTmpTest.DrawFixWidthLine(this);

        //MxDrawTmpTest.TestDraw3DPolyline();

        //MxDrawTmpTest.TestSave();

        //MxDrawTmpTest.TestGetMxHandle(this);

        // this.runOnGLThreadForDraw(new Runnable() {
        //     @Override
        //      public void run() {
        //
        //             }
        //      });

        //if(MxDrawActivity.isCanCallDraw() )
        //{
        //}

        //MxLibDraw.setLineWidth(200);
        //MxLibDraw.drawCircle(0,0,100);
        //MxLibDraw.drawMText(0,0,"aaa\\Pbbb",100);
/*
        this.runOnGLThread(new Runnable() {
            @Override
            public void run() {
              MxFunction.zoomScale(0.5);

            }
        });*/


        //MxDrawTmpTest.TestMxView2();


        //MxDrawTmpTest.Test(this);
       // MxFunction.tempTest("/storage/emulated/0/mxcadview/11.mvg");
        // MxFunction.sendStringToExecute("Mx_SavePdfFile");
        //MxDrawTmpTest.TestModifyBlockName();
        // MxDrawTmpTest.TestDeleteCurrentSelectEntity();
    }

    // 右上角弹出菜单，的测试命令。,下面代码是命令事件内执行的。
    public  void DoTest()
    {

        //MxDrawTmpTest.Test(this);
        //MxDrawTmpTest.TestIntersectWith();
        //MxDrawTmpTest.GetBlockRefAttrib();
        //MxDrawTmpTest.ExplodeTest(this);
        //MxDrawTmpTest.Test(this);
        //MxDrawTmpTest.TestDrawLine();
        //MxDrawTmpTest.DrawImageMark(this);
        // MxDrawTmpTest.TestModify(this);
        //MxDrawTmpTest.TestIntersectWith();
        //MxDrawTmpTest.TestSetMxHandle(this);
        //MxDrawTmpTest.TestGetMxHandle(this);

        //long lId = MxLibDraw.drawLine(0,0,100,100);
        // MxDrawHandle hand = new MxDrawHandle();
        //hand.set(lId,10);

        //MxFunction.startIdleTime();

        //this.runOnGLThreadForDraw();
        //MxDrawTmpTest.TestRectSelect(this);
        //MxDrawTmpTest.TestDrawFixedScreenSizeText();
        //MxDrawTmpTest.TestSetUcs();
        //MxDrawTmpTest.TestEntityOCS(this);

        //MxDrawTmpTest.TestModifyLayerName();
        //MxDrawTmpTest.TestInserBlock();
        //MxDrawTmpTest.TestDeleteEntity(this);
        //MxDrawTmpTest.TestSetLineTypeScale();

        //MxDrawTmpTest.TestGetDimText();
        //MxDrawTmpTest.TestInserBlock();
        MxDrawTmpTest.TestDrawPoint();
    }

    @Override
    public  boolean hideMxCAD()
    {
        Log.d("hideMxCAD","hideMxCAD");
        MxFunction.sendStringToExecute("Mx_StartPage");
        return true;
    }

    @Override
    public  boolean showMxCAD()
    {
        Log.d("showMxCAD","showMxCAD");
        return true;
    }


    @Override
    public void fastDrawComplete(boolean isSucces,String sJsonFile,String sJsonResult) {

        Log.d("fastDraw",sJsonFile);


    }

    @Override
    public void idleTimeCall()
    {


        if(m_sFastDrawJsonFile.isEmpty())
            return;

        if(!MxFunction.isCanFastDraw() )
            return;

        MxFunction.stopIdleTime();
        MxFunction.fastDraw(m_sFastDrawJsonFile);
        m_sFastDrawJsonFile = "";
        
    }

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        m_isLoadAndroidLayoutUi = true;

        // 显示标题栏,把下面注释打开
        this.setEnableVirtualButton(false);


        super.onCreate(savedInstanceState);

        copyeSampleFile();
        copyAssetsFileToSdcard("fm.dwg");
        copyAssetsFileToSdcard("test.dwg");
        copyAssetsFileToSdcard("draw.json");

        Bundle extras = getIntent().getExtras();

        if(extras  != null)
        {
            String mFile  = extras.getString("file");
            if(!mFile.isEmpty())
            {
               //MxFunction.openFile(mFile);
               // MxFunction.openFileEx( mFile,
                //      ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadxData | ReadContent.kReadNamedObjectsDictionary | ReadContent.kReadXrecord );
                //MxFunction.asyncOpenFile(mFile);
                MxFunction.asyncOpenBufferFile(mFile);

            }
        }

        /*
        boolean isRegistMxEvent = false;
        //boolean isRegistMxEvent = true;
        if(isRegistMxEvent)
        {
            m_mxJniInstance = new MxJNIEventInstance();
            MxJNI.Init(m_mxJniInstance);
        }

         */


    }
    @Override
    public void mcrxEntryPoint(int iCode)
    {
        super.mcrxEntryPoint(iCode);
        if(iCode == kInitAppMsg)
        {
            MxFunction.setShowFileBrowse(true);

            //MxFunction.clearDefaultFavorite();
            //MxFunction.setReadFileContent(ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadXrecord | ReadContent.kReadNamedObjectsDictionary );
            //MxFunction.setReadFileContent(ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadNamedObjectsDictionary );

            //MxFunction.setGridColor(255,0,0,0.5);
            //MxFunction.setGridWidth(2);

            // MxFunction.setShow(MxFunction.ShowType.kFavorite,false);
            // MxFunction.setShow(MxFunction.ShowType.kRecently,false);
            //MxFunction.setAsyncMode(true);

            //MxFunction.enablePopToolbar(true);
            //MxFunction.setArcDensityAngle(45);
            //MxFunction.setArcDensityAngle(1);
            MxFunction.keepSelected(true);
            //MxFunction.setAutoSaveFile(false);
            //MxFunction.setOsnapZValue(true);

            //MxFunction.setSysVarLong("OSMODE");
            //MxFunction.setSelectMode(2);

            //MxFunction.setDefaultText("tssdeng.shx");
            //MxFunction.setDisableBuffer(true);
            //MxFunction.setSelectColor(255,0,0);

            //调用openFile函数，是否自动调用ZoomAll
            //MxFunction.setAutoZoomAll(true);


            MxFunction.setAnimationInterval(3.0f / 60.0f);
            MxFunction.enableIdleStopRefresh(true);



            copyShxFile("aaa.shx");



            MxFunction.setShowUpToolBar(true);
            MxFunction.setShowDownToolBar(true);
            MxFunction.setShowReturnButton(true);
            MxFunction.enableSelect(true);
            MxFunction.enableGridEdit(true);
            //MxFunction.setShowTip(false);

            // 设置成false，选择中对像，删除按就不出来了。
            MxFunction.enablePopToolbar(true);

            // 设置true,执行命令时，右上角的X按钮就不出来了
            MxFunction.disabledCommandToolbar(false);

            boolean isUeCustomUI = false;
            isUeCustomUI = false;
            if(isUeCustomUI){
                MxFunction.addSearchPaths("mxcustomui");
                MxFunction.setToolFile("mxcustomtool.json");
                MxFunction.setMenuFile("mxcustommenu.json");
            }
            else{
                MxFunction.setToolFile("mxtool.json");
                MxFunction.setMenuFile("mxmenu.json");
            }




            MxFunction.setReadFileContent(ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadxData | ReadContent.kReadNamedObjectsDictionary | ReadContent.kReadXrecord );

            // 注意，在读取扩展数据，或字典时，必须调用下面函数，指定要加的扩展数据应用名，或字典名字。
            // 如下，调用，加载Ports，PortLinks名的扩展数据。
            //MxFunction.addSupportAppName("Ports");
            //MxFunction.addSupportAppName("PortLinks");

            // 或如下调用，加载所有名称数据。
            MxFunction.addSupportAppName("*");
            MxFunction.enableUndo();
            MxFunction.setShowTitle(true);

            MxFunction.setSaveZValue(true);


			MxFunction.setSaveDwgVersion(MxFunction.DwgVersion.kCAD2007);

			MxFunction.addDefaultFavorite("MyFavorite",MxFunction.getWorkDir() + "/fonts");

            boolean is64 = MxFunction.is64Bit();

            MxFunction.setPointSize(0.1);

            //


            MxFunction.setHighPrecisionSelect(true);
            MxFunction.setTouchReleaseSelect(true);
            MxFunction.setNewModeSavePreview(true);

            MxFunction.enableThreadSafety();
            MxFunction.enablePartialUpdate();

            // 启动图块的Instance绘制模式，
            MxFunction.enableBlockInstance(true);
            MxFunction.setMaxDrawTextNum(250000);
            //MxFunction.setHighPrecisionArc(true);
            //MxFunction.setViewColor(255,255,255);
            //MxFunction.setBownerMode(true);

            // test
            //MxFunction.setUseDwgFilePrvViewBmp(true);
            //MxFunction.setPrvViewPngAtAbsolutePosition(true);

            MxFunction.setInputOffsetDistance(14) ;
            MxFunction.setDefaultUserInputControls(3);



            boolean isTTF = false;
            //boolean isTTF = true;

            boolean isEn = false;
            //boolean isEn = true;

            if(isEn){
                MxFunction.setLanguage(MxFunction.MxSystemLanager.kEn);
                isTTF = true;
            }

            if(isTTF)
            {
                MxFunction.enableTTF(true);
                MxFunction.useUtf8StringFormat(true);
                MxFunction.useTTFReplaceShx(true);
                MxFunction.setSystemDefaultCodePage(MxFunction.DwgCodePage.CP_ANSI_949);
            }
            MxFunction.setAutoRegen(true);


            MxFunction.setShowDrawParamToolBar(true);

            //MxFunction.setExDataStringUseUtf8(true);
            MxFunction.initReserve(this);

            // 0.2mm.
            //MxFunction.setUserInputArrowLineWidth(0.2);
            //MxFunction.setUserInputCircleType(2);
            MxFunction.enableOpenGLBuffer(true);
            //MxFunction.enableDepthTest(true);
            
        }
        else if(iCode == kStartScene )
        {
            //MxFunction.setCursorRectWidth(6);
            Log.e("mxdraw","Start Scene");
        }
    }


    public void DynGetPoint()
    {
        MxFunction.setSysVarLong("OSMODE",0x3FFF);
        MxFunction.disabledCommandToolbar(false);
        // 交互取第一个点.
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setOffsetInputPostion(true);
        MxDrawDragEntity drawdata = getPoint.initUserDraw("mydyngetpoing");

        while(true)
        {
            if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
            {
               break;
            }
            McGePoint3d pt = getPoint.value();

            String sT;
            sT = String.format("pt:%f,%f,%f",pt.x,pt.y,pt.z);

            Log.e("xxxx",sT);
        }

        MxFunction.disabledCommandToolbar(true);

    }




    public void DoPolyline()
    {
        //this.runOnGLThread();
        // 交互取第一个点.
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setOffsetInputPostion(true);
        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();
        String sT;
        sT = String.format("pt:%f,%f,%f",pt.x,pt.y,pt.z);

        // 交互取第二个点
        MrxDbgUiPrPoint getPoint2 = new MrxDbgUiPrPoint();

        getPoint2.setBasePt(pt);
        getPoint2.setUseBasePt(true);
        getPoint2.setOffsetInputPostion(true);

        // 初始化动态绘制，在交到过程中，会不停调用 dynWorldDraw函数，实现动态画图。
        MxDrawDragEntity drawdata = getPoint2.initUserDraw("mydyngetpoing");

        // 设置动态绘制数据。
        drawdata.SetString("Prv","Len");
        drawdata.SetPoint("pt1",pt);

        while(true)
        {
             // 开始取第二个点.
            if(getPoint2.go() != MrxDbgUiPrPoint.Status.kOk)
             {
                return;
             }

             McGePoint3d pt2 = getPoint2.value();



            // 把取到的点，用来画直线。
            MxLibDraw.drawLine(pt.x,pt.y,pt2.x,pt2.y);

            pt.x = pt2.x;
            pt.y = pt2.y;
            getPoint2.setBasePt(pt);
            drawdata.SetPoint("pt1",pt);
        }


    }

    public static String getDefaultWorkDir(){
        return Environment.getExternalStorageDirectory() + "/"+ "TestMxLib";
    }

    public MxCADAppActivity() {

        initWorkDir(getDefaultWorkDir() );
        setPackageName("com.MxDraw");
        //initUserInfo("xxxx","yyyy");
    }

    protected  boolean m_isLoadAndroidLayoutUi = false;



    @Override
    public void onKeyReleased(int iKeyCode)
    {
        if(iKeyCode == 6)
        {
            MxFunction.sendStringToExecute("Mx_StartPage");
        }
    }
    @Override
    public boolean createInterfaceLayout()
    {

        if(!m_isLoadAndroidLayoutUi)
            return  false;

        setContentView(R.layout.cadglview);

        ResizeLayout  mFrameLayout = (ResizeLayout)this.findViewById(R.id.my_frame);

        Cocos2dxGLSurfaceView mGLSurfaceView = (Cocos2dxGLSurfaceView)this.findViewById(R.id.view_cad);
        Cocos2dxEditBox edittext =  (Cocos2dxEditBox)this.findViewById(R.id.my_edittext);
        initInterfaceLayout(mFrameLayout,edittext,mGLSurfaceView);

        {

            Button btnviewcolor = (Button) findViewById(R.id.Return);

            btnviewcolor.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    MxFunction.sendStringToExecute("Mx_StartPage");

                }
            });
        }

        {
            Button btnviewcolor = (Button) findViewById(R.id.zoomE);

            btnviewcolor.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    String sPreview = MxFunction.getPreviewFile("sample.dwg");


                    MxFunction.doCommand(6);

                }
            });
        }

        {
            Button btn = (Button) findViewById(R.id.userSelect);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    MxFunction.doThreadCommand(7);

                }
            });
        }

        {
            Button btn = (Button) findViewById(R.id.undo);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                   MxFunction.sendStringToExecute("Mx_Undo");
                   //MxFunction.sendStringToExecute("Mx_Color");

                }
            });
        }

        {
            Button btn = (Button) findViewById(R.id.getAllLayer);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    MxFunction.doCommand(11);

                }
            });
        }

        {
            Button btn = (Button) findViewById(R.id.DrawText);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                            MxFunction.doThreadCommand(12);

                }
            });
        }
        {
            Button btn = (Button) findViewById(R.id.Test);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {



                    // test;
                    MxFunction.doCommand(21);

                }
            });
        }


        {
            Button btn = (Button) findViewById(R.id.FreeDraw);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {




                    ;
                   double dW =  MxFunction.viewLongToDoc(7);

                    MxFunction.setSysVarDouble("CPLINEWIDTH",dW );
                    MxFunction.setSysVarLong("CECOLOR",0xFF0012);

                   MxFunction.sendStringToExecuteEx("Mx_DrawFree",true);
                    //MxFunction.sendStringToExecuteEx("Mx_Text",true);

                }
            });
        }
        
        return true;
    }

    @Override
    public  void commandEnded(String sCmd)
    {
        if(sCmd.equals("Mx_DrawFree")
                || sCmd.equals("Mx_InsertText")
                )
        {
            long[] aryId = MxFunction.getEntitysLastCmd();
            if(aryId == null)
                return;

            for(int j = 0; j < aryId.length;j++)
            {
                String sName =  MxFunction.getTypeName(aryId[j]);

                if(sName.equals("McDbPolyline"))
                {
                    McDbPolyline pl = new McDbPolyline(aryId[j]);
                    double dA = pl.getArea();

                    String sA;
                    sA = String.format("Area:%f",dA);

                    Log.e("McDbPolyline Area:", sA);

                    for (int i = 0; i < pl.numVerts(); i++) {
                        McGePoint3d pt = pl.getPointAt(i);
                        double dBulge = pl.getBulgeAt(i);

                        String sT2;
                        sT2 = String.format("pt:%f,%f,%f,dBulge:%f", pt.x, pt.y, pt.z,dBulge);

                        Log.e("McDbPolyline Point:", sT2);

                        if(dBulge > 0.001)
                        {
                            McGePoint3d pt2;

                            if(i ==  pl.numVerts() - 1)
                            {
                                pt2 = pl.getPointAt(0);
                            }
                            else
                            {
                                pt2 = pl.getPointAt(i + 1);
                            }

                            double[] arc = MxFunction.calcArc(pt.x,pt.y,pt2.x,pt2.y,dBulge);
                            if(arc != null)
                            {
                                String sTem = String.format("cen:%f,%f,dR:%f,dS:%f,dE:%f", arc[0],arc[1],arc[2],arc[3],arc[4]);

                                Log.e("Arc:", sTem);
                            }
                        }
                    }
                }
                else   if(sName.equals("McDbText"))
                {
                    McDbText txt = new McDbText(aryId[j]);
                    String sTem = txt.textString();
                    Log.e("txt:", sTem);
                }
            }
        }
    }


    @Override
    public void savePreviewFileComplete(boolean isOK)
    {
        Log.e("savePreviewFileComplete","");
    }


    @Override
    public void openComplete(boolean isOpenSucces) {

        //MxDrawTmpTest.TestTzEntity();
        //MxFunction.setSysVarLong("PDMODE",3);

                /*
        String sT;
        sT = String.format("openComplete:%d",isOpenSucces ? 1 : 0);

        Log.e("openComplete",sT);
*/

        //MrxDbgSelSet ss = new MrxDbgSelSet();
        //MxResbuf filter = new MxResbuf();
       // filter.addString("MxImageMark",5020);

        //ss.allSelect(filter);
        //for(int i = 0; i <ss.size();i++)
        //{
       //     MxFunction.delSelect(ss.at(i));
        //}

        /*
        double ret[] =MxFunction.getMcDbDatabaseBound();
        if(ret != null)
        {
                double dLBx = ret[0];
                double dLBy = ret[1];
                double dRTx = ret[2];
                double dRTy = ret[3];


                sT = String.format("dLB:%f,%f,dRT:%f,%f",dLBx,dLBy,dRTx,dRTy);

                Log.e("getMcDbDatabaseBound",sT);
        }
        else
        {
            Log.e("getMcDbDatabaseBound","error");
        }

        // 得到当前绘图颜色RGB
        int[] ccolor = MxFunction.cecolor();

        sT = String.format("dLB:%d,%d,%d",ccolor[0],ccolor[1],ccolor[2]);

        Log.e("cecolor",sT);
        */

        //MxFunction.doCommand(7);

        //McDbDatabase data = MxFunction.getCurrentDatabase();

       // MxFunction.zoomCenter(10,10);
        //MxFunction.writeFile()
    }

    @Override
    public void initComplete()
    {
        Log.e("initComplete","initComplete");
    }

    @Override
    public  boolean dynWorldDraw(MxDrawWorldDraw draw , MxDrawDragEntity dragData)
    {

        if(dragData.GetGuid().equals("mydyndraw") )
        {
            // 交互画直线的动态绘制。
            MxDrawTmpTest.DynDrawLine_dynWorldDraw(draw,dragData);

            return true;
        }
        else if(dragData.GetGuid().equals("dynRect")){
            MxDrawTmpTest.TestRectSelect_dynWorldDraw(draw,dragData);
            return true;
        }
        else if(dragData.GetGuid().equals("mydyngetpoing") )
        {
            McGePoint3d currentPoint =  dragData.GetDragCurrentPoint();
            String sT;
            sT = String.format("pt:%f,%f,%f",currentPoint.x,currentPoint.y,currentPoint.z);
            Log.e("currentPoint",sT);
        }
        return false;
    }

    @Override
    public void objectGripEdit(long lId,int lGripIndex)
    {
        String sT;
        sT = String.format(" lId:%d,%d",lId,lGripIndex);
        Log.e("objectGripEdit",sT);
    }

    @Override
    public void selectModified(long lId)
    {

        String sT;
        sT = String.format(" lId:%d",lId);
        Log.e("selectModified",sT);
        /*
        if(lId != 0)
        {

            String sT;
            sT = String.format(" lId:%d",lId);
            Log.e("selectModified",sT);


            McDbEntity ent = new McDbEntity (lId);

            McGePoint3d ptMin = new McGePoint3d();
            McGePoint3d ptMax = new McGePoint3d();
            if(ent.getGeomExtents(ptMin,ptMax) )
            {
                String sT1;
                sT1 = String.format("min:%f,%f,max:%f,%f",ptMin.x,ptMin.y,ptMax.x,ptMax.y);
                Log.e("extents",sT1);
            }

            String sXdata = MxFunction.getxDataString(lId,"PE_URL");


            // 得到对象的层名.
            Log.e("LayerName",ent.layerName());

            String sName =  MxFunction.getTypeName(lId);

            if(sName.equals("McDbLine"))
            {
                McDbLine line = new McDbLine(lId);

                McGePoint3d sPt = line.getStartPoint();
                McGePoint3d ePt = line.getEndPoint();

                String sT1;
                sT1 = String.format("sPt:%f,%f,%f,ePt:%f,%f,%f",sPt.x,sPt.y,sPt.z,ePt.x,ePt.y,ePt.z);

               // sPt.x = 0;
                //line.setStartPoint(sPt);

                Log.e("Linedata",sT1);
            }
            else if(sName.equals("McDbSpline"))
            {
                McDbSpline spline = new McDbSpline(lId);
                int[] degree = new int[1];
                double[] fitTolerance = new double[1];
                McGePoint3d[] aryFit = spline.getFitData(degree,fitTolerance);

                if(aryFit != null)
                {
                    for(int i = 0; i < aryFit.length;i++)
                    {
                        String sT1;
                        sT1 = String.format("%f,%f,%f",aryFit[i].x,aryFit[i].y,aryFit[i].z);

                        Log.e("McDbSpline aryFit",sT1);
                    }

                }


                boolean[] rational = new boolean[1];
                boolean[] closed = new boolean[1];
                boolean[] periodic = new boolean[1];
                List<Double> knots = new ArrayList<Double>();
                List<Double> weights = new ArrayList<Double>();
                double[] controlPtTol = new double[1];
                double[] knotTol = new double[1];

                McGePoint3d[] aryControlPt =  spline.getNurbsData(degree,rational,closed,periodic,knots,weights,controlPtTol,knotTol);

                if(aryControlPt != null)
                {
                    for(int i = 0; i < aryControlPt.length;i++)
                    {
                        String sT1;
                        sT1 = String.format("%f,%f,%f",aryControlPt[i].x,aryControlPt[i].y,aryControlPt[i].z);

                        Log.e("McDbSpline aryControlPt",sT1);
                    }

                }

                //spline.setFitData(aryFit,degree[0],fitTolerance[0]);
            }
            if(sName.equals("McDbArc"))
            {
                McDbArc arc = new McDbArc(lId);

                McGePoint3d cen = arc.getCenter();
                double dR = arc.getRadius();
                double dSA = arc.getStartAngle();
                double dEA = arc.getEndAngle();

                String sT1;
                sT1 = String.format("cen:%f,%f,%f,dR:%f,dSA:%f,dEA:%f",cen.x,cen.y,cen.z,
                        dR,dSA,dEA);

                Log.e("McDbArc",sT1);

                // 得到圆弧的开始点，和结束点.
                McGePoint3d sPt = arc.getStartPoint();
                McGePoint3d ePt = arc.getEndPoint();

                String sT2;
                sT2 = String.format("sPt:%f,%f,%f,ePt:%f,%f,%f",sPt.x,sPt.y,sPt.z,ePt.x,ePt.y,ePt.z);

                Log.e("McDbArc points:",sT2);

            }
            else if(sName.equals("McDbBlockReference"))
            {
                McDbBlockReference blkRef = new McDbBlockReference(lId);
                McDbBlockTableRecord blkRec = new McDbBlockTableRecord( blkRef.blockTableRecord());
                Log.e("BlkName:",blkRec.getName());

                long[] allAtt = blkRef.getAllAttribute();
                if(allAtt != null) {
                    for (int i = 0; i < allAtt.length; i++) {
                        McDbAttribute att = new McDbAttribute(allAtt[i]);
                        Log.e("tagConst:", att.tagConst());
                        Log.e("textString:", att.textString());

                    }
                }

            }
            else if(sName.equals("McDbSpline"))
            {
                // 把样条线离线成一堆的点返回
                McDbCurve curve = new McDbCurve(lId);
               McGePoint3d[] ponts =  curve.getSamplePoints(0.1);

                if(ponts != null) {
                    for (int i = 0; i < ponts.length; i++) {
                        McGePoint3d pt = ponts[i];
                        String sT2;
                        sT2 = String.format("pt:%f,%f,%f", pt.x, pt.y, pt.z);

                        Log.e("McDbSpline pt:", sT2);
                    }
                }

            }
            else if(sName.equals("McDbPolyline"))
            {
                McDbPolyline pl = new McDbPolyline(lId);
                double dA = pl.getArea();

                String sA;
                sA = String.format("Area:%f",dA);

                Log.e("McDbPolyline Area:", sA);

                for (int i = 0; i < pl.numVerts(); i++) {
                    McGePoint3d pt = pl.getPointAt(i);
                    double dBulge = pl.getBulgeAt(i);

                    String sT2;
                    sT2 = String.format("pt:%f,%f,%f,dBulge:%f", pt.x, pt.y, pt.z,dBulge);

                    Log.e("McDbPolyline Point:", sT2);

                    if(dBulge > 0.001)
                    {
                        McGePoint3d pt2;

                        if(i ==  pl.numVerts() - 1)
                        {
                            pt2 = pl.getPointAt(0);
                        }
                        else
                        {
                            pt2 = pl.getPointAt(i + 1);
                        }

                        double[] arc = MxFunction.calcArc(pt.x,pt.y,pt2.x,pt2.y,dBulge);
                        if(arc != null)
                        {
                            String sTem = String.format("cen:%f,%f,dR:%f,dS:%f,dE:%f", arc[0],arc[1],arc[2],arc[3],arc[4]);

                            Log.e("Arc:", sTem);
                        }
                    }
                }
            }
            else if(sName.equals("McDbPoint"))
            {
                McDbPoint pt = new McDbPoint(lId);
                McGePoint3d ptv = pt.position();

                String sTem = String.format("pt:%f,%f,%f", ptv.x,ptv.y,ptv.z);

                Log.e("Point:", sTem);
            }

        }
        */

    }


    @Override
    public void commandEvent(int iCommand)
    {
        if(iCommand == 1) {
            //MxFunction.zoomAll();
            //MxFunction.sendStringToExecute("MT_TestTip");\



            String sFileName = MxFunction.currentFileName();

            String sFileName2 = MxFunction.getWorkDir() + "/empty.dwg";
            MxFunction.writeFile(sFileName);
        }
        else if(iCommand == 2)
        {
            String sFileName = MxFunction.getWorkDir() + "/总图.dwg";
            MxFunction.openFileEx(sFileName,ReadContent.kReadxData);
        }
        else if(iCommand == 3)
        {
            MxFunction.openFile("");
        }
        else if(iCommand == 4)
        {
            MxDrawTmpTest.GetAllEntity();
        }
        else if(iCommand == 5)
        {
            //long lImageId = MxFunction.drawImage("start.png",100,100,1);

           // MxFunction.drawImageMarkEx("location.png",100,100,5,MxFunction.ImageAttachment.kMiddleCenter);3772736.368664,-4872358.583946
            MxFunction.openCurrentLayer();
            long lId = MxFunction.drawImageMarkEx("location2.png",412613.029029, 570249.878512,0.5,MxFunction.ImageAttachment.kBottomCenter);
            McDbMxImageMark image = new McDbMxImageMark(lId);
            image.setAngel(80 * 3.14159265 / 180.0);


            //MxLibDraw.drawLine(3772736.368664,-4872358.583946,3772736.368664+2000,-4872358.583946 + 200);
           // MxFunction.drawImageMarkEx("tag.png",250488.990947,3375025.060180,0.5,MxFunction.ImageAttachment.kBottomCenter);
            //MxLibDraw.drawLine(250488.990947,3375025.060180,3476025.060180,3478025.060180);


          //  MxFunction.zoomAll();
        }
        else if(iCommand == 6)
        {

            MxFunction.zoomAll();


        }
        else if(iCommand == 7)
        {

            MrxDbgSelSet ss = new MrxDbgSelSet();

            // 与用户交到在图上选择对象。
            ss.userSelect();

            String sT;
            sT = String.format("size:%d",ss.size());

            // 提示选择的对象.
            Log.e("userSelect",sT);

            MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
            getPoint.setMessage("Test:");
            if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
            {
                return;
            }

        }
        else  if(iCommand == 8)
        {
            MxFunction.openFile("");


            // 设置画图颜色.
            long[] rgb = new long[3];
            rgb[0] = 255;
            rgb[1] = 0;
            rgb[2] = 0;

            MxLibDraw.setDrawColor(rgb);

            MxLibDraw.setLineWidth(10);

            MxLibDraw.addLayer("TestLayer");
            MxLibDraw.setLayerName("TestLayer");

            MxLibDraw.drawLine(10,10,200,300);

            MxLibDraw.addLinetype("MyLine","20,-10",1);

            MxLibDraw.setLineType("MyLine");
            MxLibDraw.setLineWidth(5);

            long lId = MxLibDraw.drawLine(10,300,200,10);

            McDbObject obj = new McDbObject(lId);

            // 创建对象扩展字典
            obj.createExtensionDictionary();

            // 得到扩展字典
            McDbDictionary dict = new McDbDictionary(obj.extensionDictionary());

            // 向扩展字典中加入一个扩展记录.
            McDbXrecord xrec = new  McDbXrecord(dict.addRecord("MyData"));

            // 设置扩展记录数据。
            MxResbuf data = new MxResbuf();
            data.addLong(111);;
            data.addString("xxxxxxx");;
            xrec.setFromRbChain(data);




            // 测试求最近点函数。
            McDbCurve curve = new McDbCurve(lId);
            McGePoint3d pt = new McGePoint3d(100,100,0);
            McGePoint3d onPt =  curve.getClosestPointTo(pt);
            if(onPt != null)
            {
                // 得到最近点.
                String sT;
                sT = String.format("onPt:%f,%f,%f",onPt.x,onPt.y,onPt.z);

                Log.e("onPt",sT);
            }

            MxFunction.zoomAll();
        }
        else if(iCommand == 9)
        {
            // 交互绘直线.

            MxDrawTmpTest.DynDrawLine(this);
        }

        else if(iCommand == 10)
        {
            long lId = MrxDbgUtils.selectEnt("点击选择对象:");
            if(lId != 0)
            {
                //McDbEntity ent = new McDbEntity (lId);
                //long[] newIds = ent.explode();
                //MxFunction.erase(lId);




                String sT;
                sT = String.format("selectEnt lId:%d",lId);
                Log.e("selectEnt",sT);

                McDbEntity ent = new McDbEntity (lId);

              
                // 得到对象的层名.
                String sLayer = ent.layerName();
                Log.e("LayerName",sLayer);

               MxResbuf xdata = ent.xData("");
               if(xdata != null) {

                   long lCount = xdata.getCount();

                   xdata.print();
               }

               long lDictId = ent.extensionDictionary();
                printDictionary(lDictId);




               // final long lEraseId = lId;
              //  this.runOnGLThread(new Runnable() {
              //          @Override
                //        public void run() {
               //             MxFunction.deleteObject(lEraseId);
              //           }
               //     });


            }

        }
        else if(iCommand == 11)
        {
            long[] ids = MxFunction.getAllLayer();
            if(ids ==null)
                return;

            for(int i = 0; i < ids.length;i++)
            {
                McDbLayerTableRecord layer = new McDbLayerTableRecord(ids[i]);
                String sName = layer.getName();
                Log.e("LayerName:",sName);

                //layer.setIsOff(true);
            }
        }

        else if(iCommand == 12)
        {
            MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
            getPoint.setOffsetInputPostion(true);
            getPoint.setCursorType(MrxDbgUiPrPoint.CursorType.kDefaultCursor);
            getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
            getPoint.setMessage("点取文字插入点");
            if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
            {
                return;
            }

            McGePoint3d pt = getPoint.value();

            MxLibDraw.addTextStyle1("MyTextStyle","txt.shx","gbcbig.shx",0.7f);
            MxLibDraw.setTextStyle("MyTextStyle");


            MxLibDraw.addTextStyle1("MyTextStyle2","txt.shx","hztxt.shx",0.7f);


            double dH = MxFunction.viewLongToDoc((50));

            MxLibDraw.drawText(pt.x,pt.y,500,"测试Test");

            long lId = MxLibDraw.drawText(pt.x + 600,pt.y,dH,"测试Test2222");

            McDbText txt = new McDbText(lId);
            txt.setTextStyleName("MyTextStyle2");




        }

        else if(iCommand == 13)
        {
            MxFunction.openFile("");

           // MxLibDraw.setLineWidth((50);
            MxLibDraw.drawCircle(10,10,100);

            MxLibDraw.drawArc(10,10,200,0,45 * 3.14159265/ 180.0);
            MxFunction.zoomAll();
        }
        else if(iCommand == 14)
        {
            MxFunction.openFile("");
            MxLibDraw.pathMoveTo(10,10);
            MxLibDraw.pathLineTo(10,20);
            //MxLibDraw.pathLineToEx(20,20,2,1,0.1);
            MxLibDraw.pathLineTo(20,20);
            MxLibDraw.pathLineTo(20,10);

            MxLibDraw.drawPathToPolyline();
            MxFunction.zoomAll();
        }
        else if(iCommand == 15)
        {
            MxFunction.openFile("");
            MxLibDraw.pathMoveTo(10,10);
            MxLibDraw.pathLineTo(10,20);
            //MxLibDraw.pathLineToEx(20,20,2,1,0.1);
            MxLibDraw.pathLineTo(20,20);
            MxLibDraw.pathLineTo(20,10);

            MxLibDraw.drawPathToSpline();
            MxFunction.zoomAll();
        }
        else if(iCommand == 16)
        {
            printDictionary(MxFunction.getNamedObjectsDictionary());
        }
        else if(iCommand == 17)
        {
            MxFunction.newFile();

            Log.e("isModifyed",MxFunction.isModifyed() ? "Y" : "N");

            //String sFileName = MxFunction.getWorkDir() + "/tree.dwg";
            String sFileName = MxFunction.getWorkDir() + "/tk.dwg";
            String sBlkName = "tree";
            MxLibDraw.insertBlock(sFileName,sBlkName);

            // drawBlockReference(double dPosX, double dPosY, String pszBlkName, double dScale, double dAng);
            MxLibDraw.drawBlockReference(0,0,"ZZCSTK_A3标准图框",1,0);

            MxFunction.zoomAll();

            Log.e("isModifyed",MxFunction.isModifyed() ? "Y" : "N");
        }
        else if(iCommand == 18)
        {
            // long  drawEllipse(double dCenterX, double dCenterY, double dMajorAxisX, double dMajorAxisY, double dRadiusRatio);
            MxFunction.newFile();
            MxLibDraw.drawEllipse(0,0,100,100,0.5);

            MxLibDraw.drawEllipseArc(200,0,100,100,0.5,15 * 3.14159265 / 180,90 * 3.14159265 / 180);


            MxFunction.zoomAll();
        }

        else if(iCommand == 19)
        {
            // 得到所有图层名称
            McDbLayerTable layerTable =  MxFunction.getCurrentDatabase().getLayerTable();
            long[] allLayerId = layerTable.getAll();
            for(int i = 0; i < allLayerId.length;i++)
            {
                McDbLayerTableRecord laryRec = new McDbLayerTableRecord(allLayerId[i]);

                Log.e("LayerName:",laryRec.getName());


            }
        }
        else if(iCommand == 20)
        {
            // 得到所有文字样式名称
            McDbTextStyleTable txtstyleTable =  MxFunction.getCurrentDatabase().getTextstyle();
            long[] allId = txtstyleTable.getAll();
            for(int i = 0; i < allId.length;i++)
            {
                McDbTextStyleTableRecord textStyleRecord = new McDbTextStyleTableRecord(allId[i]);

                Log.e("textStyleRecord:",textStyleRecord.getName());

                Log.e("fileName:",textStyleRecord.fileName());
                Log.e("bigFontFileName:",textStyleRecord.bigFontFileName());

            }
        }
        else if(iCommand == 55)
        {
            Log.e("custom toolbar:","55");
        }
        else if(iCommand == 65)
        {
            Log.e("custom toolbar:","65");
        }
        else if(iCommand == 21)
        {
            TestMxDraw();
        }
        else if(iCommand == 22)
        {
            DynCreateBlock();
        }
        else if(iCommand == 25)
        {

            String mJiFanName = "add";
            MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
            getPoint.setMessage("点取插入点");

            if (getPoint.go() != MrxDbgUiPrPoint.Status.kOk) {
                return;
            }
            McGePoint3d pt = getPoint.value();

            String sFileName = String.format("%s/%s.dwg", MxFunction.getWorkDir(), mJiFanName);
            String sBlkName = String.format("机房_%s", mJiFanName);
            MxLibDraw.insertBlock(sFileName, "TempBlkName");

            long lId = MxLibDraw.drawBlockReference(pt.x, pt.y,sBlkName, 0.01, 0);

            if(lId != 0) {


                McDbBlockReference blkRef = (McDbBlockReference) MxFunction.objectIdToObject(lId);

                McGePoint3d pos = blkRef.position();
                pos.z = 100;
                blkRef.setPosition(pos);
            }

        }
        else if(iCommand == 26)
        {
            DoTest();
        }
        else if(iCommand == 27)
        {
            DoPolyline();
        }
        else if(iCommand == 30)
        {
            // 动画测试.
            DoAnimation();
        }
    }

    private  boolean m_isDrawDoAnimation = false;

    public  void DoAnimation(){
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点取插入点");

        getPoint.setOffsetInputPostion(true);
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheAll);
        if (getPoint.go() != MrxDbgUiPrPoint.Status.kOk ) {
            return;
        }

        final  McGePoint3d pt = getPoint.value();

        m_isDrawDoAnimation = true;
        this.runOnGLThread(new Runnable() {
            @Override
            public void run() {

                // 绘制一个图片。
                long lId = MxFunction.drawImageMarkEx("tag.png", pt.x, pt.y, 1.5, MxFunction.ImageAttachment.kBottomCenter);
                MxDrawHandle mxHandle = new MxDrawHandle();
                // 给该图片对象，设置一个句柄88,为了在时钟函数中，通过句柄找到该对象，然后修改它的值。
                mxHandle.set(lId, 88);
            }
        });

        // 开始一个时钟。
        mUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                OnEventHighFixedTimeRefresh(null);
            }
        }, 200, 1000);

    }

    public void OnEventHighFixedTimeRefresh(Object arg) {
        final MxDrawActivity myThis = this;
        this.runOnGLThreadForDraw(new Runnable() {
            @Override
            public void run() {
                if(!myThis.isCanCallDraw()){
                    return;
                }
                MxDrawHandle mxHandle = new MxDrawHandle();
                mxHandle.refresh();
                // 根据句柄得到对象。
                long lId = mxHandle.get(88);
                if (lId != 0) {

                    // 修改对象的角度，实现动画。
                    McDbMxImageMark image = new McDbMxImageMark(lId);
                    image.setAngel((iAngle) % 360 * 3.14159265 / 180.0);
                    MxFunction.upIdleRefreshTime();
                }
                iAngle += 10;
            }
        });
    }

    public void DynCreateBlock()
    {
        // 得到块表
        McDbBlockTable blkTab = MxFunction.getCurrentDatabase().getBlockTable();

        // 添加一个块表记录,块名为空，是个匿名块
        long lBlkRec = blkTab.add("");
        McDbBlockTableRecord blkRec = new McDbBlockTableRecord(lBlkRec);

        // 绘制直线
        long lLine = MxLibDraw.drawLine(100,100,200,200);
        long lCircle = MxLibDraw.drawCircle(100,100,30);

        // 把绘制直线加到块表记录中.
       long lNewId =  blkRec.addCloneEntity(lLine);
        blkRec.addCloneEntity(lCircle);

        // 删除之前临时画的对象。
        MxFunction.deleteObject(lLine);
        MxFunction.deleteObject(lCircle);

        // 绘制块引用，引用刚才做的匿名块
        long lIdBlkRef = MxLibDraw.drawBlockReference2(50,50,lBlkRec,1,0);
        McDbBlockReference blkRef = new McDbBlockReference(lIdBlkRef);
        McDbAttribute attrib =  blkRef.appendAttribute();
        McGePoint3d pt = new McGePoint3d(50,50,0);
        attrib.setPosition(pt);
        attrib.setAlignmentPoint(pt);
        attrib.setTextString("TestAttrib");
        attrib.setHeight(30);
        attrib.setInvisible(false);
        blkRef.assertWriteEnabled();


        MxFunction.zoomAll();
    }
    public void printDictionary(long lId)
    {
        if(lId==0)
            return;
        McDbDictionary dict = new McDbDictionary(lId);

        long[] all = dict.getAll();
        if(all == null)
            return;

        for(int i = 0; i <all.length;i++)
        {
            String sType = MxFunction.getTypeName(all[i]);
            String sName = dict.getName(all[i]);
            Log.e("Name",sName);

            if(sType.equals("McDbDictionary"))
            {
                printDictionary(all[i]);
            }
            else if(sType.equals("McDbXrecord"))
            {
                McDbXrecord xRec = new McDbXrecord(all[i]);
                MxResbuf data =  xRec.rbChain();
                if(data != null)
                    data.print();

            }
        }


    }

    @Override
    public  void willBeReturnStart()
    {
        if(!m_isDrawDoAnimation)
            return;
        MxDrawHandle mxHandle = new MxDrawHandle();
        mxHandle.refresh();
        long lId = mxHandle.get(88);
        if (lId != 0) {
            MxFunction.erase(lId);
        }
        mUpdateTimer.purge();
    }


    @Override
    public  boolean returnStart()
    {

        //Mx_StartPage命令触发.
        //MxFunction.newFile();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MxFunction.inValidPaint();
                finish();
            }
        });



        /*
        this.runOnGLThread(new Runnable() {
            @Override
            public void run() {
               //....
            }
        });
*/

        return true;
    }

    public static String decodeUnicode(String theString) {
        char aChar;
        char aChar2;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                aChar2 = theString.charAt(x++);
                if (aChar == 'U'
                        && aChar2 == '+'
                        )
                {
                    // Read the xxxx



                        int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                }

                else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
    @Override
    public  int touchesEvent(int iType,double dX,double dY)
    {

        double[] ret = MxFunction.docToView(dX,dY);
        String sK;
        sK = String.format("docToView:%f,%f",ret[0],ret[1]);
        Log.e("docToView",sK);

        //if(iType == 0)
        if(false)
        {
            long lImageId = MxFunction.drawImage("start.png",dX,dY,30);
            McDbRasterImage image = new McDbRasterImage(lImageId);
            double dAng = image.rotation();

            image.setRotation(15 * 3.14159265 / 180.0);

        }
        // 在点击事件上，得到点的对象。

        //if(iType == EventType.kLongPressed)
        if(false)
        {


            String sT;
            sT = String.format("touchesEvent:%f,%f",dX,dY);
            Log.e("touchesEvent",sT);


            //long lIdImage  = MxFunction.findEntAtPoint(dX,dY,"IMAGE");
            long lIdImage  = MxFunction.findEntAtPoint(dX,dY,"MxImageMark");
            if(lIdImage != 0)
            {
                String sGetVal = MxFunction.getxDataString(lIdImage,"MyData");

                Log.v("Find MxImage",decodeUnicode(sGetVal));

                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        new AlertDialog.Builder(MxCADAppActivity.this).setTitle("系统提示")//设置对话框标题

                                .setMessage("找到一个标记")//设置显示的内容

                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                        // TODO Auto-generated method stub



                                    }

                                }).show();//在按键响应事件中显示此对话框


                    }
                }
                );
            }
            else
            {
               // long lId = MxFunction.findEntAtPoint(dX,dY,"TEXT,MTEXT");

                //if(lId != 0)
                {


                    long lImageId = MxFunction.drawImage("start.png",dX,dY,30);

                    long lImageId2 = MxFunction.drawImage2("start.png",dX,dY,dX + 1000,dY + 3000);

                    MxFunction.setxDataString(lImageId,"MyData","TestVal中文测试111");

                    String sGetVal = MxFunction.getxDataString(lImageId,"MyData");


                    this.runOnUiThread(new Runnable() {
                                           @Override
                                           public void run() {


                                               new AlertDialog.Builder(MxCADAppActivity.this).setTitle("系统提示")//设置对话框标题

                                                       .setMessage("在图上绘了一个标记")//设置显示的内容

                                                       .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                                                           @Override

                                                           public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                                               // TODO Auto-generated method stub



                                                           }

                                                       }).show();//在按键响应事件中显示此对话框


                                           }
                                       }
                    );
                }
            }


        }
        return 0;
    }

    public boolean openCamera()
    {
        MyImagePicker.init(this);
        MyImagePicker.openCamera();
        return true;
    }

    public boolean openPhoto()
    {
        MyImagePicker.init(this);
        MyImagePicker.openPhoto();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        MyImagePicker.getInstance().onActivityResult(requestCode,resultCode,data);

    }


    @Override
    public  void displayOutOfRange()
    {
        //Log.d("Test", "displayOutOfRange: ");
    }

    @Override
    public void displayScaleChange()
    {

        ///Log.d("Test", "displayScaleChange");
    }

    @Override
    public void regenComplete()
    {

        Log.d("Test", "regenComplete");
    }

    @Override
    public void regenStart()
    {

        Log.d("Test", "regenStart");
    }

    @Override
    public String onCustomEvent(String sEvent, String sJsonParam){
        // ShowLayerPanel,HideLayerPanel
        String sTmp = new String(",");
        Log.d("onCustomEvent", sEvent + sTmp + sJsonParam);
        return "";
    }
}
