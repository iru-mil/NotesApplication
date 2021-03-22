package ru.geekbrains.notesapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Note currentNote;
    private boolean isLandscape;
    private final NoteRepository noteRepository = new NoteRepository();
    Note[] notes = noteRepository.getNotes();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < notes.length; i++) {
            String noteName = notes[i].getNoteName();
            TextView tv = new TextView(getContext());
            tv.setText(noteName);
            tv.setTextSize(25);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(v -> {
                currentNote = notes[fi];
                showNotes(currentNote);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = notes[0];
        }
        if (isLandscape) {
            showLandNotes(currentNote);
        }
    }

    private void showNotes(Note currentNote) {
        if (isLandscape) {
            showLandNotes(currentNote);
        } else {
            showPortNotes(currentNote);
        }
    }

    private void showLandNotes(Note currentNote) {
        NoteDetailsFragment detail = NoteDetailsFragment.newInstance(currentNote);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.details, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotes(Note currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteActivity.class);
        intent.putExtra(NoteDetailsFragment.ARG_NOTE, currentNote);
        startActivity(intent);
    }
}