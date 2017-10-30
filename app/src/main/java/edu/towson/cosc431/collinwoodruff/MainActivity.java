package edu.towson.cosc431.collinwoodruff;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ButtonFragment buttonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // default to our containter fragment.
        // on button2 click, we need to swap out the fragment with another...
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerFragment, new ContainerFragment())
                .commit();

        buttonFragment = (ButtonFragment)getSupportFragmentManager()
                .findFragmentById(R.id.buttonFragment);
        buttonFragment.setButtonText("People", "Other");
        buttonFragment.setOnButtonClick(this);
    }
}
