package com.codedimension.dialogfragmenttask1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginDialogFragment extends DialogFragment {
    private Button btnLogIn, btnExit;
    public static TextInputEditText editTextUserName, editTextUserPassword;
    private TextInputLayout passwordInputlayout;
    private CheckBox checkboxStay;
    SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
    private String userName = "admin";
    private String userPass = "12345678";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_dialog_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog()
                .getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkboxStay = view.findViewById(R.id.checkboxStay);
        btnLogIn = view.findViewById(R.id.btnLogIn);
        btnExit = view.findViewById(R.id.btnExit);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        editTextUserPassword = view.findViewById(R.id.editTextUserPassword);
        passwordInputlayout = view.findViewById(R.id.passwordInputlayout);
        editTextUserPassword.addTextChangedListener(passwordwatcher);
        btnLogIn.setOnClickListener(logInListener);
        btnExit.setOnClickListener(exit);
    }
    View.OnClickListener exit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.exit(0);
        }
    };

    private void saveData() {
        editor.putString("userName", userName);
        editor.putString("userPass", userPass);
        editor.putString("stay", "yes");
        editor.apply();
//        Toast.makeText(((MainActivity) getActivity()),
//                ((MainActivity) getActivity()).sharedPreferences.getString("userName", "ON_DATA1"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(((MainActivity) getActivity()),
//                ((MainActivity) getActivity()).sharedPreferences.getString("userPass", "ON_DATA2"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(((MainActivity) getActivity()),
//                ((MainActivity) getActivity()).sharedPreferences.getString("stay", "ON_DATA3"), Toast.LENGTH_SHORT).show();
    }

    View.OnClickListener logInListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (editTextUserName.getText().toString().equals(userName) &&
                    editTextUserPassword.getText().toString().equals(userPass)) {
                ((MainActivity) getActivity()).textUserName.setText(editTextUserName.getText().toString());
                if (checkboxStay.isChecked()) {
                    saveData();
                } else {
                //do no thing
                }
                dismiss();
            } else {
                Toast.makeText(((MainActivity) getActivity()), "Invalid password or username!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };
    TextWatcher passwordwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > passwordInputlayout.getCounterMaxLength()) {
                passwordInputlayout.setError("The password is Long!");
            } else {
                passwordInputlayout.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
