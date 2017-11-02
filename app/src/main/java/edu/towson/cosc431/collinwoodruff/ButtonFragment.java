package edu.towson.cosc431.collinwoodruff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Collin on 10/23/2017.
 */

public class ButtonFragment extends Fragment implements View.OnClickListener {

    Button btn1;
    Button btn2;
    ButtonController buttonController;

    public static ButtonFragment createInstance(String btn1Text, String btn2Text) {
        ButtonFragment frag = new ButtonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("btn1", btn1Text);
        bundle.putString("btn2", btn2Text);
        frag.setArguments(bundle);
        return frag;
    }

    public void setBtnText(String btn1Text, String btn2Text) {
        btn1.setText(btn1Text);
        btn2.setText(btn2Text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_frag, container, false);
        btn1 = (Button) view.findViewById(R.id.button_frag_btn_1);
        btn2 = (Button) view.findViewById(R.id.button_frag_btn_2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        buttonController = (ButtonController)getActivity();
        Bundle args = getArguments();
        setBtnTextFromBundle(args);
        return view;
    }

    private void setBtnTextFromBundle(Bundle args) {
        if (args == null) return;
        btn1.setText(args.getString("btn1"));
        btn2.setText(args.getString("btn2"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_frag_btn_1:
                buttonController.button1();
                break;
            case R.id.button_frag_btn_2:
                buttonController.button2();
                break;
        }
    }
}
