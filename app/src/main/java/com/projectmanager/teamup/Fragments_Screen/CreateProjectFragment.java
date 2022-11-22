package com.projectmanager.teamup.Fragments_Screen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.projectmanager.teamup.Activity_Screen.CardView;
import com.projectmanager.teamup.Activity_Screen.Member_activity;
import com.projectmanager.teamup.Modal.CardModal;
import com.projectmanager.teamup.R;

import java.util.Calendar;

public class CreateProjectFragment extends Fragment {
//    Button btnCalender, BtnSubmit, AddBtnMember;
//    TextView ViewDateTV;
    String Next;
    private Button TimeStartBtn, btnContinue;
    private EditText editTextProjectName, editTextDescription;
    private TextView idTVTime;
    public String ProjectName, Description, Name;
    private FirebaseFirestore db;
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
        db = FirebaseFirestore.getInstance();
        TimeStartBtn = view.findViewById(R.id.calenderBtn);
        idTVTime = view.findViewById(R.id.idTVDueDate);
        btnContinue = view.findViewById(R.id.btnContinue);
        editTextDescription = view.findViewById(R.id.idETDescription);
        editTextProjectName = view.findViewById(R.id.idETProjectName);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectName = editTextProjectName.getText().toString();
                Description = editTextDescription.getText().toString();

                if (TextUtils.isEmpty(ProjectName) && TextUtils.isEmpty(Description)) {
                    editTextProjectName.setError("Please fill the Full Field");
                    editTextDescription.setError("Please fill the Full Field");
                } else if (TextUtils.isEmpty(ProjectName)) {
                    editTextProjectName.setError("Project Name is Compulsory");
                } else if (TextUtils.isEmpty(Description)) {
                    editTextDescription.setError("Description is Compulsory");
                } else {

                    addMyDataToFirestore(ProjectName, Description);
                }
            }
        });
//        btnCalender = view.findViewById(R.id.calenderBtn);
//        ViewDateTV = view.findViewById(R.id.idTVDueDate);
//        BtnSubmit = view.findViewById(R.id.BtnSubmit);
//        AddBtnMember = view.findViewById(R.id.AddBtnMember);
//
//
//        btnCalender.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Calendar c = Calendar.getInstance();
//
//
//                int year = c.get(Calendar.YEAR);
//                int month = c.get(Calendar.MONTH);
//                int day = c.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        // on below line we are passing context.
//                        getContext(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                // on below line we are setting date to our text view.
//                                int dayMonth = monthOfYear + 1;
//                                Next = dayOfMonth + "/" + dayMonth + "/" + year;
//                                ViewDateTV.setText(Next);
//
//
//                            }
//                        },
//                        // on below line we are passing year,
//                        // month and day for selected date in our date picker.
//                        year, month, day);
//                // at last we are calling show to
//                // display our date picker dialog.
//                datePickerDialog.show();
//
//            }
//        });
//        BtnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    Fragment f = new BlankFragment();
//                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                    fm.replace(R.id.container,f).commit();
//
//                } catch (Exception e) {
//                    Toast.makeText(getContext(), "Please Fill The Full Foam", Toast.LENGTH_SHORT).show();
//                    e.fillInStackTrace();
//                }
//
//            }
//        });
//        AddBtnMember.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//
//                    Fragment f = new SearchFragment();
//                    FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
//                    fm.replace(R.id.container,f).commit();
//
//                } catch (Exception e) {
//                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        return view;
    }

    private void addMyDataToFirestore(String projectName, String description) {
        CollectionReference dbTeamUp = db.collection("TeamUp");
        CardModal cardModal = new CardModal(projectName, description);
        dbTeamUp.add(cardModal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Data Is Enter to Firebase is Successfully!!", Toast.LENGTH_SHORT).show();
                Fragment f = new HomePageFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.container,f).commit();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail to add Data \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}