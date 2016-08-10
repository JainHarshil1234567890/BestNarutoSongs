package com.justforyou.bestnarutosongs;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsListActivity extends AppCompatActivity
{

    public boolean IsPaused = false;
    public int Old_Song = -326523;
    private MediaPlayer mediaplayer;
    private AudioManager mAudioManager;
    public ImageButton P_and_P = null;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange){
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mediaplayer.pause();
                IsPaused = true;
                P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaplayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                if(mediaplayer != null)
                {
                    mediaplayer.pause();
                    IsPaused = true;
                    P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_songs_list);


        final TextView NameD = (TextView) findViewById(R.id.NameD);
        final ImageView ImageD = (ImageView) findViewById(R.id.ImageD);
        final TextView RateD = (TextView) findViewById(R.id.RateD);
        P_and_P = (ImageButton) findViewById(R.id.P_and_P);
        final ImageButton Stop = (ImageButton) findViewById(R.id.Stop);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Songs> Song = new ArrayList<>();
        Song.add(new Songs("Anger", "Rate: 4.5", R.raw.anger, R.drawable.anger ));
        Song.add(new Songs("Daylight Of Konoha", "Rate: 4.7", R.raw.d_o_k, R.drawable.d_o_k ));
        Song.add(new Songs("Experienced Many Battles", "Rate: 4.0", R.raw.experienced_many_battles, R.drawable.e_m_b ));
        Song.add(new Songs("Gekiha", "Rate: 4.0", R.raw.gekiha, R.drawable.gekiha ));
        Song.add(new Songs("Genshi(Mt. Neoboku)", "Rate: 2.9", R.raw.genshi, R.drawable.genshi));
        Song.add(new Songs("Girei(Pain's Theme)", "Rate: 4.5", R.raw.girei, R.drawable.girei));
        Song.add(new Songs("Hidan", "Rate: 3.0", R.raw.hidan, R.drawable.hidan ));
        Song.add(new Songs("Homecoming", "Rate: 3.5", R.raw.homecoming, R.drawable.homecoming));
        Song.add(new Songs("I said I'm Naruto", "Rate: 5.0", R.raw.i_said_i_am_naruto, R.drawable.i_s_i_a_n));
        Song.add(new Songs("Kakuzu", "Rate: 5.0", R.raw.kakuzu, R.drawable.kakuzu));
        Song.add(new Songs("Kokuten", "Rate: 5.0", R.raw.kokuten, R.drawable.kokuten));
        Song.add(new Songs("Naruto Main Theme", "Rate: 5.0", R.raw.naruto_main_theme, R.drawable.narutomt ));
        Song.add(new Songs("Saika", "Rate: 4.9", R.raw.saika, R.drawable.saika));
        Song.add(new Songs("Senya(Itachi's Theme)", "Rate: 5.0", R.raw.senya, R.drawable.senya ));
        Song.add(new Songs("Shirotsumekusa", "Rate: 3.0", R.raw.shirotsumekusa, R.drawable.shirot ));
        Song.add(new Songs("Turn Over", "Rate: 4.6", R.raw.turn_over, R.drawable.turn_over));

        SongsAdapter SongAdapter = new SongsAdapter(this, Song, R.color.Main_Color);
        ListView listView = (ListView) findViewById(R.id.list_Col);
        listView.setAdapter(SongAdapter);

        P_and_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaplayer != null) {
                    if (!mediaplayer.isPlaying() || IsPaused) {
                        P_and_P.setImageResource(R.drawable.ic_pause_white_48dp);
                        IsPaused = false;
                        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            mediaplayer.start();
                            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                                    IsPaused = true;
                                }
                            });
                        }
                    } else {
                        P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                        IsPaused = true;
                        mediaplayer.pause();
                    }
                }

            }
        });


        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaplayer != null) {
                    P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                    mediaplayer.pause();
                    mediaplayer.seekTo(0);
                    IsPaused = true;
                }
            }
        });



        listView.setOnItemClickListener
                (new AdapterView.OnItemClickListener()
                 {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                     {
                         int Current_Song;
                         Songs song = Song.get(i);

                         //If mediaPlayer is not used before, this will make oldsong as present song.
                         if (Old_Song == -326523)
                         {
                             Old_Song = song.getSong();
                         }

                         Current_Song = song.getSong();

                         ImageView IVP_P = (ImageView) findViewById(R.id.P_PImage);


                         //If mediaPlayer is paused.
                         if (IsPaused)
                         {
                             P_and_P.setImageResource(R.drawable.ic_pause_white_48dp);
                             //If the song paused is same as the new song.
                             if(Current_Song == Old_Song)
                             {

                                 mediaplayer.start();
                             }

                             //If the song Paused is not the new song.
                             else
                             {
                                 if (mediaplayer != null)
                                 {
                                     mediaplayer.release();
                                     mediaplayer = null;
                                 }

                                 int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                                 if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                     mediaplayer = mediaplayer.create(SongsListActivity.this, song.getSong());
                                     Old_Song = song.getSong();
                                     NameD.setText(song.getNameOfSong());
                                     RateD.setText(song.getDeveloperRate());
                                     ImageD.setImageResource(song.getImage());
                                     mediaplayer.start();
                                     mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                         @Override
                                         public void onCompletion(MediaPlayer mediaPlayer) {
                                             P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                                             IsPaused = true;
                                         }
                                     });
                                 }
                             }
                             IsPaused = false;

                         }

                         else if (mediaplayer != null)
                         {
                             //If mediaPlayer is already Playing a song.
                             if (mediaplayer.isPlaying()) {
                                 P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                                 mediaplayer.pause();
                                 IVP_P.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                                 IsPaused = true;
                             }
                         }

                         //If mediaPlayer is used for first time and if mediaPlayer is neither paused
                         else
                         {

                             if (mediaplayer != null)
                             {
                                 mediaplayer.release();
                                 mediaplayer = null;
                             }

                             int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                             if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                 mediaplayer = mediaplayer.create(SongsListActivity.this, song.getSong());
                                 Old_Song = song.getSong();
                                 NameD.setText(song.getNameOfSong());
                                 RateD.setText(song.getDeveloperRate());
                                 ImageD.setImageResource(song.getImage());
                                 mediaplayer.start();
                                 P_and_P.setImageResource(R.drawable.ic_pause_white_48dp);

                                 mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                     @Override
                                     public void onCompletion(MediaPlayer mediaPlayer) {
                                         P_and_P.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                                         IsPaused = true;
                                     }
                                 });
                             }
                         }

                     }
                 }
                );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaplayer != null)
        {
            mediaplayer.release();
            mediaplayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
