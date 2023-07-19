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
import com.MxDraw.MxDrawWorldDraw;
import com.MxDraw.MxFunction;
import com.MxDraw.MxLibDraw;
import com.MxDraw.MxModifyTheColor;
import com.MxDraw.MxResbuf;

import org.cocos2dx.lib.Cocos2dxEditBox;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.ResizeLayout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MxDrawTest {

    // 设置点样式。
    public static void SetPointMode()
    {
        MxFunction.setSysVarDouble("PDSIZE", 2.0);

        // 圆+
        MxFunction.setSysVarLong("PDMODE",35);
    }

    public  static void DoUiTest()
    {


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
    public  static void DoMenuTest()
    {
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
}
