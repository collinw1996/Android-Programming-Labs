package edu.towson.cosc431.collinwoodruff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private OnStartButtonListener listener;

    interface OnStartButtonListener{
        void onStartButtonClicked(String result);
    }

    public void setOnStartButtonListener(OnStartButtonListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        Button btn = view.findViewById(R.id.firstButton);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FakeWork fakeWork = new FakeWork();
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                fakeWork.start(new FakeWork.OnFinishedWork() {
                    @Override
                    public void onDone(String result) {
                        progressBar.setVisibility(View.INVISIBLE);
                        listener.onStartButtonClicked(result);
                    }

                    @Override
                    public void onProgress(Integer progress) {
                        progressBar.setProgress(progress);
                    }
                });
            }
        });
        return view;
    }
}
