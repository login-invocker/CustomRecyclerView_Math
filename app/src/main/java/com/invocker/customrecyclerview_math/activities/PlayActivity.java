package com.invocker.customrecyclerview_math.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.invocker.customrecyclerview_math.R;
import com.invocker.customrecyclerview_math.activities.adapter.Adapter;
import com.invocker.customrecyclerview_math.activities.modelgame.Gneratel;
import com.invocker.customrecyclerview_math.activities.modelgame.LevelM;
import com.invocker.customrecyclerview_math.activities.modelgame.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity {
    //time to play each level
    private final int TIME_PLAY_EACH_LEVEL = 3500;
    //define color backgroud
    private String[] arrColor = {"#99FF33", "#CC66FF", "#FF3300", "#330099", "#CC9933"};
    //view
    private Adapter adapter;
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private Timer timer;
    private TimerTask timerTask;
    private LevelM model;
    private Random random;
    private int score = 0;
    private List<Word> mwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //rm title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //rm notification
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        initView();
        random = new Random();
        model = Gneratel.generalLever(1);
        //show level info on screen
        displayNewLevel(model);
        createTimerTask();
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        if (direction == 4) {
                            if (model.correctWrong == false) {
                                score += 1;
                                nextLevel(score);
                            } else {
                                showGameOver(score);
                            }

                        }
                        if (direction == 8) {
                            if (model.correctWrong == true) {
                                score += 1;
                                nextLevel(score);
                            } else {
                                showGameOver(score);
                            }

                        }
                    }
                }
        );
        helper.attachToRecyclerView(recyclerView);
    }

    private void nextLevel(int score) {
        //   txtScore.setText(String.valueOf(score));
        cancelTimer();
        createTimerTask();
        model = Gneratel.generalLever(score);
        displayNewLevel(model);
    }

    private void displayNewLevel(LevelM model) {
        Word word = new Word(model.strOperator, model.strResult);
        mwords = new ArrayList<>();
        mwords.add(word);
        Toast.makeText(this, model.strOperator + "\n" + model.strResult, Toast.LENGTH_SHORT).show();
        adapter = new Adapter(this, mwords);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //ranrom color bacgrould
        int indexColor = random.nextInt(arrColor.length);
        relativeLayout.setBackgroundColor(Color.parseColor(arrColor[indexColor]));

    }

    private void createTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask, TIME_PLAY_EACH_LEVEL);
    }

    private void showGameOver(final int score) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 400 milliseconds
        v.vibrate(150);
        // Toast.makeText(this, "game over", Toast.LENGTH_SHORT).show();
        cancelTimer();

    }

    private void cancelTimer() {
        timer.cancel();
        timerTask.cancel();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_game);
        relativeLayout = findViewById(R.id.layout_game);
    }
}
