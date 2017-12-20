package com.example.kamran.logingreentheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class interfaceu extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaceu);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myref= FirebaseDatabase.getInstance().getReference().child("/Discount");
        FirebaseRecyclerAdapter<DiscountDB,BlogViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<DiscountDB,BlogViewHolder>(
                DiscountDB.class,
                R.layout.activity_interfaceu,
                BlogViewHolder.class,
                myref
        ) {


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, DiscountDB model, int position) {
               viewHolder.setDescription();
               viewHolder.setTitle(model.getProductname());
               viewHolder.setImage(model.getPhotopath());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        ImageView imageView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.title);
            textView_decription = (TextView) itemView.findViewById(R.id.description);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
        public void setTitle(String title)
        {
           textView_title.setText(title+"abc");
        }
        public void setDescription()
        {
            textView_decription.setText("abc");
        }
        public void setImage(String image)
        {
        Picasso.with(mView.getContext())
        .load(image)
        .into(imageView);
        }
    }
}