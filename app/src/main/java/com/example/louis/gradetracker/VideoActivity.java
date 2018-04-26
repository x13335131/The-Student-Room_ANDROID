package com.example.louis.gradetracker;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;
import android.widget.MediaController;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        //sets video content
        setContentView(R.layout.activity_video);

        //get the video view from resources
        VideoView vView = (VideoView)findViewById(R.id.videoView);

        //use this to get touch events
        vView.requestFocus();

        //load video from resource folder
        //set video path
        String vSource ="android.resource://com.example.louis.gradetracker/" +R.raw.html;

        //set the video URI, passing the vSource as a URI
        vView.setVideoURI(Uri.parse(vSource));

        //enable this if you want to enable video controllers, such as pause and forward
        vView.setMediaController(new MediaController(this));

        //plays the movie
        vView.start();
    }
}
