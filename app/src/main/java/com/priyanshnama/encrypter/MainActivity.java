package com.priyanshnama.encrypter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajwadshaikh.amazingautofitedittext.AmazingAutofitEditText;

import static android.content.ClipDescription.MIMETYPE_TEXT_PLAIN;

public class MainActivity extends AppCompatActivity {
    private TextView output;
    private AmazingAutofitEditText input;
    private String raw_data, processed_data;
    private final Encrypter encrypter = new Encrypter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
    }

    public String getRawData() {
        return input.getText().toString();
    }

    public String getProcessedData() {
        return processed_data;
    }

    public void setProcessedData() {
        clear(null);
        output.setText(getProcessedData());
    }

    public void encrypt(View view) {
        raw_data = getRawData();
        processed_data = encrypter.encrypt(raw_data);
        setProcessedData();
        Toast.makeText(this, "encrypted", Toast.LENGTH_LONG).show();
    }

    public void decrypt(View view) {
        raw_data = getRawData();
        processed_data = encrypter.decrypt(raw_data);
        setProcessedData();
        Toast.makeText(this, "decrypted", Toast.LENGTH_LONG).show();
    }

    public void copy(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("processed data", getProcessedData());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "copied", Toast.LENGTH_LONG).show();
    }

    public void clear(View view) {
        output.setText("");
    }

    public void paste(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String text = "";
        if (clipboard.hasPrimaryClip()) {
            ClipData clip = clipboard.getPrimaryClip();
            if (clip.getDescription().hasMimeType(MIMETYPE_TEXT_PLAIN))
                text = clip.getItemAt(0).getText().toString();
        }
        input.setText(text);
    }
}