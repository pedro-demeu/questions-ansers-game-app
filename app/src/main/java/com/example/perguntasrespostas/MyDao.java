package com.example.perguntasrespostas;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    long insertQuestion(Questions questions);

    @Query("SELECT * FROM Questions")
    List<Questions> searchAllQuestions();

    @Query("DELETE FROM Questions")
    void deleteQuestions();
}
