package com.example.kamran.logingreentheme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Discount extends AppCompatActivity {
    EditText productnametxt,pricetxt,discounttxt,infotxt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
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
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReferenceFromUrl("https://tloffersfinder.firebaseio.com/Discount");
        picturepr = (ImageView) findViewById(R.id.picproduct);
        picturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Intent.ACTION_PICK);
                inten.setType("image/*");
                startActivityForResult(inten,GALLERY_INTENT);
            }
        });
        publishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddiscount();

            }
        });

    }
    private void adddiscount(){
        String productname = productnametxt.getText().toString().trim();
        String price = pricetxt.getText().toString().trim();
        String discount = discounttxt.getText().toString().trim();
        String descript = infotxt.getText().toString().trim();
        if(!TextUtils.isEmpty(productname)){
            String id = databaseReference.push().getKey();
            Shops shops = new Shops(id,productname,price,discount,descript);

            databaseReference.child(id).setValue(shops);
            Toast.makeText(this,"Επιτυχής εγγραφή προσφοράς",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Πληκτρολογήστε όνομα προσφοράς!",Toast.LENGTH_LONG).show();
        }
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
