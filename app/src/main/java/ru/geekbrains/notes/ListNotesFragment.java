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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ListNotesFragment extends Fragment {
    int currentPosition = -1;

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

    }

    public void initView(View view) {

        List<Note2_0> note2_0List = Arrays.asList(
                new Note2_0("1 заметка", "Текст первой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "22.12.2048"),
                new Note2_0("2 заметка", "Текст второй заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "23.12.2048"),
                new Note2_0("3 заметка", "Текст третьей заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "25.12.2048"),
                new Note2_0("4 заметка", "Текст четвертой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note2_0("5 заметка", "Текст пятой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note2_0("6 заметка", "Текст шестой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note2_0("7 заметка", "Текст седьмой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048"),
                new Note2_0("8 заметка", "Текст восьмой заметки. Текст. Текст. Текст. Текст. Текст. Текст.", "26.12.2048")
        );

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        NotesAdapter adapter = new NotesAdapter(note2_0List);
        adapter.setClickListener((view1, position) -> {
            currentPosition = position;
            Note2_0 note = note2_0List.get(currentPosition);
            showText(note.getNameNote(), note.getTextNote(), note.getDateNote());
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    void showText(String name, String text, String date) {
        if (isLand()) {
            showTextLand(name, text, date);
        } else {
            showTextPort(name, text, date);
        }

    }

    void  showTextPort (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note2_0(name, text, date));
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container1, textNotesFragment)
                .addToBackStack(null)
                .commit();

    }

    void  showTextLand (String name, String text, String date) {
        TextNotesFragment textNotesFragment = TextNotesFragment.newInstance(new Note2_0(name, text, date));
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