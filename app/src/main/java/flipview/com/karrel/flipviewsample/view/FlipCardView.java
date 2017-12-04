package flipview.com.karrel.flipviewsample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import flipview.com.karrel.flipviewsample.R;
import flipview.com.karrel.flipviewsample.databinding.ViewFlipBinding;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenter;
import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenterImpl;

/**
 * Created by Rell on 2017. 12. 1..
 */
public class FlipCardView extends LinearLayout implements FlipCardPresenter.View {
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

    private void init() {
        Log.d(TAG, "init");
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_flip, this, true);

        presenter = new FlipCardPresenterImpl(this);
        binding.flipEventView.setPresenter(presenter);

        binding.image.setCameraDistance(10000f);
    }

    @Override
    public void rotateY(float rotateY) {
        Log.d(TAG, String.format("rotateY(%s)", rotateY));
        binding.image.setRotationY(rotateY);
    }

    @Override
    public void showCard1(boolean b) {
        binding.image.setVisibility(b ? View.VISIBLE : View.GONE);
        binding.image2.setVisibility(!b ? View.VISIBLE : View.GONE);
    }
}
