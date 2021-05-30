package com.example.studentapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ChildViewHolderJava extends RecyclerView.ViewHolder {

    ImageView img;
    TextView tv1, tv2;
    CardView cardView;

    public ChildViewHolderJava(@NonNull @NotNull View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.srimg1);
        tv1 = (TextView) itemView.findViewById(R.id.srtv1);
        tv2 = (TextView) itemView.findViewById(R.id.srtv2);
        cardView = (CardView) itemView.findViewById(R.id.cardView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handel yout event here;
//                itemView.getContext().startActivity(new Intent(itemView.getContext(), DashBoard.class));
            }
        });
    }


}
