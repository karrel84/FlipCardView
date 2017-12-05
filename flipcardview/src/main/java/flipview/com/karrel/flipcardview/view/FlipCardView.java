package flipview.com.karrel.flipcardview.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import flipview.com.karrel.flipcardview.R;
import flipview.com.karrel.flipcardview.databinding.ViewFlipBinding;
import flipview.com.karrel.flipcardview.presenter.FlipCardPresenter;
import flipview.com.karrel.flipcardview.presenter.FlipCardPresenterImpl;


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
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_flip, this, true);

        presenter = new FlipCardPresenterImpl(this);
        binding.flipEventView.setPresenter(presenter);

        binding.cardALayout.setCameraDistance(40000f);
        binding.cardBLayout.setCameraDistance(40000f);
    }

    @Override
    public void showFrontCard(boolean b) {
        binding.cardALayout.setVisibility(b ? View.VISIBLE : View.GONE);
        binding.cardBLayout.setVisibility(!b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void rotateY(boolean b, float rotateY) {
        if (b) binding.cardALayout.setRotationY(rotateY);
        else binding.cardBLayout.setRotationY(rotateY);
    }

    @Override
    public void rotateAnimation(long duration, boolean isShowFrontCard, float startY, float endY) {
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(isShowFrontCard ? binding.cardALayout : binding.cardBLayout, "rotationY", 0f);
        animation1.setDuration(duration);
        animation1.start();

    }

    public void setCardA(View view) {
        binding.cardALayout.removeAllViews();
        binding.cardALayout.addView(view);
    }

    public void setCardB(View view) {
        binding.cardBLayout.removeAllViews();
        binding.cardBLayout.addView(view);
    }
}
