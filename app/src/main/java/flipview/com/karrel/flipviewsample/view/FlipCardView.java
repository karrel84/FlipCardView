package flipview.com.karrel.flipviewsample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import flipview.com.karrel.flipviewsample.R;
import flipview.com.karrel.flipviewsample.databinding.ViewFlipBinding;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenter;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenterImpl;

/**
 * Created by Rell on 2017. 12. 1..
 */

public class FlipCardView extends ViewGroup implements FlipCardPresenter.View {
    private String TAG = FlipCardView.class.getSimpleName();

    private ViewFlipBinding binding;
    private FlipCardPresenter presenter;

    public FlipCardView(Context context) {
        super(context);
        init();
    }

    public FlipCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void init() {
        Log.d(TAG, "init");
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_flip, this, false);
        addView(binding.getRoot());
        presenter = new FlipCardPresenterImpl(this);

        binding.flipEventView.setPresenter(presenter);
    }

    @Override
    public void rotateY(float rotateY) {
        binding.image.setRotationY(rotateY);
    }
}
