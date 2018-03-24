package william1099.com.design2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Main3Activity extends YouTubeBaseActivity {
    public static String apiKey = "AIzaSyCt_XfahSuhsNIQnmKC8uGESn6wp2GwrEo";
    YouTubePlayerView yutub;
    YouTubePlayer.OnInitializedListener init;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        yutub = (YouTubePlayerView) findViewById(R.id.yt);
        init = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("X9AU5I-0njs");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(Main3Activity.this, "Something wrong happening", Toast.LENGTH_LONG).show();
            }
        };

        yutub.initialize(apiKey, init);
    }
}
