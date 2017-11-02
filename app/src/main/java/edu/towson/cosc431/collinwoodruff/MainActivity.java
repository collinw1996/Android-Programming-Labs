package edu.towson.cosc431.collinwoodruff;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonController{

    private ButtonFragment buttonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerFragment, new ContainerFragment())
                .commit();

        buttonFragment = (ButtonFragment)getSupportFragmentManager()
                .findFragmentById(R.id.buttonFragment);
        buttonFragment.setBtnText("People", "Other");
    }

    @Override
    public void button1() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFragment, new ContainerFragment())
                .commit();
    }

    @Override
    public void button2() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFragment, new HelloFragment())
                .commit();
    }
}
