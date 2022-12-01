package com.projectmanager.teamup.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.projectmanager.teamup.Modal.CardModal;
import com.projectmanager.teamup.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    ArrayList<CardModal> cardModals;
    String myUid;

    public CardAdapter(ArrayList<CardModal> cardModals, Context context) {
        this.cardModals = cardModals;
        this.context = context;
//        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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
        CardModal cardModal =  cardModals.get(position);
        holder.TVText.setText(cardModal.getTVTitle());
//        cardModal.getDescription();
        holder.BtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                // type of the content to be shared
                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(Intent.EXTRA_TEXT,cardModal.getDescription());
                // Body of the content
                String shareBody = "This is Where You can Write Your Problem";
////                sharingIntent.putExtra("TVTitle",cardModal.getTVTitle());
//
//
//                // subject of the content. you can share anything
//                String shareSubject = "Your Subject Here";
//
//                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//
//                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, cardModal.getTVTitle());



                context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        holder.DetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click On More Option", Toast.LENGTH_SHORT).show();
//                showMoreOption(holder.DetBtn,uid,);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardModals.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TVText;
        Button DetBtn,BtnShare;
//        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVText = itemView.findViewById(R.id.TVTitle);
            DetBtn = itemView.findViewById(R.id.DetailBtn);
            BtnShare = itemView.findViewById(R.id.idshareBtn);

        }
    }
}
