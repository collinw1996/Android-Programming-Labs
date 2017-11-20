package edu.towson.cosc431.collinwoodruff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Fragment1.OnStartButtonListener, Fragment2.OnFinishButtonListener {

    Fragment1 firstFragment;
    Fragment2 secondFragment;
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstFragment = new Fragment1();
        secondFragment = new Fragment2();

        firstFragment.setOnStartButtonListener(this);
        secondFragment.setOnFinishButtonListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, firstFragment)
                .commit();

    }

    @Override
    public void onStartButtonClicked(String result) {
        Log.d("MainActivity" , result);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, secondFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFinishButtonClicked() {
        getSupportFragmentManager()
                .popBackStack();
    }
}
