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
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SizeF;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MxDraw.McDb3DPolyline;
import com.MxDraw.McDbArc;
import com.MxDraw.McDbAttribute;
import com.MxDraw.McDbBlockReference;
import com.MxDraw.McDbBlockTable;
import com.MxDraw.McDbBlockTableRecord;
import com.MxDraw.McDbCircle;
import com.MxDraw.McDbCurve;
import com.MxDraw.McDbDatabase;
import com.MxDraw.McDbDictionary;
import com.MxDraw.McDbDimension;
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
import com.MxDraw.MxLibDraw;
import com.MxDraw.MxModifyTheColor;
import com.MxDraw.MxResbuf;
import com.MxDraw.MxView;

import org.cocos2dx.lib.Cocos2dxEditBox;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.ResizeLayout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MxDrawTmpTest {

    public static void TestMxView(MxCADAppActivity activity)
    {
        
        activity.initMxView();
        MxView mxview = activity.getMxView();

        String dirMxDraw = Environment.getExternalStorageDirectory() + "/"+ "TestMxLib";
        String sFile = dirMxDraw +  "/test.dwg";


        String sMd5 = mxview.getMd5(sFile);

        String sData = String.format("{\"md5\":\"%s\"}",sMd5);

        String sPostRet =mxview.post("http://192.168.2.9:5101/users/mxapp_oss_upfile_init_start",sData);

        Log.e("postRet",sPostRet);
        //mxview.uploadFile("http://192.168.2.9:5101/upfile", sFile,"test");

    }


    public static void TestDrawPoint()
    {

        McGeMatrix3d ucsToWcs = MxFunction.getUcsMatrix();

        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点位置");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();
        // 圆+
        MxFunction.setSysVarLong("PDMODE", 35);
        MxFunction.setSysVarDouble("PDSIZE", 5000);

        MxLibDraw.drawPoint(pt.x,pt.y);


    }

    public static void TestModifyBlockName(){
        long lBlkRecId = MxFunction.getCurrentDatabase().getBlockTable().getAt("Temp");
        if(lBlkRecId != 0){
            McDbBlockTableRecord blkRec = new McDbBlockTableRecord(lBlkRecId);
            blkRec.setName("NewName2");
            String sName = blkRec.getName();
            Log.e("sName",sName);

        }


    }

    public static void TestDeleteCurrentSelectEntity(){
        MrxDbgSelSet ss = new MrxDbgSelSet();

        // 得到当前选中的对象.
        ss.currentSelect();

        // 遍历选择集中的对象.
        for(int i = 0; i <ss.size();i++) {
            // 得到id.
            long lId = ss.at(i);
            MxFunction.deleteObject(lId);
        }
    }

    public static void TestDeleteEntity(MxCADAppActivity activity){
        final long lId = MrxDbgUtils.selectEnt("点击选择PL:");
        if (lId != 0) {


            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {

                    MxFunction.erase(lId);

                }
            });
        }

    }


    public static void TestModifyLayerName(){

        // 添加一个图层Test
        MxLibDraw.addLayer("Test");

        // 根据层名Test得到层记录对象。,如果Test不存在，layerRecord指向一个空对象ID.
        McDbLayerTableRecord layerRecord = new McDbLayerTableRecord("Test");

        // 修改层名为Test2
        layerRecord.setName("Test2");

        String sNewName = layerRecord.getName();
        Log.e("NewName",sNewName);
    }

    public static void TestMxView2()
    {
        //MxFunction.startIntellectTask(1,"Test");
        //MxFunction.startSearchDwgFileTask(false,"823","test1");
        //MxFunction.viewToDoc()
    }

    public static void TestEntityOCS(MxCADAppActivity activity){
        final long lId = MrxDbgUtils.selectEnt("点击选择PL:");
        if (lId != 0) {


            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("test:",MxFunction.getTypeName(lId));

                    if(MxFunction.getTypeName(lId).equals("McDbPolyline")){
                        McDbPolyline pl = new McDbPolyline(lId);


                        McGePoint3d p0 = pl.getPointAt(pl.numVerts() -1);

                        McGeMatrix3d matOCS = pl.getOCSMatrix();
                        p0.z = 0;
                        p0.TransformBy(matOCS);

                        String str;
                        str = String.format("pt0:%f,%f,%f",p0.x,p0.y,p0.z);
                        Log.e("test:",str);
                    }


                }
            });
        }

    }

    public static void DrawFixWidthLine(MxCADAppActivity activity)
    {
        MxLibDraw.setLineWidth(10);
        MxLibDraw.drawCircle(0,0,5);

        /*
        MxLibDraw.pathMoveTo(0,0);
        MxLibDraw.pathLineTo(100,0);
        MxLibDraw.pathLineTo(100,100);

        MxLibDraw.addLayer("MyLayer");
        MxLibDraw.setLayerName("MyLayer");
        long lId = MxLibDraw.drawPathToPolyline();
        McDbEntity obj =  (McDbEntity)MxFunction.objectIdToObject(lId);
        String s = obj.getTypeName();

        String sLayer = obj.layerName();

        if(obj.getTypeName().equals( "McDbPolyline") )
        {
            McDbPolyline pl = (McDbPolyline)MxFunction.objectIdToObject(lId);

            // 1 单位是mm.
            pl.setFixdLineWidth(1);
        }
        MxLibDraw.setLineWidth(0);
        MxLibDraw.addLayer("中文");
        MxLibDraw.setLayerName("中文");
        lId = MxLibDraw.drawCircle(100,100,300);
        McDbEntity ent =  (McDbEntity)MxFunction.objectIdToObject(lId);

        String sSetLayer = MxLibDraw.layerName();
        String sCircleLayer = ent.layerName();
        ent.setFixdLineWidth(1);

         */

        /*
        long lId = MxLibDraw.drawLine(10,10,200,200);
        MxDrawHandle mxHandle = new MxDrawHandle();
        mxHandle.set(lId,111);

        // 在删除的地方：
        mxHandle.refresh();
        long lOldId  = mxHandle.get(111);
        MxFunction.erase(lOldId);
*/

        //MxFunction.undoMark();
        //MxLibDraw.drawCircle(10,10,200);
        //MxFunction.undoBack();
        //MxLibDraw.drawLine(10,200,200,10);


        //MxLibDraw.drawMText(0,0,"",100);

        //MxFunction.sendStringToExecute("Mx_Line");



    }


    public static void TestRectSelect_dynWorldDraw(MxDrawWorldDraw draw , MxDrawDragEntity dragData){
        McGePoint3d pt1 = dragData.GetPoint("pt1");
        McGePoint3d pt2 =  dragData.GetDragCurrentPoint();
        draw.DrawLine(pt1.x,pt1.y,pt2.x,pt1.y);
        draw.DrawLine(pt2.x,pt1.y,pt2.x,pt2.y);
        draw.DrawLine(pt2.x,pt2.y,pt1.x,pt2.y);
        draw.DrawLine(pt1.x,pt2.y,pt1.x,pt1.y);
    }

    // 给图纸设计用户坐标系.
    public static void TestSetUcs(){


        double[] ptDWGUcsToWcs1 = MxFunction.ucsToWcs(0,0,0);
        double[] ptDWGUcsToWcs2 = MxFunction.ucsToWcs(100,100,0);

        McGeMatrix3d matDWGUCSToWCS = new McGeMatrix3d();
        matDWGUCSToWCS.alignCoordSys(0,0,0,100,100,0,
                ptDWGUcsToWcs1[0],ptDWGUcsToWcs1[1],ptDWGUcsToWcs1[2],ptDWGUcsToWcs2[0],ptDWGUcsToWcs2[1],ptDWGUcsToWcs2[2]);


        McGePoint3d ptTest2 = new McGePoint3d(8216991.8549747551, 2335421.8077209503, 0);
        double[] ptUcs2 = MxFunction.wcsToUcs(ptTest2.x,ptTest2.y,ptTest2.z);
        Log.e("TAG", String.format("align ucsffff:(%.4f,%.4f,%.4f)", ptUcs2[0], ptUcs2[1], ptUcs2[2]));


        McGeMatrix3d matWcsToUCS = new McGeMatrix3d();

        // wcs
        McGePoint3d pt1 = new McGePoint3d(8216991.8549747551, 2335421.8077209503, 0);
        McGePoint3d pt2 = new McGePoint3d(8760991.8549747579, 2455421.8077209340, 0);

        //ucs
        McGePoint3d pt1To = new McGePoint3d(3560608.881, 39522087.932,0);
        McGePoint3d pt2To = new McGePoint3d(3560833.008, 39522597.935,0);

        matWcsToUCS.alignCoordSys(pt1.x,pt1.y,pt1.z,pt2.x,pt2.y,pt2.z,pt1To.x,pt1To.y,pt1To.z,pt2To.x,pt2To.y,pt2To.z);
        matWcsToUCS.invert();

        matWcsToUCS.postMultBy(matDWGUCSToWCS);


        MxFunction.setUcsMatrix(matWcsToUCS);

        McGePoint3d ptTest = new McGePoint3d(8216991.8549747551, 2335421.8077209503, 0);
        double[] ptUcs = MxFunction.wcsToUcs(ptTest.x,ptTest.y,ptTest.z);
        Log.e("TAG", String.format("align ucs:(%.4f,%.4f,%.4f)", ptUcs[0], ptUcs[1], ptUcs[2]));


        /*
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点取计算点：");
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }
        McGePoint3d pt1 = getPoint.value();
        double[] ptT1 = MxFunction.wcsToUcs(pt1.x,pt1.y,pt1.z);

        McGeMatrix3d mat = new McGeMatrix3d();

        // 原点缩放100陪.
        mat.scaling(100,0,0,0);
        MxFunction.setUcsMatrix(mat);

        double[] ptT2 = MxFunction.wcsToUcs(pt1.x,pt1.y,pt1.z);
        Log.e("TAG", "TestSetUcs: ");
         */

    }

    public static void TestSetLineTypeScale(){
        MxLibDraw.addLinetype("MyLine","20,-10",1);

        MxLibDraw.setLineType("MyLine");

        long lId = MxLibDraw.drawLine(10,300,200,10);

        McDbEntity ent = MxFunction.objectIdToEntity(lId);

        ent.setLinetypeScale(0.1);

        String str;
        str = String.format("linetypeScale:%f",ent.linetypeScale());
        Log.e("linetypeScale:",str);



    }

    public static void TestDrawFixedScreenSizeText(){

        MxLibDraw.addLayer("TempTest");
        MxLibDraw.setLayerName("TempTest");
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("第一点：");
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }
        McGePoint3d pt1 = getPoint.value();
        long lIndex = MxFunction.drawFixedScreenSizeText("  Test测试",pt1.x,pt1.y,50,255,2,0);

        // 隐藏绘制的文字.
        // MxFunction.setVisibilityForFixedScreenSizeText(lIndex,false);

        long lId = MxLibDraw.drawPoint(pt1.x ,pt1.y,0);
        McDbPoint ptObject = (McDbPoint)MxFunction.objectIdToObject(lId);
        ptObject.setFixedSize(1);

    }

    public static void TestRectSelect(MxCADAppActivity activity){
        // 交互取第一个点.
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("第一点：");
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }
        McGePoint3d pt1 = getPoint.value();

        // 交互取第二个点
        final  MrxDbgUiPrPoint getPoint2 = new MrxDbgUiPrPoint();
        getPoint2.setMessage("第二点：");
        getPoint2.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        // 初始化动态绘制，在交到过程中，会不停调用 dynWorldDraw函数，实现动态画图。
        MxDrawDragEntity drawdata = getPoint2.initUserDraw("dynRect");
        drawdata.SetPoint("pt1",pt1);

        // 开始取第二个点.
        if(getPoint2.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }
        McGePoint3d pt2 = getPoint2.value();

        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("LINE",5020);
        ss.crossingSelect(pt1,pt2,filter);
        for(int i = 0; i <ss.size();i++)
        {
            ss.at(i);
        }
    }

    public static void DrawImageMark(MxCADAppActivity activity)
    {
        MxFunction.setOsnapZValue(true);
        MxFunction.openCurrentLayer();
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点取开始点");

        getPoint.setOffsetInputPostion(true);
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        if (getPoint.go() != MrxDbgUiPrPoint.Status.kOk ) {
            return;
        }
        final McGePoint3d pt = getPoint.value();

        activity.runOnGLThread(new Runnable() {
            @Override
            public void run() {
                long lId = MxFunction.drawImageMarkEx("location2.png",pt.x, pt.y,0.5,MxFunction.ImageAttachment.kBottomCenter);
                McDbMxImageMark image = new McDbMxImageMark(lId);
                //image.setAngel(80 * 3.14159265 / 180.0);
                //pt.x += 100;
                //image.setPosition(pt);
                image.move( pt.x, pt.y, pt.x+500, pt.y+500);
            }
        });





    }

    public static void TestDraw3DPolyline()
    {
        MxLibDraw.pathMoveTo3D(110,100,23);
        MxLibDraw.pathLineTo3D(510,600,23);
        long lId = MxLibDraw.drawPathTo3DPolyline();
        McDbEntity ent = MxFunction.objectIdToEntity(lId);
        ent.explode();

    }

    public  static void TestSetMxHandle(MxCADAppActivity activity)
    {
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        McGeVector3d normal;
        if (lId != 0) {

            MxDrawHandle mxHandle = new MxDrawHandle();
            mxHandle.set(lId,111);

            long lHandle = mxHandle.getHandle(lId);
            Log.e("TestSetMxHandle: ","");

        }
    }

    public  static void TestGetMxHandle(MxCADAppActivity activity)
    {
        MxDrawHandle mxHandle = new MxDrawHandle();
        mxHandle.refresh();
        long lId = mxHandle.get(111);
        if(lId != 0){
            MxFunction.erase(lId);
        }
    }

    public static void TestSelect(){
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("aa",8);

        ss.allSelect(filter);

        String sT;
        sT = String.format("currentSelect:%d",ss.size());

        Log.e("MrxDbgSelSet",sT);

        // 把aa层上的对象，设置为选中.
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.addCurrentSelect(ss.at(i));
        }
    }

    public  static void TestGetBlockRefForName(){
        String sBlkName = "BlkName";
        MxResbuf filter = new MxResbuf();
        filter.addString("INSERT",5020);
        filter.addString(sBlkName,2);

        MrxDbgSelSet ss = new MrxDbgSelSet();
        ss.allSelect(filter);

        // 遍历选择集中的对象.
        for(int i = 0; i <ss.size();i++) {
            // 得到块引用id.
            long lId = ss.at(i);
        }

    }

    public static void TestTzEntity(){
        if(MxFunction.isHaveTzEntity() )
        {
            Log.d("isHaveTzEntity","true");
        }
        else
        {
            Log.d("isHaveTzEntity","false");
        }
    }


    static int iCall = 0;
    public static void Test(MxCADAppActivity activity)
    {
        long lId = MxLibDraw.drawLine(10,10,200,200);
        McDbLine line = new McDbLine(lId);
        McGePoint3d pt = new McGePoint3d(50,50,05);
        line.setStartPoint(pt);
        //MxFunction.sendStringToExecute("Mx_DrawLeaderDimension");
        //MxFunction.sendStringToExecute("Mx_DrawArrow");
        /*
        long lId = MxLibDraw.drawLine(1,1,0,0);
        McDbLine line = (McDbLine)MxFunction.objectIdToObject(lId);
        McGePoint3d pt = line.getStartPoint();
        pt.z = 100;
        line.setStartPoint(pt);
        */


        /*
        long lId = 0;
        McDbEntity ent = MxFunction.objectIdToEntity(lId);
        if(ent != null){

            McGePoint3d ptMin = new McGePoint3d(),ptMax = new McGePoint3d();
            ent.getGeomExtents(ptMin,ptMax);
        }*/
        /*
        // 设置CAD坐标显示精度

        MxFunction.setSysVarLong("LUPREC",4);


        long lId = MxLibDraw.drawLine(100,100,200,300);
        McDbEntity ent =  (McDbEntity)MxFunction.objectIdToObject(lId);
        String sName = ent.getTypeName();
        if(ent.getTypeName().equals("McDbLine")){
            McDbLine line = (McDbLine)ent;
            McGePoint3d pt1 = new McGePoint3d();
            pt1.x = 100;
            pt1.y = 100;
            pt1.z = 100;
            line.setStartPoint(pt1);
        }*/


        //double dViewLen = MxFunction.docLongToView(100);
        /*
        // 		例如: 设置图纸单位毫米,CAD单位
        // INSUNITS具体的值，可以看autocad帮助,
        long lUnits = MxFunction.getSysVarLong("INSUNITS");


        MxFunction.setSysVarLong("INSUNITS",4);

        lUnits = MxFunction.getSysVarLong("INSUNITS");
        */



        /*
        // 定义一个选择集对象.
        MrxDbgSelSet ss = new MrxDbgSelSet();

        // 得到当前选中的对象.
        ss.currentSelect();

        // 遍历选择集中的对象.
        for(int i = 0; i <ss.size();i++)
        {
            // 得到id.
            long lId = ss.at(i);

            // 得到对象类型名.
            String sName = MxFunction.getTypeName(lId);
            McDbEntity ent = new McDbEntity(lId);
            if(sName.equals("McDbLine"))
            {
                // 该对象是个直线。
                McDbLine line = new McDbLine(ss.at(i));

                McGePoint3d sPt = line.getStartPoint();
                McGePoint3d ePt = line.getEndPoint();

                String sT;
                sT = String.format("sPt:%f,%f,%f,ePt:%f,%f,%f",sPt.x,sPt.y,sPt.z,ePt.x,ePt.y,ePt.z);

                Log.e("Linedata",sT);
            }
        }
*/

        /*
        McDbEntity ent2 = MxFunction.safeObjectIdToEntity(2699);
        long lId = MxLibDraw.drawLine(10,10,200,200);
        long lSafeId = MxFunction.getSafeObjectId(lId);
        McDbEntity ent = MxFunction.safeObjectIdToEntity(lSafeId);
        return;
*/


        //String sFile = MxFunction.getWorkDir() + "/default.dxf";
        //MxFunction.openFile(sFile);
        //MxFunction.asyncOpenFile(sFile);

        /*
        activity.runOnGLThread(new Runnable() {
            @Override
            public void run() {
                long lId = MxFunction.drawImageMarkEx("start.png",642541.291249	,3469813.555835,-450,MxFunction.ImageAttachment.kBottomCenter);
                McDbMxImageMark image = new McDbMxImageMark(lId);
                //image.setAngel(80 * 3.14159265 / 180.0);

                //image.move( pt.x, pt.y, pt.x+500, pt.y+500);
            }
        });
        */


        /*
        MxResbuf filter = new MxResbuf();
        filter.addString("INSERT",5020);

        final long lId = MrxDbgUtils.selectEnt("点击选择对象:",filter);
        if (lId != 0) {
            Log.e("Test","ok");

        }
        */


        /*

        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if (lId != 0) {


            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbPolyline pl = new McDbPolyline(lId);

                    McGePoint3d p0 = pl.getPointAt(0);

                    String str;
                    str = String.format("pt0:%f,%f,%f",p0.x,p0.y,p0.z);
                    Log.e("test:",str);
                }
            });
        }
        */


        //MxLibDraw.drawText(0,0,500,"测试");
        //String sFile = MxFunction.getWorkDir() + "/nnn12.dwg";
        //Boolean isOk = MxFunction.writeFile(sFile);
        //MxFunction.openFile(sFile);
        /*
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");

        if (lId != 0) {
            Log.e("testsel:","ok");
            McDbEntity ent =  MxFunction.objectIdToEntity(lId);
            if(ent != null)
            {
                if(ent.getTypeName().equals("McDbPolyline")
                        || ent.getTypeName().equals("McDbEllipse")
                        || ent.getTypeName().equals("McDb3dPolyline")
                        || ent.getTypeName().equals("McDbSpline")

                )
                {
                    McDbCurve curve = (McDbCurve)ent;
                    double dL = curve.GetLength();

                    McGePoint3d endPt = curve.getEndPoint();
                    double dDist = curve.getDistAtPoint(endPt.x,endPt.y);

                    String str;
                    str = String.format("dL:%f,dDist:%f",dL,dDist);
                    Log.e("test:",str);


                }
            }
        }*/


        // 0,UCS 与 WCS 不同,1,UCS 与 WCS 相同
        //long lWORLDUCS = MxFunction.getSysVarLong("WORLDUCS");
        //Log.e("xxx", "Test: ");

        /*
        iCall++;
        if(iCall == 1)
        {
            long[] ids = MxFunction.getAllLayer();
            for (long id : ids) {
                McDbLayerTableRecord layer = new McDbLayerTableRecord(id);
                layer.setIsOff(true);
            }
        }
        else if(iCall == 2)
        {

            long lLayerId = MxFunction.getCurrentDatabase().getLayerTable().getAt("基坑环境尺寸");
            if(lLayerId != 0)
            {
                McDbLayerTableRecord layer = new McDbLayerTableRecord(lLayerId);
                layer.setIsOff(false);
            }
        }
        else if(iCall == 3)
        {
            long lLayerId = MxFunction.getCurrentDatabase().getLayerTable().getAt("基坑环境尺寸");
            if(lLayerId != 0)
            {
                McDbLayerTableRecord layer = new McDbLayerTableRecord(lLayerId);
                layer.setIsOff(true);
            }
        }
    */

        /*
        double[] point = MxFunction.wcsToUcs(0,0,0);
        String str;
        str = String.format("%f,%f,%f",point[0],point[1],point[2]);
        Log.e("pt:",str);
*/
        //MxFunction.clearSelect();
        //boolean isOk = MxFunction.saveCurrentViewData();
        //Log.e("ver:",isOk ? "true": "false");
        //String s  = MxFunction.getBulidVersionString();
        //Log.e("ver:",s);
        //MxFunction.deleteLayerAndEntitys("kkk");
        /*


        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        Log.e("copy:","xxxx");
        if (lId != 0) {
            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbEntity ent = new McDbEntity(lId);
                    long lCopyId =  ent.cloneObject();
                    String str;
                    str = String.format("color:%d",lCopyId);
                    Log.e("copy:",str);
                }
            });

        }
*/
        /*

        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        McGeVector3d normal;
        if (lId != 0) {
            McDbEntity ent = new McDbEntity(lId);
            int[] color = new int[3];
            if(ent.getColorIndex() ==  MrxDbgUtils.Color.kBylayer){
                McDbLayerTableRecord layer = new McDbLayerTableRecord(ent.layer());
                int[] layerColor = layer.getColor();
                color[0] = (int)layerColor[0];
                color[1] = (int)layerColor[1];
                color[2] = (int)layerColor[2];
            }
            else if(ent.getColorIndex() ==   MrxDbgUtils.Color.kByblock)
            {
                color[0] = 255;
                color[1] = 255;
                color[2] = 255;
            }
            else{
                color = ent.getColor();
            }

            String strColor;
            strColor = String.format("color:%d,%d,%d",color[0],color[1],color[2]);
            Log.e("normal:",strColor);
        }


        // 选择一个块，把块里面的对象的颜色改成随块，然后把块引用颜色改成红色.
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        McGeVector3d normal;
        if (lId != 0) {

            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    String sType = MxFunction.getTypeName(lId);
                    if (sType.equals("McDbBlockReference")) {
                        McDbBlockReference blkRef = new McDbBlockReference(lId);
                        long lIdBlkRec = blkRef.blockTableRecord();

                        McDbBlockTableRecord blkRec = new McDbBlockTableRecord(lIdBlkRec);
                        String sBlkName = blkRec.getName();
                        long aryIds[] = blkRec.getAllEntity();

                        int kByblock = 0;
                        for (int i = 0; i < aryIds.length; i++) {
                            McDbEntity ent = new McDbEntity(aryIds[i]);
                            ent.setColorIndex(kByblock);
                        }

                        blkRef.setColor(255, 0, 0);
                        blkRec.assertWriteEnabled();

                    }
                }
            });

        }*/


        /*
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        McGeVector3d normal;
        if (lId != 0) {
            McDbEntity ent = new McDbEntity(lId);
            normal = ent.normal();

            String strNoraml;
            strNoraml = String.format("lId:%f,%f,%f",normal.x,normal.y,normal.z);
            Log.e("normal:",strNoraml);
        }
*/


        /*
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if (lId != 0) {
            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbPolyline pl = new McDbPolyline(lId);

                    McGePoint3d p0 = pl.getPointAt(0);
                    double width = 0.01;
                    MxLibDraw.setDrawColor(new long []{0,0,255});

                }
            });



        }
        */


        /*
        final MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();

        ss.userSelect(filter);
        McGePoint3d pt;
        double dX = 0;
        double dY = 0;
        double dZ = 0;
        for(int i = 0; i<ss.size();i++)
        {
            String sName =  MxFunction.getTypeName(ss.at(i));


            if(sName.equals("McDbText")) {
                McDbText txt = new McDbText(ss.at(i));

                pt = txt.position();
                dZ = pt.z;
                dX = pt.x;
                dY = pt.y;

            }
        }
        */


        /*
        final MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();

        ss.userSelect(filter);

        int iSize = ss.size();
        activity.runOnGLThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<ss.size();i++)
                {
                    MxFunction.deleteObject(ss.at(i));
                }
            }
        });
        */

        /*
        final long lId = MrxDbgUtils.selectEnt("选择对象:");
        if (lId != 0) {

            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    //McDbEntity arc = new McDbEntity(lId);
                    //arc.setArcDensityAngle(2);
                    McDbArc arc = new McDbArc(lId);
                    arc.correctOCS();
                }
            });


        }
*/
        /*
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if (lId != 0) {
            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbPolyline pl = new McDbPolyline(lId);
                    double width = 0.01;
                    MxLibDraw.setDrawColor(new long []{0,0,255});
                    McGePoint3d p0 = pl.getPointAt(0);
                    MxLibDraw.pathMoveToEx(p0.x, p0.y , width, width, pl.getBulgeAt(0));
                    for (int i = 1; i <  pl.numVerts(); i++) {
                        McGePoint3d mcGePoint3d = pl.getPointAt(i);
                        MxLibDraw.pathLineToEx(mcGePoint3d.x, mcGePoint3d.y, width, width, pl.getBulgeAt(i));
                    }
                    MxLibDraw.drawPathToPolyline();
                }
            });



        }
         */

    }

    // 测试修改对象
    public static void TestModify(MxCADAppActivity activity) {
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if (lId != 0) {
            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbEntity ent = (McDbEntity)MxFunction.objectIdToObject(lId);

                    String sName =   ent.getTypeName();

                    if (sName.equals("McDbEllipse")) {
                        McDbEllipse ell = (McDbEllipse) ent;
                        McGeVector3d vec = new McGeVector3d();
                        vec.x = 100;
                        vec.y = 0;
                        vec.z = 0;
                        ell.setMinorAxis(vec);
                    }





                }
            });



        }
    }

    // 打碎对象
    public static void ExplodeTest(MxCADAppActivity activity) {
        final long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if (lId != 0) {
            activity.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    McDbEntity ent = new McDbEntity (lId);
                    long[] newIds = ent.explode();
                    MxFunction.erase(lId);
                }
            });



        }
    }

    public  static void TestInserBlock(){
        //String sFileName = MxFunction.getWorkDir() + "/blk.dxf";
        String sFileName = MxFunction.getWorkDir() + "/testblk.dwg";
        MxLibDraw.insertBlock(sFileName, "Temp");

        long lBlkRecId = MxFunction.getCurrentDatabase().getBlockTable().getAt("Temp");
        if(lBlkRecId != 0){
            McDbBlockTableRecord blkRec = new McDbBlockTableRecord(lBlkRecId);
            McGePoint3d pt = new McGePoint3d();
            pt.x = 200;
            pt.y = 200;
            // 设置块表记录的插入基点 。
            blkRec.setOrigin(pt);
        }

        long lId = MxLibDraw.drawBlockReference(100,100,"Temp", 10, 0);

    }

    // 设置点样式。
    public static void SetPointMode() {
        MxFunction.setSysVarDouble("PDSIZE", 2.0);

        // 圆+
        MxFunction.setSysVarLong("PDMODE", 35);
    }

    public static void DoUiTest() {



        //MxFunction.sendStringToExecute("MxSample_Test");
        /*
        String sFile = MxFunction.getWorkDir() + "/2.pdf";
        MxFunction.writePdf(sFile);
*/
        //MxLibDraw.addLayer("图层中文");
        //double[] pt = MxFunction.ucsToWcs(0,0,0);


        //MxFunction.openFile("");



        /*// 画一个箭头.
        MxLibDraw.pathMoveToEx(1000, 300, 10, 10, 0);
        MxLibDraw.pathLineToEx(1000, 500, 30, 0, 0);

        MxLibDraw.pathLineTo(1000, 580);

        MxLibDraw.drawPathToPolyline();
        MxFunction.zoomAll();

        String sFile = MxFunction.getWorkDir() + "/111.png";
       MxFunction.savePreviewFile(sFile);

        MrxDbgSelSet ss = new MrxDbgSelSet();
        ss.allSelect();
        for (int i = 0; i < ss.size(); i++) {
            long lId = ss.at(i);

            McDbEntity ent = new McDbEntity(lId);

            String sName = MxFunction.getTypeName(lId);

            if (sName.equals("McDbText")) {
                McDbText txt = new McDbText(ss.at(i));
                McGePoint3d pos = txt.position();
                McGePoint3d apos =  txt.alignmentPoint();




                String sTxt = txt.textString();
                if (sTxt.equals("1n5X1")) {

                    String strPos;
                    strPos = String.format("lId:%f,%f,%f",pos.x,pos.y,pos.z);
                    Log.e("Pos",strPos);


                    Log.e("sText", sTxt);
                    String ssid;
                    ssid = String.format("lId:%d",lId);
                    Log.e("lids",ssid);

                    MxResbuf xdata = ent.xData("");
                    if(xdata != null) {
                        long lCount = xdata.getCount();
                        xdata.print();
                    }
                }

            }
        }

*/
        //MxFunction.deleteOnLayerAllEntity("0");
        //MxFunction.se
        //MxFunction.reLoadToolbar();

        /*
        String sFile = MxFunction.getWorkDir() + "/nnn.dwg";




        Boolean isOk = MxFunction.writeFile(sFile);
         Log.e("currentFileName",MxFunction.currentFileName());

*/
        //Boolean isOk3 = MxFunction.writeFile(MxFunction.currentFileName());

        //Log.e("currentFileName2",MxFunction.currentFileName());
        /*
        MxModifyTheColor modifColor = new MxModifyTheColor();
        modifColor.Do(255,255,255);
        MxFunction.sendStringToExecute("Mx_RegenEx");
*/
        //return;

        //MxFunction.setReadFileContent(ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadxData | ReadContent.kReadNamedObjectsDictionary | ReadContent.kReadXrecord );
        //MxFunction.addSupportAppName("*");
        //long lId = MxLibDraw.drawLine(0,0,100,100);
        //MxFunction.setxDataString(lId,"MyData","（常规倾角）_1710～2170_17.5dbi_65°_手动电调");
        //MxFunction.zoomAll();

        //MxFunction.writeFile( MxFunction.getWorkDir() + "/nnnnn.dwg");


        //long id = MxLibDraw.drawLine(10,10,100,100);

        //   {
        //  McDbEntity obj =  (McDbEntity)MxFunction.objectIdToObject(id);
        //obj.deleteAllXData();

        // }

        /*

        MxLibDraw.addLayer("中文");

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
        */
/*
        MxLibDraw.addLayer("MyTest");
        MxLibDraw.setLayerName("MyTest");
        long id = MxLibDraw.drawLine(10,10,100,100);

        {
            McDbObject obj =  MxFunction.objectIdToObject(id);
            //创建对象扩展字典
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


        }

        {
            McDbObject obj =  MxFunction.objectIdToObject(id);
            //创建对象扩展字典
            obj.createExtensionDictionary();

            // 得到扩展字典
            McDbDictionary dict = new McDbDictionary(obj.extensionDictionary());

            // 向扩展字典中加入一个扩展记录.
            McDbXrecord xrec;
            long lDataId = dict.getAt("MyData");
            if(lDataId == 0)
                 xrec = new  McDbXrecord(dict.addRecord("MyData"));
            else
                xrec = (McDbXrecord)MxFunction.objectIdToObject(lDataId);

            // 设置扩展记录数据。

            MxResbuf data = new MxResbuf();
            data.addLong(111);;
            data.addString("yyyyyyy");;
            xrec.setFromRbChain(data);


        }
        MxFunction.writeFile( MxFunction.getWorkDir() + "/MyTestDict.dwg");
*/
        /*
        String sFile = MxFunction.currentFileName();

        if(MxFunction.writeFile(sFile))
        {
            Log.e("writeFile","Ok");
        }else {
            Log.e("writeFile","Failed");
        }
*/
        //MxFunction.sendStringToExecute("Mx_RegenEx");
        /*
        McDbDictionary dict = new McDbDictionary( MxFunction.getNamedObjectsDictionary());

        long lDict = dict.getAt("MyDict");
        if(lDict == 0)
            return;

        McDbDictionary myDict = new McDbDictionary(lDict);

        long lRecord = myDict.getAt("MyData");
        if(lRecord == 0)
            return;

        McDbXrecord xrec = new  McDbXrecord(lRecord);
        MxResbuf data =  xrec.rbChain();
        if(data == null)
            return;

        data.print();
        data.print();
        */
        //data.atLong(0);

        //MxFunction.sendStringToExecute("Mx_LayerManager");
        //Log.e("currentFileName",MxFunction.currentFileName());
        /*
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("aa",8);

        ss.allSelect(filter);

        String sT;
        sT = String.format("currentSelect:%d",ss.size());

        Log.e("MrxDbgSelSet",sT);

        // 把aa层上的对象，设置为选中.
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.delSelect(ss.at(i));
        }

          MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("TEXT,MTEXT",5020);

        ss.allSelect(filter);
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.delSelect(ss.at(i));
        }
        */

    }

    public static void DoMenuTest() {
         /* MxFunction.setSysVarLong("PDMODE",35);


        long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if(lId == 0)
            return;

        McDbEntity ent = (McDbEntity)MxFunction.objectIdToObject(lId);
        MxResbuf xdata = ent.xData("");
        if(xdata != null) {
            long lCount = xdata.getCount();
            xdata.print();
        }
        */


/*
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setOffsetInputPostion(true);
        getPoint.setMessage("请选择一个点");
        if (getPoint.go() != MrxDbgUiPrPoint.Status.kOk) {
            return;
        }
        McGePoint3d value = getPoint.value();


        String sFileName = String.format("%s/%s.dwg", getWorkDir(), "模型库");

            MxLibDraw.insertBlock(sFileName, "tmp");

        McDbBlockTable blkTab = MxFunction.getCurrentDatabase().getBlockTable();
        long[] all = blkTab.getAll();
        if (all != null) {
            for (long lBlkRec : all) {
                McDbBlockTableRecord blkRec = new McDbBlockTableRecord(lBlkRec);
                Log.i("lyt" , blkRec.getName());
            }
            Log.i("lyt", "是否包含块getAt： " + blkTab.getAt("俯视图_塔_仿生树_5"));
            Log.i("lyt", "是否包含块has： " + blkTab.has("俯视图_塔_仿生树_5"));
        }
        boolean b = blkTab.has("俯视图_塔_仿生树_5");

        MxFunction.setSysVarDouble("PDSIZE", 2.0);
        long reference = MxLibDraw.drawBlockReference(value.x, value.y, "俯视图_增高架_楼面增高架_2", 1, 0);
        MxFunction.setxDataString(reference, "LINE", "test1");
        MxFunction.setxDataString(reference, "LINE2", "test2");
        MxFunction.setxDataString(reference, "LINE3", "test3");
        if (reference == 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "插入失败", Toast.LENGTH_LONG).show();
                }
            });
        }
        */

        /*
          this.runOnGLThread(new Runnable() {
                  @Override
                public void run() {
                      MxFunction.drawImage("../myimage/start.png",200,200,30);

                      String sFile = MxFunction.getWorkDir() + "/a111.dwg";
                      MxFunction.writeFile(sFile);

                  }
             });
*/



        /*
        McDbLayerTableRecord layRecord = new McDbLayerTableRecord("苏州河河道线");
        layRecord.setFrozen(true);
        MxFunction.sendStringToExecute("Mx_RegenEx");
*/
        //MxLibDraw.addLayer("MyTestLayer");
/*
        String sLayer = new String("多段线");


        MxFunction.setSysVarString("CLAYER",sLayer);

        String sFile = MxFunction.getWorkDir() + "/aaaa5.dwg";
        MxFunction.writeFile(sFile);


        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点取文字插入点");
        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();

        MxLibDraw.addTextStyle1("MyTextStyle","txt.shx","gbcbig.shx",0.7f);
        MxLibDraw.setTextStyle("MyTextStyle");


        MxLibDraw.addTextStyle1("MyTextStyle2","txt.shx","hztxt.shx",0.7f);


        for(int i =0;i<1000;i++){
            MxLibDraw.drawText(pt.x+i,pt.y+i,500,"测试Test");
        }


        long lId = MxLibDraw.drawText(pt.x + 600,pt.y,500,"测试Test2222");

        McDbText txt = new McDbText(lId);
        txt.setTextStyleName("MyTextStyle2");
*/

        //DynGetPoint();

        //return;



        /*
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点位置");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();

        String sFileName = MxFunction.getWorkDir() + "/Gc097.dwg";
        long lId = MxLibDraw.insertBlock(sFileName, "Temp");

         lId = MxLibDraw.drawBlockReference(pt.x,pt.y,"Temp", 10, 0);

        //String sFile = MxFunction.getWorkDir() + "/a5.dwg";
       //MxFunction.writeFile(sFile);
*/

        /*
        long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if(lId == 0)
            return;

        McDbEntity ent = (McDbEntity)MxFunction.objectIdToObject(lId);
        ent.setVisibility(false);
        */
        //   ent.set


        /*
        long id = MxLibDraw.drawLine(0,0,100,100);
        McDbEntity obj = (McDbEntity)MxFunction.objectIdToObject(id);
        McGeMatrix3d mat1 = new McGeMatrix3d();
        mat1.scaling(2,0,0,0);

        McGeMatrix3d mat2 = new McGeMatrix3d();
        mat2.rotation(45 * 3.14159265 / 180.0,0,0,1,0,0,0);

        McGeMatrix3d mat3 = new McGeMatrix3d();
        mat3.translation(100,200,0);

        mat1.postMultBy(mat3);
        mat1.postMultBy(mat2);

        obj.transformBy(mat1);
        */
        /*
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点位置");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();

        MxLibDraw.drawPoint(pt.x,pt.y);
        */
        /*
       //MxFunction.openFileEx( MxFunction.getWorkDir() + "/MyTestDict.dwg",
        //       ReadContent.kFastRead | ReadContent.kReadObjectsDictionary | ReadContent.kReadxData | ReadContent.kReadNamedObjectsDictionary | ReadContent.kReadXrecord );
        MxFunction.openFile(MxFunction.getWorkDir() + "/MyTestDict.dwg");
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filet = new MxResbuf();
        filet.addString("MyTest",8);
        ss.allSelect(filet);




        for(int i = 0; i <ss.size();i++)
        {
            McDbObject obj =  MxFunction.objectIdToObject(ss.at(i));
            long lDictId = obj.extensionDictionary();
            printDictionary(lDictId);


        }

        */
        /*
        long id = MxLibDraw.drawLine(0,0,100,100);
        McDbEntity obj = (McDbEntity)MxFunction.objectIdToObject(id);
        int[]  iColor = obj.getColor();

        McDbLayerTableRecord layer = new McDbLayerTableRecord("0");
        layer.setColor(0,255,0);
        iColor = layer.getColor();
*/
        //layer.g(true);


        // 下面代码，是把图片，画到MyLayer.
        /*
        MxLibDraw.addLayer("MyLayer");
        MxLibDraw.setLayerName("MyLayer");
        MxFunction.drawImage("start.png",200,200,30);
        //MxLibDraw.drawLine(0,0,100,100);

        // 下面，是找到MyLayer,删除上面的对象。
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("MyLayer",8);

        // 得到MyLayer上所有对象
        ss.allSelect(filter);
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.erase(ss.at(i));

        }
*/

        /*
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点位置");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        McGePoint3d pt = getPoint.value();

        String sFileName = MxFunction.getWorkDir() + "/fm.dwg";
        MxLibDraw.insertBlock(sFileName, "Temp");

        long [] color = new long[3];
        color[0] = 255;
        color[1] = 0;
        color[2] = 0;
        MxLibDraw.setDrawColor(color);

        long lId = MxLibDraw.drawBlockReference(pt.x,pt.y,"Temp", 10, 0);
        MxLibDraw.drawText(pt.x,pt.y,50,"测试Test");


        color[0] = 0;
        color[1] = 255;
        color[2] = 0;
        MxLibDraw.setDrawColor(color);
        pt.y += 100;

        MxLibDraw.drawBlockReference(pt.x,pt.y,"Temp", 10, 0);
        MxLibDraw.drawText(pt.x,pt.y,50,"测试Test");
*/
        /*
        MxLibDraw.setLineWidth(10);;
        final long lId2 = MxLibDraw.drawLine(400,0,0,400);

        long [] color = new long[3];
        color[0] = 255;
        color[1] = 0;
        color[2] = 0;

        MxLibDraw.setDrawColor(color);
        long lId1 = MxLibDraw.drawLine(0,0,400,400);

        McDbEntity ent2 = new McDbEntity(lId2);
        ent2.setDrawOrder(4);

        McDbEntity ent1= new McDbEntity(lId1);
        ent1.setDrawOrder(1);

        MxResbuf data = new MxResbuf();
        data.addString("MYAppName",1001);
        McGePoint3d pt = new McGePoint3d();
        pt.x = 100;
        pt.y = 100;
        data.addPoint(pt);
        ent1.setXData(data);
        */


        /*
       // '《---------------------------------------》
       // '绘制一个有图案的填充
       // 'angle, x-origin,y-origin, delta-x,delta-y,dash-1,dash-2, …
       // '45 = angle 是图案线角度.
       // '0 = x-origin 是第一个填充线经过的点位置X坐标
        // '0 = y-origin 是第一个填充线经过的点位置Y坐标
       // '0 = delta-x   是下一个填充线相对前一个线的X方向偏移
       // '0.125 = delta-y   是下一个填充线相对前一个线的Y方向偏移
        MxLibDraw.addPatternDefinition ("MyHatchPattern1", "((45, 0,0, 0,0.125))");
        MxLibDraw.setPatternDefinition("MyHatchPattern1");



        //'定义一个路径的开始点
        MxLibDraw.pathMoveToEx( 600, 3300, 0, 0, 0.3);

        //'路径的一下个点
        MxLibDraw.pathLineTo(700, 3300);

        //'路径的一下个点
        MxLibDraw.pathLineTo( 700, 3400);

        //'路径的一下个点
        MxLibDraw.pathLineTo(600, 3300);

        //'把路径变成一个填充,80,是填充图案的缩放比例.
        MxLibDraw.drawPathToHatch(20);
        MxFunction.zoomAll();
*/

        /*
         MxLibDraw.addLayer("AAA");
         MxLibDraw.setLayerName("AAA");
         long lId = MxLibDraw.drawLine(400,0,0,400);

         McDbLine line = new McDbLine(lId);
         line.setColorIndex(5);

         McDbLayerTableRecord layer = new McDbLayerTableRecord("AAA");
         layer.setIsLocked(true);

         MxFunction.zoomAll();

*/
        //McDbLayerTableRecord layer = new McDbLayerTableRecord("layername");
        //layer.setIsOff(true);

        // MxFunction.sendStringToExecute("Mx_LayerManager");
        // 得到扩展字典

        /*
        McDbDictionary dict = new McDbDictionary( MxFunction.getNamedObjectsDictionary());
        ;
        McDbDictionary myDict = new McDbDictionary(dict.addDict("MyDict"));

        // 向扩展字典中加入一个扩展记录.
        McDbXrecord xrec = new  McDbXrecord(myDict.addRecord("MyData"));

        // 设置扩展记录数据。
        MxResbuf data = new MxResbuf();
        data.addLong(111);;
        data.addString("xxxxxxx");;
        xrec.setFromRbChain(data);
*/


        //  McDbLine line = new McDbLine(lId2);
        //McGePoint3d pt = new McGePoint3d(10,10,0);

        //line.setEndPoint(pt);


        //MxFunction.sendStringToExecute("Mx_LayerManager");
        /*
        String sFileName = MxFunction.getWorkDir() + "/blk.dwg";
        MxLibDraw.insertBlock(sFileName, "Temp");

        long lId = MxLibDraw.drawBlockReference(100,100,"其他_落地式空调_新增", 10, 0);


*/
        /*
        long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if(lId == 0)
            return;


        if(MxFunction.getTypeName(lId).equals("McDbPolyline"))
        {
            McDbPolyline pl = new McDbPolyline(lId);

            MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
            if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
            {
                return;
            }

            //McGePoint3d onPt = pl.getClosestPointTo(getPoint.value() );
            //McGePoint3d onPt = pl.getClosestPointTo(getPoint.value() );
            McGePoint3d onPt = pl.getClosestPointTo(new McGePoint3d(250489.64862643,3375032.01291682,0.0));



            String sT;
            sT = String.format("ClosestPoint:%f,%f,%f",onPt.x,onPt.y,onPt.z);

            Log.e("ClosestPoint",sT);
        }
*/
        /*

        long lId = MrxDbgUtils.selectEnt("点击选择对象:");
        if(lId == 0)
            return;


        if(MxFunction.getTypeName(lId).equals("McDbPolyline"))
        {
            McDbPolyline pl = new McDbPolyline(lId);

            MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
            if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
            {
                return;
            }

            McDbCurve[] cur =  pl.getOffsetCurvesEx(10,getPoint.value() );

            String sT;
            sT = String.format("getOffsetCurvesEx:%d",cur.length);

            Log.e("getOffsetCurvesEx",sT);
        }
*/

        /*
        long lId = MrxDbgUtils.selectEnt("点击曲线对象:");
        if(lId == 0)
            return;

        McDbObject obj = MxFunction.objectIdToObject(lId);
        if(!obj.isCurve())
            return;
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setMessage("点取打位置");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }
        McDbCurve curve = (McDbCurve)obj;
        McGePoint3d pt = curve.getClosestPointTo(getPoint.value());
        if(pt == null)
            return;
        McDbCurve[] newCurve =  curve.getSplitCurves(pt);
        if(newCurve != null)
        {
            // 删除以前对象;
            curve.erase();
        }
*/

        /*
        MrxDbgSelSet ss = new MrxDbgSelSet();
        McGePoint3d pt1 = new McGePoint3d(100,10,10);
        McGePoint3d pt2 = new McGePoint3d(0,0,0);

        ss.crossingSelect(pt1,pt2);

        String sT;
        sT = String.format("crossingSelect:%d",ss.size());

        Log.e("MrxDbgSelSet",sT);
        */

        /*
        try {

            String filename = Environment.getExternalStorageDirectory() + "/"+ "TestMxLib/sample.dwg";
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[input.available()];
            input.read(temp);

            //关闭输入流
            input.close();

            MxFunction.openBinFile(temp);
        }
        catch (IOException e)
        {

        }
        */

        /*
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("aa",8);

        ss.allSelect(filter);

        String sT;
        sT = String.format("currentSelect:%d",ss.size());

        Log.e("MrxDbgSelSet",sT);

        // 把aa层上的对象，设置为选中.
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.addCurrentSelect(ss.at(i));
        }


          MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();
        filter.addString("你的层名",8);

        ss.allSelect(filter);

        String sT;
        sT = String.format("currentSelect:%d",ss.size());

        Log.e("MrxDbgSelSet",sT);

        // 把aa层上的对象，设置为选中.
        for(int i = 0; i <ss.size();i++)
        {
            MxFunction.erase(ss.at(i));
        }

*/
    }

    // 测试求交。
    public static void TestIntersectWith() {
        MrxDbgSelSet ss = new MrxDbgSelSet();
        MxResbuf filter = new MxResbuf();


        ss.userSelect(filter);
        int iSize = ss.size();

        if (ss.size() < 2)
            return;

        McDbEntity ent = new McDbEntity(ss.at(0));
        McGePoint3d[] pts = ent.intersectWith(ss.at(1), McDbEntity.Intersect.kExtendBoth);


        if (pts != null) {

            String sK;
            sK = String.format("%d,%f,%f", pts.length, pts[0].x, pts[0].y);
            MxLibDraw.drawCircle(pts[0].x, pts[0].y, 10);

            Log.e("pts len:", sK);

        }

    }

    // 测试得到属性文本
    public static void GetBlockRefAttrib() {
        MrxDbgSelSet ss = new MrxDbgSelSet();
        ss.userSelect();
        for (int i = 0; i < ss.size(); i++) {
            if (!MxFunction.getTypeName(ss.at(i)).equals("McDbBlockReference"))
                continue;

            McDbBlockReference blkRef = new McDbBlockReference(ss.at(i));
            McDbBlockTableRecord blkRec = new McDbBlockTableRecord(blkRef.blockTableRecord());
            Log.e("BlkName:", blkRec.getName());

            long[] allAtt = blkRef.getAllAttribute();
            if (allAtt != null) {
                for (int j = 0; j < allAtt.length; j++) {
                    McDbAttribute att = new McDbAttribute(allAtt[j]);
                    Log.e("tagConst:", att.tagConst());
                    Log.e("textString:", att.textString());

                }
            }
        }

    }

    // 测试插入图片。
    public static void TestDrawImage() {
        MxLibDraw.addLayer("MyTest");
        MxLibDraw.setLayerName("MyTest");
        long lImageId2 = MxFunction.drawImage2("start.png", 0, 0, 1000, 3000);
    }

    // 测试保存文件
    public static void TestSave() {

        long lId = MxLibDraw.drawLine(100,100,200,300);
        McDbEntity ent =  (McDbEntity)MxFunction.objectIdToObject(lId);
        MxResbuf data = new MxResbuf();
        data.addString("MYAppName",1001);
        McGePoint3d pt = new McGePoint3d();
        pt.x = 100;
        pt.y = 100;

        data.addPoint(pt);
        data.addString("xxxxxx");
        ent.setXData(data);
        String sFileName = MxFunction.getWorkDir() + "/testsave.dwg";
        MxFunction.writeFile(sFileName);
    }

    public  static void DrawText()
    {
        MxLibDraw.addLayer("TestLayer");
        MxLibDraw.setLayerName("TestLayer");

        MxLibDraw.drawTextEx(100,100,500,"测试Test2",15,McDbText.TextHorzMode.kTextLeft,McDbText.TextVertMode.kTextBottom);
    }

    public  static void TestSaveBuffer()
    {
        MxFunction.writeBufferFile("",true);
    }



    public static void TestDrawLine()
    {
        MxLibDraw.addLayer("mxcadcomment2");
        MxLibDraw.setLayerName("mxcadcomment2");

        MrxDbgUiPrPoint getStartPoint = new MrxDbgUiPrPoint();
        getStartPoint.setMessage("点取开始点");

        getStartPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheBegan);
        if (getStartPoint.go() != MrxDbgUiPrPoint.Status.kOk ) {
            return;
        }
        McGePoint3d startPt = getStartPoint.value();


        MrxDbgUiPrPoint getEndPoint = new MrxDbgUiPrPoint();
        getEndPoint.setMessage("点取结束点");
        getEndPoint.setBasePt(startPt);
        getEndPoint.setUseBasePt(true);


        if (getEndPoint.go() != MrxDbgUiPrPoint.Status.kOk) {
            return;
        }
        McGePoint3d endPt = getEndPoint.value();
        MxLibDraw.drawLine(startPt.x,startPt.y,endPt.x,endPt.y);
    }


    public static void DynDrawLine_dynWorldDraw(MxDrawWorldDraw draw , MxDrawDragEntity dragData)
    {
        // 取到动态绘制数据。
        String sPrv = dragData.GetString("Prv");

        McGePoint3d pt2 =  dragData.GetDragCurrentPoint();
        McGePoint3d pt1 = dragData.GetPoint("pt1");
        // 算出，动态距离。
        double dDist = pt1.distanceTo(pt2);


        McGePoint3d[] pts = new McGePoint3d[4];
        double[] swidths = new double[4];
        double[] ewidths = new double[4];
        double[] bulges = new double[4];
        McGeVector3d vec2 = pt2.SumVector(pt1);
        double dArrowLen = MxFunction.viewLongToDoc(40);

        if(dArrowLen > dDist * 0.5)
            dArrowLen = dDist * 0.5;

        vec2.normal();
        vec2.Mult(dArrowLen);

        McGePoint3d pt3 = new McGePoint3d(pt1.x,pt1.y,pt1.z);
        pt3.Add(vec2);

        McGePoint3d pt4 = new McGePoint3d(pt2.x,pt2.y,pt2.z);
        pt4.Sum(vec2);


        pts[0] = pt1;
        swidths[0] = 0;
        ewidths[0] = MxFunction.viewLongToDoc(16);
        bulges[0] = 0;

        pts[1] = pt3;
        swidths[1] = 0;
        ewidths[1] = 0;
        bulges[1] = 0;

        pts[2] = pt4;
        swidths[2] =  MxFunction.viewLongToDoc(16);
        ewidths[2] =0;
        bulges[2] = 0;

        pts[3] = pt2;
        swidths[3] = 0;
        ewidths[3] = 0;
        bulges[3] = 0;

        draw.DrawPolyline(pts,swidths,ewidths,bulges);

        McGeVector3d vec = pt2.SumVector(pt1);
        vec.Mult(0.5);

        pt1.Add(vec);

        if(vec.dotProduct(McGeVector3d.kXAxis) < 0) {
            vec.x = -vec.x;
            vec.y = -vec.y;
            vec.z = -vec.z;
        }


        double dAng = vec.angleTo(McGeVector3d.kXAxis,McGeVector3d.kNZAxis);


        vec.normal();


        vec.rotateBy(3.14159265 * 0.5);

        double dH =  MxFunction.viewLongToDoc(20);
        vec.Mult(dH * 2.0 / 3.0);
        pt1.Add(vec);

        String sT;
        sT = sPrv + "=" + String.format("%f",dDist);



        // 在两点的中心点，动态绘制一个文本。
        draw.DrawText(pt1.x,pt1.y,sT,dH,dAng,1,1);

    }

    public static void DynDrawLine(MxCADAppActivity activity)
    {

        // 交互取第一个点.
        MrxDbgUiPrPoint getPoint = new MrxDbgUiPrPoint();
        getPoint.setOffsetInputPostion(true);
        //getPoint.setLongPressedCatch(true);
        getPoint.setCursorType(MrxDbgUiPrPoint.CursorType.kCrossCursor);
        getPoint.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);
        getPoint.setMessage("第一点：");

        if(getPoint.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }

        MxFunction.setSysVarLong("OSMODE", 0x3FFF);
        McGePoint3d pt = getPoint.value();
        String sT;
        sT = String.format("pt:%f,%f,%f,lastOsmodeMode:%d",pt.x,pt.y,pt.z, MxFunction.getLastOsnapMode());

        // 交互取第二个点
        final  MrxDbgUiPrPoint getPoint2 = new MrxDbgUiPrPoint();
        getPoint2.setMessage("第二点：");
        getPoint2.setBasePt(pt);
        getPoint2.setUseBasePt(true);
        getPoint2.setOffsetInputPostion(true);
        //getPoint2.setLongPressedCatch(true);
        getPoint2.setCursorType(MrxDbgUiPrPoint.CursorType.kCrossCursor);
        getPoint2.setToucheType(MrxDbgUiPrPoint.ToucheType.kToucheEnded);

        // 初始化动态绘制，在交到过程中，会不停调用 dynWorldDraw函数，实现动态画图。
        MxDrawDragEntity drawdata = getPoint2.initUserDraw("mydyndraw");

        // 设置动态绘制数据。
        drawdata.SetString("Prv","Len");
        drawdata.SetPoint("pt1",pt);

        // 开始取第二个点.
        if(getPoint2.go() != MrxDbgUiPrPoint.Status.kOk)
        {
            return;
        }



        McGePoint3d pt2 = getPoint2.value();


        // 把取到的点，用来画直线。
        //MxLibDraw.drawLine(pt.x,pt.y,pt2.x,pt2.y);
        activity.runOnGLThread(new Runnable() {
            @Override
            public void run() {

                getPoint2.drawEntitys();
            }
        });


        Log.e("getPoint",sT);

    }

    // 得到图上所有对象。
    public static  void GetAllEntity()
    {
        MrxDbgSelSet ss = new MrxDbgSelSet();
        ss.allSelect();
        for(int i = 0; i <ss.size();i++)
        {
            long lId = ss.at(i);

            McDbEntity ent = new McDbEntity (lId);

            // 得到对象的层名.
            Log.e("LayerName",ent.layerName());

            String sName =  MxFunction.getTypeName(lId);

            if(sName.equals("McDbLine"))
            {
                McDbLine line = new McDbLine(ss.at(i));

                McGePoint3d sPt = line.getStartPoint();
                McGePoint3d ePt = line.getEndPoint();

                String sT;
                sT = String.format("sPt:%f,%f,%f,ePt:%f,%f,%f",sPt.x,sPt.y,sPt.z,ePt.x,ePt.y,ePt.z);

                Log.e("Linedata",sT);
            }
            else if(sName.equals("McDbPolyline"))
            {
                McDbPolyline pl = new McDbPolyline(ss.at(i));
                double dA = pl.getArea();

                String sA;
                sA = String.format("Area:%f",dA);

                Log.e("McDbPolyline Area:", sA);

                for (int j = 0; j  < pl.numVerts(); j ++) {
                    McGePoint3d pt = pl.getPointAt(j );
                    double dBulge = pl.getBulgeAt(j );

                    String sT2;
                    sT2 = String.format("pt:%f,%f,%f,dBulge:%f", pt.x, pt.y, pt.z,dBulge);

                    Log.e("McDbPolyline Point:", sT2);

                    if(dBulge > 0.001)
                    {
                        McGePoint3d pt2;

                        if(j  ==  pl.numVerts() - 1)
                        {
                            pt2 = pl.getPointAt(0);
                        }
                        else
                        {
                            pt2 = pl.getPointAt(j  + 1);
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
            else if(sName.equals("McDb3dPolyline"))
            {
                McDb3DPolyline pl = new McDb3DPolyline(ss.at(i));
                int iNum = pl.numVerts();
                int j = 0;
                for(; j <iNum;j++)
                {
                    McGePoint3d pt =  pl.getVertexAt(j);
                    String sT;
                    sT = String.format("pt:%f,%f,%f",pt.x,pt.y,pt.z);
                    Log.e("McDb3dPolyline Vertex ",sT);
                }
            }
            else if(sName.equals("McDbCircle"))
            {
                McDbCircle cir = new McDbCircle(ss.at(i));

                McGePoint3d cen = cir.getCenter();
                double fR = cir.getRadius();

                String sT;
                sT = String.format("cen:%f,%f,r:%f",cen.x,cen.y,fR);

                Log.e("Circledata",sT);
            }

            else if(sName.equals("McDbPoint"))
            {
                McDbPoint point = new McDbPoint(ss.at(i));

                McGePoint3d pos = point.position();


                String sT;
                sT = String.format("Point pos:%f,%f",pos.x,pos.y);

                Log.e("McDbPoint",sT);
            }

            else if(sName.equals("McDbText"))
            {
                McDbText txt = new McDbText(ss.at(i));

                McGePoint3d pos = txt.position();

                String sTxt = txt.textString();
                double dH = txt.height();

                String sT;
                sT = String.format(" pos:%f,%f,Txt:%s,H:%f",pos.x,pos.y,sTxt,dH);

                Log.e("McDbText",sT);
            }

            else if(sName.equals("McDbMText"))
            {
                McDbMText txt = new McDbMText(ss.at(i));

                McGePoint3d pos = txt.location();

                String sTxt = txt.contents();
                double dH = txt.textHeight();

                String sT;
                sT = String.format(" pos:%f,%f,Txt:%s,H:%f",pos.x,pos.y,sTxt,dH);

                Log.e("McDbMText",sT);
            }

            else if(sName.equals("McDbEllipse"))
            {
                McDbEllipse ellipse = new McDbEllipse(ss.at(i));
                McGePoint3d cen = ellipse.center();
                McGeVector3d major = ellipse.majorAxis();
                double radius = ellipse.radiusRatio();
                double sang = ellipse.startAngle();
                double eang = ellipse.endAngle();

                String sT;
                sT = String.format(" cen:%f,%f,major:%f,%f,radius:%f,sang:%f,eang:%f",cen.x,cen.y,major.x,major.y,radius,sang,eang);

                Log.e("McDbEllipse",sT);

            }

            else if(sName.equals("McDbBlockReference"))
            {
                McDbBlockReference blkRef = new McDbBlockReference(lId);
                McDbBlockTableRecord blkRec = new McDbBlockTableRecord( blkRef.blockTableRecord());
                Log.e("BlkName:",blkRec.getName());

                long[] allAtt = blkRef.getAllAttribute();
                if(allAtt != null) {
                    for (int j = 0; j < allAtt.length; j++) {
                        McDbAttribute att = new McDbAttribute(allAtt[j]);
                        Log.e("tagConst:", att.tagConst());
                        Log.e("textString:", att.textString());

                        //att.setTextString("zzzzzzzzzzzz");
                    }
                }
                // blkRef.assertWriteEnabled();

            }
        }
    }

    public static void TestGetDimText(){

        long lId = MrxDbgUtils.selectEnt("点标注对象:");
        if(lId == 0)
            return;


        String sName =  MxFunction.getTypeName(lId);
        if(sName.equals("McDbAlignedDimension") || sName.equals("McDbRotatedDimension")){
            McDbDimension dim = new McDbDimension(lId);
            MxResbuf ret = dim.getExplodeText();
            if(ret.getCount() < 2) return;

            String sText = ret.atString(0);
            McGePoint3d pos = ret.atPoint(1);
            String sT;
            sT = String.format(" text:%s,pos:%f,%f,%f",sText,pos.x,pos.y,pos.z);
            Log.e("dim text",sT);
        }
    }

    public  static void TestGetViewDocBound(){
        McGePoint3d minPt = new McGePoint3d();
        McGePoint3d maxPt = new McGePoint3d();
        MxFunction.getViewDocBound(minPt,maxPt);

        String sT;
        sT = String.format(" ViewDocBound:%f,%f,%f,%f",minPt.x,minPt.y,maxPt.x,maxPt.y);
        Log.e("ViewDocBound",sT);
    }
}
