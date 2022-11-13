package com.projectmanager.teamup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectmanager.teamup.Modal.CardModal;
import com.projectmanager.teamup.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    ArrayList<Object> cardModals;

    public CardAdapter(ArrayList<Object> cardModals, Context context) {
        this.cardModals = cardModals;
        this.context = context;
    }


    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_items_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        CardModal cardModal = (CardModal) cardModals.get(position);
        holder.TVText.setText(cardModal.getTVTitle());
    }

    @Override
    public int getItemCount() {
        return cardModals.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TVText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVText = itemView.findViewById(R.id.TVTitle);
        }
    }
}
