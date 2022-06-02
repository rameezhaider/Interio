package com.google.ar.core.examples.java.helloar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class upload extends AppCompatActivity {

    // views for button
    private Button btnSelect, btnUpload;

    // view for image view

    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
//    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // initialise views
        btnSelect = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imgView);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // on pressing btnSelect SelectImage() is called
        btnSelect.setOnClickListener(v -> SelectImage());

        // on pressing btnUpload uploadImage() is called
        btnUpload.setOnClickListener(v -> uploadImage());
    }

    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == 1
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            imageView.setImageURI(filePath);
//            try {
//
//                // Setting image on image view using Bitmap
//                Bitmap bitmap = MediaStore
//                        .Images
//                        .Media
//                        .getBitmap(
//                                getContentResolver(),
//                                filePath);
//                imageView.setImageBitmap(bitmap);
//            }
//
//            catch (IOException e) {
//                // Log the exception
//                e.printStackTrace();
//            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        final  ProgressDialog pd;
        pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image");
        pd.show();

        final String randomkey = UUID.randomUUID().toString();
        StorageReference riversref = storageReference.child("images/"+ randomkey);
        riversref.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(
                            taskSnapshot -> {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
                                        pd.setMessage(
                                        "Uploaded "
                                                + (int)progress + "%");
                            });

//        if (filePath != null) {
//
//            // Code for showing progressDialog while uploading
//            ProgressDialog progressDialog
//                    = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//            // Defining the child of storageReference
//            StorageReference ref
//                    = storageReference
//                    .child(
//                            "images/"
//                                    + UUID.randomUUID().toString());
//
//            // adding listeners on upload
//            // or failure of image
//            // Progress Listener for loading
//            // percentage on the dialog box
//            ref.putFile(filePath)
//                    .addOnSuccessListener(
//                            taskSnapshot -> {
//
//                                // Image uploaded successfully
//                                // Dismiss dialog
//                                progressDialog.dismiss();
//                                Toast
//                                        .makeText(upload.this,
//                                                "Image Uploaded!!",
//                                                Toast.LENGTH_SHORT)
//                                        .show();
//                            })
//
//                    .addOnFailureListener(e -> {
//
//                        // Error, Image not uploaded
//                        progressDialog.dismiss();
//                        Toast
//                                .makeText(upload.this,
//                                        "Failed " + e.getMessage(),
//                                        Toast.LENGTH_SHORT)
//                                .show();
//                    })
//                    .addOnProgressListener(
//                            taskSnapshot -> {
//                                double progress
//                                        = (100.0
//                                        * taskSnapshot.getBytesTransferred()
//                                        / taskSnapshot.getTotalByteCount());
//                                progressDialog.setMessage(
//                                        "Uploaded "
//                                                + (int)progress + "%");
//                            });
//        }


    }
}