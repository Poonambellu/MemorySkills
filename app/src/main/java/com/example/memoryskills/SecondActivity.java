package com.example.memoryskills;

import android.graphics.PixelFormat;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    Button button1;
    VideoView videoView1;
    MediaController mediacontroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button buttonPlayVideo2 = (Button)findViewById(R.id.button1);

        getWindow().setFormat(PixelFormat.UNKNOWN);

//displays a video file
        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView1);



        String uriPath2 = "android.resource://com.example.memoryskills/"+R.raw.intro;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();

        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView1);
                // VideoView mVideoView = new VideoView(this);
                String uriPath = "android.resource://com.example.memoryskills/" + R.raw.intro;

                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);

                videoView1.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();



            }
        });

    }

}
