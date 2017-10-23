package edu.towson.cosc431.collinwoodruff;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonListFragment extends ListFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayAdapter<String> adapter;
    Controller controller;
    private ArrayList<String> personsList = new ArrayList<>();
    private ArrayList<String> personsAgeList = new ArrayList<>();

    public PersonListFragment() {
        personsList.add("Person0");
        personsList.add("Person1");
        personsList.add("Person2");
        personsList.add("Person3");
        personsList.add("Person4");
        personsList.add("Person5");
        personsAgeList.add("21");
        personsAgeList.add("28");
        personsAgeList.add("26");
        personsAgeList.add("23");
        personsAgeList.add("29");
        personsAgeList.add("20");
    }

    public void setPersonalList(ArrayList<String> people){
        this.personsList = people;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, personsList);
        setListAdapter(adapter);
        controller = (Controller)getActivity();
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        controller.person(position, personsList, personsAgeList);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        personsList.remove(position);
        adapter.notifyDataSetChanged();
        return false;
    }
}
