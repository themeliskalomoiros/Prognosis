package gr.kalymnos.sk3m3l10.prognosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }
}
