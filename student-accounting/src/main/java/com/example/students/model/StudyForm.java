package com.example.students.model;


public enum StudyForm {

    FULL_TIME("Очная"),
    EXTRAMURAL("Заочная"),
    PART_TIME("Очно-заочная");

    private final String title;

    StudyForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
