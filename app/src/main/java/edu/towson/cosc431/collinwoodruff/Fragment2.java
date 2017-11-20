package edu.towson.cosc431.collinwoodruff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    private OnFinishButtonListener listener;

    interface OnFinishButtonListener{
        void onFinishButtonClicked();
    }

    public void setOnFinishButtonListener(OnFinishButtonListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        Button btn = view.findViewById(R.id.secondButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFinishButtonClicked();
            }
        });
        return view;
    }
}
