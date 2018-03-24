package william1099.com.design2;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;


public class FragmentB extends Fragment {

    ImageView img;
    View v;
    RelativeLayout rl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_b, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rl = (RelativeLayout) v.findViewById(R.id.contentimg);
        img = (ImageView) v.findViewById(R.id.img1);

        Picasso.with(getContext()).load(Uri.parse("http://img.youtube.com/vi/X9AU5I-0njs/0.jpg")).centerCrop()
                .placeholder(R.drawable.ic_action_name).error(R.drawable.ic_action_error).resize(500, 300)
                .into(img);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Main3Activity.class));
            }
        });
    }
}
