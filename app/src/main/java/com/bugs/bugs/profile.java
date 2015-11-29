package com.bugs.bugs;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ImageView;

public class profile extends ActionBarActivity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    //bind imageview with your xml's id
        mImageView = (ImageView)findViewById(R.id.imageView);
    //set resource for imageview
        mImageView.setImageResource(R.drawable.profilepic);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }




}
