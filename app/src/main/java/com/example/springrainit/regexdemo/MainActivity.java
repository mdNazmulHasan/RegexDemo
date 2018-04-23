package com.example.springrainit.regexdemo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputLayout=findViewById(R.id.bankNameWrapper);
        editText=findViewById(R.id.bankNameET);
    }
    private boolean patternCheck(final String regex,
                                 final String input) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        boolean b = m.find();
        return !b;
    }

    public void check(View view) {
        if(isAlphabetWithSlashAndSpace(editText.getText().toString())){
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            textInputLayout.setError(null);
        }else{
            textInputLayout.setError("validation failed");
        }

    }
    private boolean isAlphabetWithSlashAndSpace(String bankName) {
        return patternCheck("[^a-z-/ ]", bankName);
    }
    private boolean isPasswordValid(String iban) {
        if (iban.length() <= 12 & iban.length() >= 8) {
            int last_remaining = iban.length() - 2;
            return patternCheck("[a-z]{2}[a-z0-9]{" + last_remaining + "}", iban);
        } else {
            return true;
        }
    }

    private boolean isAlphaNumeric(String accountNumber) {
        return patternCheck("[^a-z0-9]", accountNumber);
    }
}
