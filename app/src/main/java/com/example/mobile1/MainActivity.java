package com.example.mobile1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Animation top;
    private ImageView img;
    private Spinner spinner;
    private TextView text;
    private Button solveButton;
    private Button submitButton;
    private EditText editText;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private RecyclerView recyclerView;
    private EquationAdapter equationAdapter;

    private List<Solution> equations;
    private String answer;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_SCORE = "score";
    private SharedPreferences sharedPreferences;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score =0;
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        img = findViewById(R.id.imageView);
        img.setAnimation(top);

        spinner = findViewById(R.id.spinner);
        text = findViewById(R.id.ex);
        solveButton = findViewById(R.id.solve);
        submitButton = findViewById(R.id.submit);
        editText = findViewById(R.id.ans);
        text3 = findViewById(R.id.good);
        text4 = findViewById(R.id.bad);
        text5 = findViewById(R.id.msg);
        text6 = findViewById(R.id.score);
        recyclerView = findViewById(R.id.recyclerView);

        editText.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        text5.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);

        mathh Data = new mathh();
        equations = Data.getEqu();
        String[] types = Data.getType().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        spinner.setAdapter(adapter);

        equationAdapter = new EquationAdapter(equations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(equationAdapter);

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSolve(v);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit(v);

            }
        });
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        text6.setText(String.valueOf(score));
    }

    public void btnSolve(View view) {
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        editText.getText().clear();

        String selectedType = spinner.getSelectedItem().toString();

        Solution selectedEquation = null;
        for (Solution equation : equations) {
            if (equation.getType().equals(selectedType)) {
                selectedEquation = equation;
                break;
            }
        }

        if (selectedEquation != null) {
            text.setText(selectedEquation.getEqu());
            answer = selectedEquation.getSol();
        } else {
            text.setText(""); // Clear the text view if no equation is selected
            answer = ""; // Clear the answer
        }

        editText.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void submit(View view) {
        String userInput = editText.getText().toString();
        text5.setVisibility(View.VISIBLE);
        text6.setVisibility(View.VISIBLE);

        if (userInput.equals(answer)) {
            text3.setVisibility(View.VISIBLE);
            score++;
            text6.setText(String.valueOf(score));
        } else {
            text4.setText(text4.getText()+"The correct Answer is"+answer);
            text4.setVisibility(View.VISIBLE);

            if (score > 0) {
                score--;
                text6.setText(String.valueOf(score));
            }
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_SCORE, score);
        editor.commit();
        text6.setText(String.valueOf(score));
    }
}