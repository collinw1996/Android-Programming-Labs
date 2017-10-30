package edu.towson.cosc431.collinwoodruff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Collin on 10/23/2017.
 */

public class ButtonFragment extends Fragment {
    Button btn1;
    Button btn2;

    public void setButtonText(String btn1Text, String btn2Text){
        btn1.setText(btn1Text);
        btn2.setText(btn2Text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.button_frag, container, false);
        btn1 = view.findViewById(R.id.button_frag_btn_1);
        btn2 = view.findViewById(R.id.button_frag_btn_2);
        return view;
    }

    public void setOnButtonClick(MainActivity mainActivity) {
        btn1.setOnClickListener((View.OnClickListener) this);
        btn2.setOnClickListener((View.OnClickListener) this);
        switch(getView().getId()){
            case R.id.button_frag_btn_1:

                break;
            case R.id.button_frag_btn_2:

                break;
        }
    }
}
