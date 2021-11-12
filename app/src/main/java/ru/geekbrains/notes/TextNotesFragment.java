package ru.geekbrains.notes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_text_notes, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItemSearch = menu.findItem(R.id.action_search);
        if (menuItemSearch != null) {
            menuItemSearch.setVisible(false);
        }
        MenuItem menuItemLook = menu.findItem(R.id.action_look);
        if (menuItemLook != null) {
            menuItemLook.setVisible(false);
        }

        MenuItem menuItemFontSize = menu.findItem(R.id.action_font_size);
        if (menuItemFontSize != null) {
            menuItemFontSize.setVisible(false);
        }

        MenuItem menuItemFontStyle = menu.findItem(R.id.action_font_style);
        if (menuItemFontStyle != null) {
            menuItemFontStyle.setVisible(false);
        }

        MenuItem menuItemColorApp = menu.findItem(R.id.action_color_app);
        if (menuItemColorApp != null) {
            menuItemColorApp.setVisible(false);
        }

        MenuItem menuItemSort = menu.findItem(R.id.action_sort);
        if (menuItemSort != null) {
            menuItemSort.setVisible(false);
        }


        MenuItem menuItemDelete = menu.findItem(R.id.action_delete);
        if (menuItemDelete != null) {
            menuItemDelete.setVisible(true);
        }

        MenuItem menuItemShare = menu.findItem(R.id.action_share);
        if (menuItemShare != null) {
            menuItemShare.setVisible(true);
        }

        MenuItem menuItemRemind = menu.findItem(R.id.action_remind);
        if (menuItemRemind != null) {
            menuItemRemind.setVisible(true);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (note != null) {

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_child, ChildTextFragment.newInstance(note))
                    .addToBackStack("ChildTextFragment")
                    .commit();

            TextView textView = view.findViewById(R.id.nameNote);
            textView.setText(note.getNameNote());
            textView.setTextColor(Color.BLACK);
            TextView textView2 = view.findViewById(R.id.dateNote);
            textView2.setText(note.getDateNote());
            textView2.setTextColor(Color.BLACK);
        }

    }
}