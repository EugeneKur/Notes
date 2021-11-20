package ru.geekbrains.notes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ChildTextFragment extends Fragment {

    private static final String ARG_POSITION = "position_arg";
    private Note2_0 note = null;


    public ChildTextFragment() {
        // Required empty public constructor
    }

    public static ChildTextFragment newInstance(Note2_0 note) {
        ChildTextFragment fragment = new ChildTextFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POSITION, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {


            TextView textView = view.findViewById(R.id.textNotes);
            textView.setText(note.getTextNote());
            textView.setTextSize(30);
            textView.setTextColor(Color.BLACK);
        }

        Button buttonBack = view.findViewById(R.id.button_back_child);
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}