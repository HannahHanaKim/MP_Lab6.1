package com.example.hannahkim.lab6_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText txtData;
    Button writeBtn, clearBtn, readBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = (EditText)findViewById(R.id.txtData);
        writeBtn = (Button)findViewById(R.id.writeBtn);
        clearBtn = (Button)findViewById(R.id.clearBtn);
        readBtn = (Button)findViewById(R.id.readBtn);
        finishBtn = (Button)findViewById(R.id.finishBtn);

        writeBtn.setOnClickListener(new View.OnClickListener() { //write data to file
            @Override
            public void onClick(View v) {
                try {
                    File dir = getExternalFilesDir("Myfiles");
                    File sdCard = new File(dir, "myfile.txt");
                    dir.mkdirs();

                    if(!sdCard.exists()) { //if file doesn't exists
                        sdCard.createNewFile();
                    }

                    FileOutputStream fOut = new FileOutputStream(sdCard, true);

                    fOut.write(txtData.getText().toString().getBytes());
                    fOut.close();

                    Toast.makeText(MainActivity.this, "Done writing SD 'myfile.txt", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); //writeBtn

        clearBtn.setOnClickListener(new View.OnClickListener() { //clear the screen
            @Override
            public void onClick(View v) {
                txtData.setText("");
            }
        }); //clearBtn

        readBtn.setOnClickListener(new View.OnClickListener() { //read data from file
            public void onClick(View v) {
                try {
                    File sdCard = new File(getExternalFilesDir("Myfiles"), "myfile.txt");

                    FileInputStream fIn = new FileInputStream(sdCard);
                    InputStreamReader isw = new InputStreamReader(fIn);

                    byte[] data = new byte[fIn.available()];
                    while (fIn.read(data) != -1) { //read until data is done
                    }
                    txtData.setText(new String(data));
                    fIn.close();

                    Toast.makeText(MainActivity.this, "Done reading SD 'myfile.txt'", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); //readBtn

        finishBtn.setOnClickListener(new View.OnClickListener() { //finish the app
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
