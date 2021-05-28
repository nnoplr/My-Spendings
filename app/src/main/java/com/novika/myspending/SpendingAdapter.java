package com.novika.myspending;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpendingAdapter extends RecyclerView.Adapter<SpendingAdapter.ViewHolder>{

    Context ctx;

    ArrayList<Spendings> spendinglist;

    @NonNull
    @Override
    public SpendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflater
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.spending_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendingAdapter.ViewHolder holder, int position) {

        String tempt = String.valueOf(spendinglist.get(position).getNominal());

        String tempt2="", tempt3="";
        int len = tempt.length(), count=0;

        for(int N = len-1; N>=0; N-- ){

            tempt2 = tempt2 + tempt.charAt(N);
            count++;

            if (count%3==0 && count < len){
                tempt2 = tempt2 + ",";
            }
        }

        int len2 = tempt2.length();

        for(int N = len2-1; N>=0; N--){
            tempt3 = tempt3 + tempt2.charAt(N);
        }


        holder.tvName.setText(spendinglist.get(position).getName());
        holder.tvDate.setText(spendinglist.get(position).getDate());
        holder.tvNominal.setText(tempt3);

        holder.frSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, EditData.class);

                intent.putExtra("idDipilih", position+1);

                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return spendinglist.size();
    }

    public void setArrayListData(ArrayList<Spendings>spendinglist){
        this.spendinglist = spendinglist;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDate, tvNominal;
        FrameLayout frSpending;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.textName);
            tvDate = itemView.findViewById(R.id.textDate);
            tvNominal = itemView.findViewById(R.id.textNominal);
            frSpending = itemView.findViewById(R.id.frSpendings);

        }
    }
}
