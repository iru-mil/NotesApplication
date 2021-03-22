package ru.geekbrains.notesapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteDetailsFragment extends Fragment {


    public static final String ARG_NOTE = "note";
    private Note note;

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_details, container,
                false);

        TextView tvPurport = view.findViewById(R.id.details);
        TextView tvDate = view.findViewById(R.id.date);
        TextView tvToArchive = view.findViewById(R.id.to_archive);
        tvPurport.setText(note.getNotePurport());
        tvDate.setText(note.getNoteCreationDate());
        if (note.getNoteImportanceDegree() == 1) {
            tvPurport.setTextColor(Color.RED);
        }
        if (note.isNoteToArchive())
            tvToArchive.setText("Перенести в архив");
        return view;
    }
}