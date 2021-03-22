package ru.geekbrains.notesapplication;

public class NoteRepository {
    public Note[] notes = new Note[5];

    public Note[] getNotes() {
        notes[0] = new Note("Звонок", "Позвонить шефу", "15.03.21", 1, false);
        notes[1] = new Note("Встреча", "Сходить к терапевту", "01.03.21", 2, true);
        notes[2] = new Note("Мероприятие", "Утренник 1.04.21", "17.03.21", 2, false);
        notes[3] = new Note("Звонок", "Позвонить на счет документов", "15.02.21", 1, true);
        notes[4] = new Note("Покупка", "Купить планшет", "1.01.21", 2, false);
        return notes;
    }
}
