package ru.geekbrains.notes;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


public class ListNotesFragment extends Fragment {
    int currentPosition = -1;
    private AllNotes allNotes;




    public ListNotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);


        Button buttonBack = view.findViewById(R.id.button_option);
        buttonBack.setOnClickListener(v -> {
            OptoinsFragment optoinsFragment = OptoinsFragment.newInstance("","");
            if (isLand()) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container2, optoinsFragment)
                        .addToBackStack(null)
                        .commit();
            } else {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container1, optoinsFragment)
                        .addToBackStack(null)
                        .commit();
            }

        });
    }



    public void initView(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.note_container);

        allNotes = new AllNotes(4);
        allNotes.addNote("1 заметка", "Текст первой заметки", "22.12.2048");
        allNotes.addNote("2 заметка", "Текст второй заметки", "23.12.2048");
        allNotes.addNote("3 заметка", "Текст третьей заметки", "25.12.2048");
        allNotes.addNote("4 заметка", "Текст четвертой заметки", "26.12.2048");

        List<Note> supportList = allNotes.getNotesList();


        for (int i = 0; i < allNotes.getSize(); i++) {
            Note note = supportList.get(i);
            TextView textView = new TextView(getContext());

            textView.setText(note.getNameNote());
            textView.setTextSize(30);
            textView.setTextColor(Color.BLACK);

            final int position = i;

            textView.setOnClickListener(v -> {
                currentPosition = position;
                showText(note.getNameNote(), note.getTextNote(), note.getDateNote());
                updateBackground();


            });
            linearLayout.addView(textView);
        }





    }

    public AllNotes getAllNotes() {
        return allNotes;
    }

    void updateBackground() {
        LinearLayout linearLayout = getView().findViewById(R.id.note_container);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

            if (currentPosition == i) {
                linearLayout.getChildAt(i).setBackgroundColor(Color.GRAY);
            }
        }

    }


    void showText(String name, String text, String date) {
        if (isLand()) {
            showTextLand(name, text, date);
        } else {
            showTextPort(name, text, date);
        }

    }

    void  showTextPort (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note(name, text, date));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container1, textNotesFragment)
                .addToBackStack(null)
                .commit();

    }

    void  showTextLand (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note(name, text, date));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container2, textNotesFragment)
                .addToBackStack(null)
                .commit();

    }

    private boolean isLand () {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    };
}