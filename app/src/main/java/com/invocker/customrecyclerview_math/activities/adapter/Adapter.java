package com.invocker.customrecyclerview_math.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.invocker.customrecyclerview_math.R;
import com.invocker.customrecyclerview_math.activities.modelgame.Word;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.WordViewHodel> {
    private List<Word> words;
    private LayoutInflater mlayoutInflater;
    private Context context;

    public Adapter(Context context, List<Word> words) {
        this.words = words;
        mlayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_word, parent, false);
        return new WordViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHodel holder, int position) {
        Word word = words.get(position);
        holder.txtResult.setText(word.getResult());
        holder.txtOperator.setText(word.getOperator());
    }

    void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    public Word getWordAtPosition(int position) {
        return words.get(position);
    }

    @Override
    public int getItemCount() {
        return words == null ? 0 : words.size();
    }

    static class WordViewHodel extends RecyclerView.ViewHolder {

        private TextView txtOperator;
        private TextView txtResult;


        public WordViewHodel(@NonNull View itemView) {
            super(itemView);
            txtOperator = itemView.findViewById(R.id.txt_operator);
            txtResult = itemView.findViewById(R.id.txt_result);
        }

    }
}
