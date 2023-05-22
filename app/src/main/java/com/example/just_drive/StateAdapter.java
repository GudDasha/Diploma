package com.example.just_drive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<State> states;
    interface OnStateClickListener{
        void onState(State state, int position);
    }
    public OnStateClickListener onStateClickListener;
    public StateAdapter(Context context, List<State> states, OnStateClickListener onStateClickListener){
        this.states=states;
        this.inflater = LayoutInflater.from(context);
        this.onStateClickListener = onStateClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_buttons,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State state = states.get(position);
        holder.name.setText(state.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStateClickListener.onState(state,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
        }
    }
}
