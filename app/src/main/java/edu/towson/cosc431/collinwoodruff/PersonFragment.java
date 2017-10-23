package edu.towson.cosc431.collinwoodruff;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {

    TextView name;
    TextView age;

    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.person_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = getActivity().findViewById(R.id.name);
        age = getActivity().findViewById(R.id.age);
    }

    public void person(int position, ArrayList<String> person, ArrayList<String> age){
        name.setText(person.get(position));
        this.age.setText(String.valueOf(age.get(position)));
    }

}
