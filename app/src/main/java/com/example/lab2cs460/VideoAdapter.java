package com.example.lab2cs460;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.media3.common.PlaybackException;
import java.util.List;

/**
 * Adapter that binds a list of {@link Video} objects to full-screen pages
 * inside a ViewPager2. Each page plays its video while it is on screen.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<Video> videos;

    /**
     * Creates the adapter.
     *
     * @param videos the list of videos to display
     */
    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    /** Start playback when the page becomes visible. */
    @Override
    public void onViewAttachedToWindow(@NonNull VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.startPlayer();
    }

    /** Release the player when the page is swiped away. */
    @Override
    public void onViewDetachedFromWindow(@NonNull VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.releasePlayer();
    }

    /**
     * Holds the views and the ExoPlayer for one video page.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        private final PlayerView playerView;
        private final TextView titleText;
        private final TextView idText;
        private final TextView descriptionText;
        private ExoPlayer player;
        private String currentUrl;

        /**
         * Looks up the views for a page.
         *
         * @param itemView the inflated item_video layout
         */
        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            titleText = itemView.findViewById(R.id.titleText);
            idText = itemView.findViewById(R.id.idText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
        }

        /**
         * Fills the page with a video's text details.
         *
         * @param video the video to show
         */
        void bind(Video video) {
            titleText.setText(video.getTitle());
            idText.setText("ID: " + video.getId());
            descriptionText.setText(video.getDescription());
            currentUrl = video.getUrl();
        }

        /** Creates an ExoPlayer and starts playing the current video in a loop. */
        /** Creates an ExoPlayer and starts playing the current video in a loop. */
        void startPlayer() {
            if (player != null || currentUrl == null) {
                return;
            }
            Context context = itemView.getContext();
            player = new ExoPlayer.Builder(context).build();
            playerView.setPlayer(player);
            player.addListener(new Player.Listener() {
                @Override
                public void onPlayerError(@NonNull PlaybackException error) {
                    android.util.Log.e("SwipeVideo", "Playback error", error);
                    android.widget.Toast.makeText(context,
                            "Error: " + error.getErrorCodeName(),
                            android.widget.Toast.LENGTH_LONG).show();
                }
            });
            player.setMediaItem(MediaItem.fromUri(currentUrl));
            player.setRepeatMode(Player.REPEAT_MODE_ONE);
            player.prepare();
            player.play();
        }
        /** Stops playback and frees the player's resources. */
        void releasePlayer() {
            if (player != null) {
                playerView.setPlayer(null);
                player.release();
                player = null;
            }
        }
    }
}