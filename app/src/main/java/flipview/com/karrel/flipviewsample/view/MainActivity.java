package flipview.com.karrel.flipviewsample.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import flipview.com.karrel.flipviewsample.R;
import flipview.com.karrel.flipviewsample.databinding.ActivityMainBinding;
import flipview.com.karrel.flipviewsample.databinding.ViewCardABinding;
import flipview.com.karrel.flipviewsample.databinding.ViewCardBBinding;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupCardView();
    }

    private void setupCardView() {
        ViewCardABinding aBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_card_a, null, false);
        binding.flipCard.setCardA(aBinding.getRoot());


        ViewCardBBinding bBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_card_b, null, false);
        binding.flipCard.setCardB(bBinding.getRoot());
    }

}
