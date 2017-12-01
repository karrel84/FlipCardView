package flipview.com.karrel.flipviewsample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import flipview.com.karrel.flipviewsample.R;
import flipview.com.karrel.flipviewsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
    }

    private void setupEvents() {
    }
}
