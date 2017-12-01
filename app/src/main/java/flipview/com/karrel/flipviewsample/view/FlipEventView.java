package flipview.com.karrel.flipviewsample.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import flipview.com.karrel.flipviewsample.presenter.FlipCardPresenter;

/**
 * Created by Rell on 2017. 12. 1..
 */

public class FlipEventView extends View {
    private String TAG = FlipEventView.class.getSimpleName();
    private GestureDetector detector;
    private FlipCardPresenter presenter;

    public FlipEventView(Context context) {
        super(context);
        init();
    }

    public FlipEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        detector = new GestureDetector(getContext(), gestureListener);
    }

    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(TAG, "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            presenter.onScroll(e1.getX(), e2.getX());
            Log.d(TAG, String.format("onScroll e1 : %s, e2 : %s", e1.getX(), e2.getX()));
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(TAG, "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling");
            return false;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    public void setPresenter(FlipCardPresenter presenter) {
        this.presenter = presenter;
    }
}
