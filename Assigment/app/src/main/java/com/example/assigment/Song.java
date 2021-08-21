package com.example.assigment;

public class Song {
    private String songName;
    private int file;

    public Song(String songName, int file) {
        this.songName = songName;
        this.file = file;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
