package com.projectmanager.teamup.Fragments_Screen;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.projectmanager.teamup.Activity_Screen.Member_activity;
import com.projectmanager.teamup.R;

import java.util.Calendar;

public class CreateProjectFragment extends Fragment {
    Button btnCalender, BtnSubmit, AddBtnMember;
    TextView ViewDateTV;
    String Next;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CreateProjectFragment() {
        // Required empty public constructor
    }
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_project, container, false);
        btnCalender = view.findViewById(R.id.calenderBtn);
        ViewDateTV = view.findViewById(R.id.idTVDueDate);
        BtnSubmit = view.findViewById(R.id.BtnSubmit);
        AddBtnMember = view.findViewById(R.id.AddBtnMember);


        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();


                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // on below line we are setting date to our text view.
                                int dayMonth = monthOfYear + 1;
                                Next = dayOfMonth + "/" + dayMonth + "/" + year;
                                ViewDateTV.setText(Next);


                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();

            }
        });
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(getContext(), Next, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Please Fill The Full Foam", Toast.LENGTH_SHORT).show();
                    e.fillInStackTrace();
                }

            }
        });
        AddBtnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    ((Member_activity) getActivity()).FragmentMethod();

                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}