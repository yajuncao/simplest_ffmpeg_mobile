/**
 * 最简单的基于FFmpeg的视频解码器-安卓 - 单库版
 * Simplest FFmpeg Android Decoder - One Library
 * 
 * 雷霄骅 Lei Xiaohua
 * leixiaohua1020@126.com
 * 中国传媒大学/数字电视技术
 * Communication University of China / Digital TV Technology
 * http://blog.csdn.net/leixiaohua1020
 * 
 * 本程序是安卓平台下最简单的基于FFmpeg的视频解码器。
 * 它可以将输入的视频数据解码成YUV像素数据。
 * 
 * This software is the simplest decoder based on FFmpeg in Android. 
 * It can decode video stream to raw YUV data.
 * 
 */
package com.leixiaohua1020.sffmpegandroiddecoder;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		Button startButton = (Button) this.findViewById(R.id.button_start);
		final EditText urlEdittext_input= (EditText) this.findViewById(R.id.input_url);
		final EditText urlEdittext_output= (EditText) this.findViewById(R.id.output_url);
		
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0){

				String folderurl=Environment.getExternalStorageDirectory().getPath();
				
				String urltext_input=urlEdittext_input.getText().toString();
		        String inputurl=folderurl+"/"+urltext_input;
		        
		        String urltext_output=urlEdittext_output.getText().toString();
		        String outputurl=folderurl+"/"+urltext_output;
		        
		        Log.i("inputurl",inputurl);
		        Log.i("outputurl",outputurl);
		    
		        decode(inputurl,outputurl);
		        
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //JNI
    public native int decode(String inputurl, String outputurl);
    
    static{
    	System.loadLibrary("ffmpeg");
    	System.loadLibrary("sffdecoder");
    }
}
