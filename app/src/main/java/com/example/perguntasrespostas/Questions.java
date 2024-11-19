package com.example.perguntasrespostas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Questions
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question;
    private String answer;

    public Questions(String question, String answer){
        this.answer = answer;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
