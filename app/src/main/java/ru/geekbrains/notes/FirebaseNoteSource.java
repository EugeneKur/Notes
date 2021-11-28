package ru.geekbrains.notes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseNoteSource implements NoteSource{

    private static final String NOTE_COLLECTION = "Notes";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collection = db.collection(NOTE_COLLECTION);
    private List<Note> noteList = new ArrayList<>();

    public FirebaseNoteSource(OnInitListener onInitListener) {
        collection.orderBy("title")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        noteList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                            Map<String, Object> doc = documentSnapshot.getData();
                            Note note = documentSnapshot.toObject(Note.class);
                            noteList.add(note);

                        }

                        Log.d("DebugLog", "isSuccessful " + noteList.toString());
                        onInitListener.onInit();
                    }
                })
                .addOnFailureListener(e -> Log.e("DebugLog", "error", e));

    }

    @Override
    public Note getNote(int position) {
        return noteList.get(position);
    }

    @Override
    public int size() {
        if (noteList == null) {
            return 0;
        }
        return noteList.size();
    }

    @Override
    public void deleteNote(int position) {
        collection.document(noteList.get(position).getId()).delete();
        noteList.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        collection.document(noteList.get(position).getId()).set(note);
        noteList.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        collection.add(note);
        noteList.add(note);
    }

    @Override
    public void clearNotes() {
        for (Note note: noteList){
            collection.document(note.getId()).delete();
        }
        noteList.clear();
    }

    interface OnInitListener {
        void onInit();
    }
}
