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

import edu.towson.cosc431.collinwoodruff.models.Person;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    Person person;
    TextView nameTv;
    TextView ageTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        nameTv = view.findViewById(R.id.name);
        ageTv = view.findViewById(R.id.age);
        return view;
    }

    public void setPerson(Person person) {
        this.person = person;
        nameTv.setText(person.getName());
        ageTv.setText(Integer.toString(person.getAge()));
    }
}
