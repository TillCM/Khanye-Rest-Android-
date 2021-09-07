package com.tillster.khaynerestmanual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView txt_khanyesaid;
    Button btn_khanyeSaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_khanyesaid = findViewById(R.id.txt_khanysaid);
        btn_khanyeSaid= findViewById(R.id.btn_speak);

        btn_khanyeSaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                getKhanyeWisdom speakKhanye = new getKhanyeWisdom();
                new Thread(speakKhanye).start();
            }
        });




    }

    //innerclass
    class getKhanyeWisdom implements Runnable
    {

        @Override
        public void run()
        {
            URL url = NetworkUtil.buildURL();

            String Khanye = null;

            try
            {
                Khanye = NetworkUtil.getResponseFromHttpUrl(url);
            }
            catch (IOException e)
            {
                e.printStackTrace();


                Log.i(TAG, "run: " + Khanye);
                Log.i(TAG, "run: "+ e.getMessage());
            }

            if (Khanye!=null)
            {
                try {
                    JSONObject rootData = new JSONObject(Khanye);
                    Log.i(TAG, "run: " + rootData);
                    String khanyeSaid = rootData.getString("quote");
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            txt_khanyesaid.setText(khanyeSaid);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }






        }
    }


}

