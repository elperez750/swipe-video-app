package com.example.lab2cs460;

/**
 * Model class representing a single video in the swipe feed.
 */
public class Video {
    private final String id;
    private final String title;
    private final String description;
    private final String url;

    /**
     * Creates a new Video.
     *
     * @param id          unique ID shown on screen
     * @param title       the video title
     * @param description a brief description of the video
     * @param url         direct URL to the hosted .mp4 file
     */
    public Video(String id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    /** @return the unique video ID */
    public String getId() {
        return id;
    }

    /** @return the video title */
    public String getTitle() {
        return title;
    }

    /** @return the video description */
    public String getDescription() {
        return description;
    }

    /** @return the direct URL of the video file */
    public String getUrl() {
        return url;
    }
}