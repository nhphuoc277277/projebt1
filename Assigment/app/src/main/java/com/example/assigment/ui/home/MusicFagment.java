package com.example.assigment.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assigment.R;
import com.example.assigment.Song;

import java.util.ArrayList;

public class MusicFagment extends Fragment {
    ImageView next,pre,play;
    ArrayList<Song> arraySong;
    int index = 0;
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment,container,false);
        next = view.findViewById(R.id.next);
        pre = view.findViewById(R.id.previous);
        play = view.findViewById(R.id.play);
        arraySong = new ArrayList<>();
        arraySong.add(new Song("ddaeng",R.raw.abcd));
        arraySong.add(new Song("Nắm đôi bàn tay",R.raw.namdoibantay));
        arraySong.add(new Song("Hãy trao cho anh",R.raw.haytraochoanh));
        arraySong.add(new Song("Dù cho mai về sau",R.raw.duchomaivesau));

       khoitao();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index>arraySong.size()-1){
                    index = 0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoitao();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if(index<0){
                    index = arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoitao();
                mediaPlayer.start();
                play.setImageResource(R.drawable.pause);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.play);
                }else {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                }
            }
        });
        return view;
    }
    private void khoitao(){
        mediaPlayer = MediaPlayer.create(getContext(),arraySong.get(index).getFile());
    }
}
