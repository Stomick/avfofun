package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.team.noty.event.R;

public class VideoActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    public static void open(Context context, String id) {
        context.startActivity(new Intent(context, VideoActivity.class).putExtra(VIDEO, id));
    }

    static private String VIDEO = "id";
    static private String YOUTUBE_API_KEY = "";
    private YouTubePlayer player;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YOUTUBE_API_KEY = getString(R.string.google_maps_key);
        setContentView(R.layout.activity_video);
        id = getId(getIntent().getExtras());
        YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
        youTubeView.initialize(YOUTUBE_API_KEY, this);
    }

    private String getId(Bundle b) {
        if (b != null) {
            String vId = b.getString(VIDEO);
            if (vId != null && vId.contains("="))
                vId = vId.substring(vId.indexOf("=") + 1);
            return vId;
        }
        return "";
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult error) {
        Toast.makeText(this, "Ошибка! " + error.toString(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        player.loadVideo(id);
        this.player = player;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null && !player.isPlaying())
            player.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null && player.isPlaying())
            player.play();
    }

    @Override
    protected void onDestroy() {
        if (player != null)
            player.release();
        super.onDestroy();
    }
}

