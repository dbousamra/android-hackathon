package com.example.androidhackathon.photos;

public class Photo {

    private final String path;
    private final String latitude;
    private final String longitude;

    public Photo(String path, String latitude, String longitude) {
        this.path = path;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPath() {
        return path;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "path='" + path + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
