package com.huawei.touchgame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyDisplayInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard_RecycleViewAdapter extends RecyclerView.Adapter<Scoreboard_RecycleViewAdapter.MyViewHolder> {
    private ArrayList<scoremodel> scores;
    Context context;
    int rankingcount =1;



    public Scoreboard_RecycleViewAdapter(Context context, ArrayList<scoremodel> results) {
        this.context=context;
        this.scores=results;
    }



    @NonNull
    @Override
    public Scoreboard_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view_row, viewGroup, false);
        return new Scoreboard_RecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Scoreboard_RecycleViewAdapter.MyViewHolder holder, int i) {
        holder.BoardName.setText(scores.get(i).getBoardName());
        holder.BoardScore.setText(String.valueOf(scores.get(i).getBoardScore()));
        holder.BoardRanking.setText(String.valueOf(i+1));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView BoardName;
        TextView BoardScore;
        TextView BoardRanking;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            BoardName = itemView.findViewById(R.id.BoardName);
            BoardScore = itemView.findViewById(R.id.BoardScore);
            BoardRanking = itemView.findViewById(R.id.Ranking);

        }
    }
}
