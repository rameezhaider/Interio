package com.google.ar.core.examples.java.helloar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Review extends AppCompatActivity {

    EditText text;
    Button btnReview;
    DatabaseReference ref;
    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        text = findViewById(R.id.txt1);
        btnReview = findViewById(R.id.button);
        feedback = new Feedback();
        ref = FirebaseDatabase.getInstance().getReference().child("Review");


        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedback.setReview(text.getText().toString());
                ref.push().setValue(feedback);
                Toast.makeText(Review.this,"Review is Submitted",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
