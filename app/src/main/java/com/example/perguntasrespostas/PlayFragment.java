package com.example.perguntasrespostas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class PlayFragment extends Fragment {
    Button saveButton;
    Button nextButton;
    Button showAnswerButton;

    TextView questionTextView;
    TextView answerTextView;
    List<Questions> questionsList;

    public PlayFragment() {
    }

    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButton = getActivity().findViewById(R.id.createQuestionsButton);
        nextButton = getActivity().findViewById(R.id.nextBtn);
        questionTextView = getActivity().findViewById(R.id.questionText);
        answerTextView = getActivity().findViewById(R.id.answerText);

        questionsList = MyDatabase.getDatabase(getActivity())
                        .myDao().searchAllQuestions();

        nextQuestion();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main, new FormFragment()).commit();
            }
        });
    }

    public void nextQuestion() {
        if (!questionsList.isEmpty()){
            int totalQuestions = questionsList.size();
            int randomIndex = new Random().nextInt(totalQuestions);
            Questions questions = questionsList.get(randomIndex);

            questionTextView.setText(questions.getQuestion());
            answerTextView.setText(questions.getAnswer());
            answerTextView.setVisibility(View.GONE);
        }
    }
}