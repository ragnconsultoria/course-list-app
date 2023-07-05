package com.developdetail.courselistapp.views.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.developdetail.courselistapp.R;
import com.developdetail.courselistapp.models.Subscribed;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText etInputFirstName, etInputLastName, etInputCourseName, etInputPhoneNumber;
    ImageView checkFirstName;
    MaterialButton btnClear, btnSend, btnSave;
    Subscribed subscribed;
    Dialog mProgressDialog;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFields();

        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        btnClear.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initializeFields() {
        toolbar = findViewById(R.id.tb_main_activity);

        etInputFirstName = findViewById(R.id.et_input_first_name_main_activity);
        etInputLastName = findViewById(R.id.et_input_last_name_main_activity);
        etInputCourseName = findViewById(R.id.et_input_course_name_main_activity);
        etInputPhoneNumber = findViewById(R.id.et_input_phone_number_main_activity);

        checkFirstName = findViewById(R.id.iv_check_input_first_name_activity_main);

        btnClear = findViewById(R.id.btn_clear_main_activity);
        btnSend = findViewById(R.id.btn_send_main_activity);
        btnSave = findViewById(R.id.btn_save_main_activity);

        btnSave.setEnabled(false);
        btnSave.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        btnSave.setTextColor(getResources().getColor(R.color.white));

        btnSend.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_clear_main_activity) clearForm();
        if (v.getId() == R.id.btn_send_main_activity) validateForm();
        if (v.getId() == R.id.btn_save_main_activity) saveForm();
    }

    private void clearForm() {

        etInputFirstName.clearFocus();
        etInputFirstName.setEnabled(true);
        etInputFirstName.setError(null);
        etInputFirstName.setText("");
        etInputFirstName.setAlpha(1.0f);
        etInputFirstName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text));

        //checkFirstName.setVisibility(View.GONE);

        etInputLastName.clearFocus();
        etInputLastName.setEnabled(true);
        etInputLastName.setError(null);
        etInputLastName.setText("");
        etInputLastName.setAlpha(1.0f);
        etInputLastName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text));

        etInputCourseName.clearFocus();
        etInputCourseName.setEnabled(true);
        etInputCourseName.setError(null);
        etInputCourseName.setText("");
        etInputCourseName.setAlpha(1.0f);
        etInputCourseName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text));

        etInputPhoneNumber.clearFocus();
        etInputPhoneNumber.setEnabled(true);
        etInputPhoneNumber.setError(null);
        etInputPhoneNumber.setText("");
        etInputPhoneNumber.setAlpha(1.0f);
        etInputPhoneNumber.setBackground(getResources().getDrawable(R.drawable.bg_edit_text));

        btnSave.setEnabled(false);
        btnSave.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        btnSave.setTextColor(getResources().getColor(R.color.white));

        btnSend.setEnabled(true);
        btnSend.setBackground(getResources().getDrawable(R.drawable.bg_button_enabled));
        btnSend.setTextColor(getResources().getColor(R.color.white));
    }

    private void validateForm() {
        String firstName = etInputFirstName.getText().toString();
        String lastName = etInputLastName.getText().toString();
        String courseName = etInputCourseName.getText().toString();
        String phoneNumber = etInputPhoneNumber.getText().toString();

        subscribed = new Subscribed();
        subscribed.setFirstName(firstName);
        subscribed.setLastName(lastName);
        subscribed.setCourseName(courseName);
        subscribed.setPhoneNumber(phoneNumber);

        if (validateFields(firstName, lastName, courseName, phoneNumber)) {
            sendForm(subscribed);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public boolean validateFields(String firstName, String lastName, String courseName,
                                  String phoneNumber) {
        if (TextUtils.isEmpty(firstName)) {
            etInputFirstName.setError(getResources().getString(R.string.input_first_name));
            etInputFirstName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text_error));
            Toast.makeText(MainActivity.this, getResources().
                    getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();

            etInputFirstName.setOnFocusChangeListener((v, hasFocus) ->
                etInputFirstName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text)));
            return false;
        } else if (TextUtils.isEmpty(lastName)) {
            etInputLastName.setError(getResources().getString(R.string.input_last_name));
            etInputLastName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text_error));
            Toast.makeText(MainActivity.this, getResources().
                    getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();

            etInputLastName.setOnFocusChangeListener((v, hasFocus) ->
                    etInputLastName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text)));
            return false;
        } else if (TextUtils.isEmpty(courseName)) {
            etInputCourseName.setError(getResources().getString(R.string.input_course_name));
            etInputCourseName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text_error));
            Toast.makeText(MainActivity.this, getResources().
                    getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();
            etInputCourseName.setOnFocusChangeListener((v, hasFocus) ->
                    etInputCourseName.setBackground(getResources().getDrawable(R.drawable.bg_edit_text)));
            return false;
        } else if (TextUtils.isEmpty(phoneNumber)) {
            etInputPhoneNumber.setError(getResources().getString(R.string.input_phone_number_name));
            etInputPhoneNumber.setBackground(getResources().getDrawable(R.drawable.bg_edit_text_error));
            Toast.makeText(MainActivity.this, getResources().
                    getString(R.string.all_fields_must_be_filled), Toast.LENGTH_SHORT).show();
            etInputPhoneNumber.setOnFocusChangeListener((v, hasFocus) ->
                    etInputPhoneNumber.setBackground(getResources().getDrawable(R.drawable.bg_edit_text)));
            return false;
        }

        return true;
    }

    private void sendForm(Subscribed subscribed) {
        btnSave.setEnabled(true);
        btnSave.setBackground(getResources().getDrawable(R.drawable.bg_button_enabled));
        btnSave.setTextColor(getResources().getColor(R.color.white));

        etInputFirstName.clearFocus();
        etInputFirstName.setEnabled(false);
        etInputFirstName.setAlpha(0.3f);

        checkFirstName.setAlpha(0.3f);

        etInputLastName.clearFocus();
        etInputLastName.setEnabled(false);
        etInputLastName.setAlpha(0.3f);

        etInputCourseName.clearFocus();
        etInputCourseName.setEnabled(false);
        etInputCourseName.setAlpha(0.3f);

        etInputPhoneNumber.clearFocus();
        etInputPhoneNumber.setEnabled(false);
        etInputPhoneNumber.setAlpha(0.3f);

        btnSend.clearFocus();
        btnSend.setEnabled(false);
        btnSend.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        btnSend.setTextColor(getResources().getColor(R.color.white));
    }

    private void saveForm() {
        handler.postDelayed(() -> {
            clearForm();
            Toast.makeText(MainActivity.this, getResources().
                    getString(R.string.successfull_registration), Toast.LENGTH_SHORT).show();
        }, 3000);
        showProgressDialog(getResources().getString(R.string.please_wait));
    }

    private void showProgressDialog(String text) {
        mProgressDialog = new Dialog(this);
        mProgressDialog.setContentView(R.layout.layout_progress_dialog_transparent);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView mText = mProgressDialog.findViewById(R.id.tv_progress_dialog_please_wait);
        mText.setText(text);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        mProgressDialog.dismiss();
    }
}