package com.example.kamran.logingreentheme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Discount extends AppCompatActivity {
    EditText productnametxt,pricetxt,discounttxt,infotxt;
    Button publishbtn;
    //Camera components
    private Button picturebtn;
    private ImageView picturepr;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        mProgressDialog = new ProgressDialog(this);
        productnametxt = (EditText) findViewById(R.id.productname);
        pricetxt = (EditText) findViewById(R.id.price);
        discounttxt = (EditText) findViewById(R.id.discount);
        infotxt = (EditText) findViewById(R.id.info);
        publishbtn = (Button) findViewById(R.id.publish);
        mStorage = FirebaseStorage.getInstance().getReference();
        picturebtn = (Button) findViewById(R.id.pictureupl);
        picturepr = (ImageView) findViewById(R.id.picproduct);
        picturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Intent.ACTION_PICK);
                inten.setType("image/*");
                startActivityForResult(inten,GALLERY_INTENT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT && requestCode == RESULT_OK){
            mProgressDialog.setMessage("Uploading ...");
            mProgressDialog.show();
            Uri uri = data.getData();
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Discount.this,"Upload Done", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();

                }
            });
        }
    }
}
