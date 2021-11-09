package ru.geekbrains.notes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextNotesFragment extends Fragment {

    private static final String ARG_POSITION = "position_arg";

    private int position = -1;

    private Note note = null;

    public TextNotesFragment() {
        // Required empty public constructor
    }

    public static TextNotesFragment newInstance(Note note) {
        TextNotesFragment fragment = new TextNotesFragment();
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
        return inflater.inflate(R.layout.fragment_text_notes, container, false);
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
    }
}