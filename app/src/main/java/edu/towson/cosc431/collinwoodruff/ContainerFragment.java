package edu.towson.cosc431.collinwoodruff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.models.Person;

/**
 * Created by Collin on 10/23/2017.
 */

public class ContainerFragment extends Fragment implements PersonListFragment.OnPersonSelected {

    List<Person> people;
    PersonFragment personFragment;
    PersonListFragment peopleFragment;

    public ContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        people = makePeople();
        personFragment = (PersonFragment)getChildFragmentManager()
                .findFragmentById(R.id.person);
        peopleFragment = (PersonListFragment)getChildFragmentManager()
                .findFragmentById(R.id.personlist);
        peopleFragment.setPeople(people);
        peopleFragment.setPersonSelectedListener(this);
    }

    private List<Person> makePeople() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            personList.add(new Person("Person" + i, 20 + i));
        }
        return personList;
    }

    @Override
    public void onPersonSelected(Person p) {
        personFragment.setPerson(p);
    }

    @Override
    public void onPersonDeleted(Person p) {
        people.remove(p);
        peopleFragment.setPeople(people);
    }

}
