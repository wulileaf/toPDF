package com.example.rcdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class PDF extends AppCompatActivity implements View.OnClickListener {

    private int width, height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 布局自己加，就一个Button，点击后进行保存操作即可
//        setContentView();

        // 获取的是整个屏幕的宽高
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;// 屏幕宽度（像素）
        height = dm.heightPixels;

    }

    @Override
    public void onClick(View view) {
//        case R.id.toPdf:
//        toPDF();
//        break;
    }

    private void toPDF() {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = Environment.getExternalStoragePublicDirectory("/ABC");
            if (file.exists()) {

            } else {
                file.mkdir();
            }

            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(width, height, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();

            // s1
            Paint mPaint = new Paint();
            mPaint.setStrokeWidth(10);
            mPaint.setTextSize(60);
            mPaint.setFakeBoldText(true);
            mPaint.setColor(Color.BLACK);
            mPaint.setTextAlign(Paint.Align.CENTER);
            String text = "***报告";
            canvas.drawText(text, width / 2, 70, mPaint);

            Paint mPaint1 = new Paint();
            mPaint1.setStrokeWidth(3);
            mPaint1.setTextSize(30);
            mPaint1.setColor(Color.BLACK);
            mPaint1.setTextAlign(Paint.Align.LEFT);
            String text1 = "时间：2022-05-13";
            canvas.drawText(text1, 10, 170, mPaint1);

            Paint mPaint2 = new Paint();
            mPaint2.setStrokeWidth(5);
            mPaint2.setTextSize(30);
            mPaint2.setFakeBoldText(true);
            mPaint2.setColor(Color.BLACK);
            mPaint2.setTextAlign(Paint.Align.LEFT);
            String text2 = "参数1：";
            canvas.drawText(text2, 10, (float) (210), mPaint2);

            String text3 = "参数2：0.0";
            canvas.drawText(text3, 10, (float) (250), mPaint1);

            document.finishPage(page);
            File file1 = new File(file, "android.pdf");
            try {
                FileOutputStream mFileOutStream = new FileOutputStream(file1);
                document.writeTo(mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            document.close();
        }

    }
}
