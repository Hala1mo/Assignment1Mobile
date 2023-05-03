package com.example.mobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Spinner spinner;
    private TextView text;
    private TextView text2;
    private EditText editText;
    private TextView text3;
    private TextView text4;

    private TextView text5;

    private TextView text6;
    String answer;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        text=findViewById(R.id.Ex);
        text2 = (TextView) findViewById(R.id.submit) ;
        text2.setVisibility(View.INVISIBLE);
        editText=findViewById(R.id.ans);
        editText.setVisibility(View.INVISIBLE);

        submit=(Button)findViewById(R.id.submit);
        submit.setVisibility(View.INVISIBLE);

        text3=findViewById(R.id.good);
        text3.setVisibility(View.INVISIBLE);

        text4=findViewById(R.id.bad);
        text4.setVisibility(View.INVISIBLE);

        text5=findViewById(R.id.msg);
        text5.setVisibility(View.INVISIBLE);

        text6=findViewById(R.id.score);
        text6.setVisibility(View.INVISIBLE);


        mathh Data=new mathh();
        String [] types= Data.getType().toArray(new String[0]);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,types);
        spinner.setAdapter(adapter);
    }


    public void btnSolve(View view) {
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        editText.getText().clear();


        String spn=spinner.getSelectedItem().toString();
        submit.setVisibility(View.VISIBLE);
mathh dd=new mathh();
        Solution [] equ= dd.getEqu().toArray(new Solution[0]);
        for(Solution b:equ){
            if(b.getType().equals(spn)){
text.setText(b.getEqu());
                editText.setVisibility(View.VISIBLE);
                answer=b.getSol();



            }
        }
    }

    public void submit(View view) {


    String check= String.valueOf(editText.getText());
        text5.setVisibility(View.VISIBLE);
        text6.setVisibility(View.VISIBLE);
       if(check.equals(answer)){
           text3.setVisibility(View.VISIBLE);

           text6.setText(String.valueOf(Integer.parseInt(text6.getText().toString())+1));

       }
       else {
           text4.setVisibility(View.VISIBLE);
           if(Integer.parseInt(text6.getText().toString())!=0){
               text6.setText(String.valueOf(Integer.parseInt(text6.getText().toString())-1));
           }
       }
    }
}