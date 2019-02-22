package com.annotion.ruiyi.huibain;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {
    //public String path = "/storage/emulated/0/ARM/mian";
    public String path = "/data/test";
    PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity","这是log");

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //execCmd("./"+path);

                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "chendongqi_wakelock");
                wakeLock.acquire();
            }
        });


    }
    @Override
    protected void onDestroy() {
        wakeLock.release();
        super.onDestroy();
    }


    public static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            if(sourceChannel != null) {
                sourceChannel.close();
            }
            if(destChannel != null) {
                destChannel.close();
            }
        }
    }



    public void execCmd(String cmd) {
        String result = "";
        OutputStream out = null;
        DataInputStream dis = null;
        try {
            Process p = Runtime.getRuntime().exec("su");
            out = p.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dis = new DataInputStream(p.getInputStream());
            dos.writeBytes( "chmod 777 "+path+"\n");
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.close();
            out.close();
            int value = p.waitFor();

            String line = null;
            while ((line = dis.readLine()) != null) {
                Log.d("result", line);
                result += line;
            }
            Log.e("MianActivity", "执行结果int：" + value +",解释："+result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MianActivity", "异常：" + e.toString());

        }
    }
}
