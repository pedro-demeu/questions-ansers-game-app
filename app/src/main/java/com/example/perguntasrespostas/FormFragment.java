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
import android.widget.Toast;

import java.util.List;

public class FormFragment extends Fragment {

    Button playBtn;
    Button saveBtn;
    EditText questionEditText;
    EditText answerEditText;

    public FormFragment() {
        // Required empty public constructor
    }

    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playBtn = getActivity().findViewById(R.id.playBtn);
        saveBtn = getActivity().findViewById(R.id.saveButton);
        questionEditText = getActivity().findViewById(R.id.questionInput);
        answerEditText = getActivity().findViewById(R.id.answerInput);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main, new PlayFragment()).commit();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionEditText.getText().toString();
                String answer = answerEditText.getText().toString();

                if (!question.isEmpty() && !answer.isEmpty()){
                    Questions questions = new Questions(question, answer);

                    MyDatabase.getDatabase(getActivity())
                            .myDao().insertQuestion(questions);

                    questionEditText.setText("");
                    answerEditText.setText("");

                    Toast.makeText(getActivity(), "Questão salva com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}