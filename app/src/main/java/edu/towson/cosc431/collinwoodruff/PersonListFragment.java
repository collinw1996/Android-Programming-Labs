package edu.towson.cosc431.collinwoodruff;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.R;
import edu.towson.cosc431.collinwoodruff.models.Person;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonListFragment extends ListFragment {

    List<Person> people;
    OnPersonSelected personSelectedListener;

    public interface OnPersonSelected {
        void onPersonSelected(Person p);
        void onPersonDeleted(Person p);
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                personSelectedListener.onPersonDeleted(people.get(i));
                return true;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setPeople(final List<Person> people) {
        this.people = people;
        List<String> names = new ArrayList<>();
        for (Person person : people) {
            names.add(person.getName());
        }
        final ListAdapter adapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
    }

    public void setPersonSelectedListener(OnPersonSelected listener){
        personSelectedListener = listener;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        personSelectedListener.onPersonSelected(people.get(position));
    }
}
