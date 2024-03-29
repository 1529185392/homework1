package com.example.exercise;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner;
    private Student student;
    private StudentDaoImpl studentDaoImpl;
    private EditText edName,edAge;
    private Button btnYES,btnExists;
    private int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gai);

        spinner =findViewById(R.id.sp_classmate);
        studentDaoImpl =  new StudentDaoImpl(this);
        edName = findViewById(R.id.et_name);
        edAge = findViewById(R.id.et_age);
        btnYES = findViewById(R.id.btn_right);
        btnExists = findViewById(R.id.btn_no);


        initData();
        btnExists.setOnClickListener(this);
        btnYES.setOnClickListener(this);
    }


    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            student = (Student)bundle.get("updata2");
            if (student != null){
                SpinnerAdapter spinnerAdapter  = spinner.getAdapter();
                for (int i=0;i<spinnerAdapter.getCount();i++){
                    if (spinnerAdapter.getItem(i).toString().equals(student.getClassmage())){
                        spinner.setSelection(i);
                    }
                }
                flag=student.getId();
                edName.setText(student.getName());
                edAge.setText(String.valueOf(student.getAge()));
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_right:
                ADDSHUJ();
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }


    private void ADDSHUJ() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (student == null)
        {
            student = new Student();
        }
        student.setId(flag);
        student.setName(edName.getText().toString());
        student.setClassmage(String.valueOf(spinner.getSelectedItem()));
        student.setAge(Integer.parseInt(edAge.getText().toString()));

        studentDaoImpl.updata(student);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("22",student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
