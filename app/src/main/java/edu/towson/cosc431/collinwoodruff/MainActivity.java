package edu.towson.cosc431.collinwoodruff;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Controller {

    PersonFragment person = new PersonFragment();
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void person(int position, ArrayList<String> person, ArrayList<String> age) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        this.person = (PersonFragment) fragmentManager.findFragmentById(R.id.person);
        this.person.person(position, person, age);
    }
}
