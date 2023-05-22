package com.example.just_drive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class StateStatisticAdapter extends RecyclerView.Adapter<StateStatisticAdapter.ViewHolder> {

    private final Context context;
    private final List<StateStatistic> states;

    public StateStatisticAdapter(Context context, List<StateStatistic> states){
        this.states = states;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_statistic,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StateStatistic state = states.get(position);
        holder.correct.setText(state.getCorrect());
        holder.date.setText(state.getDate());
        holder.mistakes.setText(state.getMistakes());
        holder.result.setText(state.getResult());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView correct;
        final TextView date;
        final TextView mistakes;
        final TextView result;
        public ViewHolder(View view){
            super(view);
            correct = view.findViewById(R.id.correct);
            date = view.findViewById(R.id.date);
            mistakes = view.findViewById(R.id.uncorrect);
            result = view.findViewById(R.id.type);
        }
    }
}
