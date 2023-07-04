package com.developdetail.courselistapp.views.activities;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.developdetail.courselistapp.R;
import com.developdetail.courselistapp.models.Subscribed;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etInputFirstName, etInputLastName, etInputCourseName, etInputPhoneNumber;
    MaterialButton btnClear, btnSave, btnFinish;

    Subscribed subscribed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeFields();

        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
    }

    private void initializeFields() {
        toolbar = findViewById(R.id.tb_main_activity);
        etInputFirstName = findViewById(R.id.et_input_first_name_main_activity);
        etInputLastName = findViewById(R.id.et_input_last_name_main_activity);
        etInputCourseName = findViewById(R.id.et_input_course_name_main_activity);
        etInputPhoneNumber = findViewById(R.id.et_input_phone_number_main_activity);
        btnClear = findViewById(R.id.btn_clear_main_activity);
        btnSave = findViewById(R.id.btn_save_main_activity);
        btnFinish = findViewById(R.id.btn_finish_main_activity);
    }

    private void saveForm() {
        String firstName = etInputFirstName.getText().toString();
        String lastName = etInputLastName.getText().toString();
        String courseName = etInputCourseName.getText().toString();
        String phoneNumber = etInputPhoneNumber.getText().toString();

        subscribed = new Subscribed();
        subscribed.setFirstName(firstName);
        subscribed.setLastName(lastName);
        subscribed.setCourseName(courseName);
        subscribed.setPhoneNumber(phoneNumber);
    }
}