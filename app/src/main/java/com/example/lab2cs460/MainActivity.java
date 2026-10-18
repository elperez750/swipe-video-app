package com.example.lab2cs460;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * Main screen of the Swipe Video app. Shows a vertically swipeable
 * feed of videos, each with a title, description, and unique ID.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Sets up the ViewPager2 with the list of videos.
     *
     * @param savedInstanceState saved state from a previous run, if any
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new VideoAdapter(buildVideoList()));
    }

    /**
     * Builds the list of videos shown in the app.
     *
     * @return a list of Video objects
     */
    private List<Video> buildVideoList() {
        String base = "https://storage.googleapis.com/gtv-videos-bucket/sample/";
        List<Video> list = new ArrayList<>();
        list.add(new Video("10000001", "Cat licking itself",
                "A beautiful cat licking itself",
                "https://github.com/elperez750/swipe-video-app/raw/main/app/videos/cat_video.mp4"));
        list.add(new Video("10000002", "Big Buck Bunny",
                "A giant rabbit deals with three pesky rodents.",
                "https://www.w3schools.com/html/mov_bbb.mp4"));
        list.add(new Video("10000003", "Bear Clip",
                "A short nature clip for testing playback.",
                "https://www.w3schools.com/html/movie.mp4"));
        list.add(new Video("10000004", "Sintel Trailer",
                "A short animated trailer from the Blender Foundation.",
                "https://media.w3.org/2010/05/sintel/trailer.mp4"));
        return list;
    }
}