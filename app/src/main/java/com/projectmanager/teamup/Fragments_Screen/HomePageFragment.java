package com.projectmanager.teamup.Fragments_Screen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectmanager.teamup.Adapter.CardAdapter;
import com.projectmanager.teamup.Modal.CardModal;
import com.projectmanager.teamup.R;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends Fragment {

    public HomePageFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    ArrayList <Object> list;
    ArrayList <Object> getList(){
        list = new ArrayList<>();
        list.add(new CardModal("C++ Language"));
        list.add(new CardModal("C++ Language"));
        list.add(new CardModal("C++ Language"));
        list.add(new CardModal("C++ Language"));

        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = view.findViewById(R.id.container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CardAdapter(getList(),getContext()));
        return view;
    }
}