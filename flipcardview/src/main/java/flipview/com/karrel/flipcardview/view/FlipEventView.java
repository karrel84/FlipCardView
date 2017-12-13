package flipview.com.karrel.flipcardview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import flipview.com.karrel.flipcardview.presenter.FlipCardPresenter;


/**
 * Created by Rell on 2017. 12. 1..
 * <p>
 * 카드의 이벤트를 담당하는 뷰이다.
 * 플링이벤트의 경우 안드로이드의 left drawer 오픈소스를 참고하여 만들었다..
 */

public class FlipEventView extends View {
    private String TAG = FlipEventView.class.getSimpleName();
    private GestureDetector detector;
    private FlipCardPresenter presenter;

    // 플링 이벤트시 최저 이동 거리
    private static final int SWIPE_MIN_DISTANCE = 40;
    private static final int SWIPE_THRESHOLD_VELOCITY = 400;

    /**
     * 최소 가속도
     */
    private float mMinVelocity;

    /**
     * 최대 가속도
     */
    private float mMaxVelocity;

    /**
     * Minimum velocity that will be detected as a fling
     */
    private static final int MIN_FLING_VELOCITY = 400; // dips per second

    /**
     * maximum velocity that will be detected as a fling
     */
    private static final int MAX_FLING_VELOCITY = 8000; // dips per second

    public static final int BASE_SETTLE_DURATION = 200; // ms
    public static final int MAX_SETTLE_DURATION = 300; // ms

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
        final float density = getResources().getDisplayMetrics().density;
        mMinVelocity = MIN_FLING_VELOCITY * density;
        mMaxVelocity = MAX_FLING_VELOCITY * density;

        detector = new GestureDetector(getContext(), gestureListener);
    }

    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            presenter.onDown(e.getX());
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            presenter.onScroll(e1.getX(), e2.getX());
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getRawX() - e2.getRawX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeLeft(getDuration(e1, e2, (int) velocityX, (int) velocityY));
                return true;
            } else if (e2.getRawX() - e1.getRawX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeRight(getDuration(e1, e2, (int) velocityX, (int) velocityY));
                return true;
            }

            return false;
        }
    };

    private void swipeLeft(long duration) {
        presenter.swipeLeft(duration);
    }

    private void swipeRight(long duration) {
        presenter.swipeRight(duration);
    }

    private int getDuration(MotionEvent e1, MotionEvent e2, int velocityX, int velocityY) {
        int dx = (int) (e1.getRawX() - e2.getRawX());
        int dy = 0;
        int xvel = velocityX;
        int yvel = velocityY;
        return computeSettleDuration(dx, dy, xvel, yvel);
    }

    private int computeSettleDuration(int dx, int dy, int xvel, int yvel) {
        xvel = clampMag(xvel, (int) mMinVelocity, (int) mMaxVelocity);
        yvel = clampMag(yvel, (int) mMinVelocity, (int) mMaxVelocity);
        final int absDx = Math.abs(dx);
        final int absDy = Math.abs(dy);
        final int absXVel = Math.abs(xvel);
        final int absYVel = Math.abs(yvel);
        final int addedVel = absXVel + absYVel;
        final int addedDistance = absDx + absDy;

        final float xweight = xvel != 0 ? (float) absXVel / addedVel :
                (float) absDx / addedDistance;
        final float yweight = yvel != 0 ? (float) absYVel / addedVel :
                (float) absDy / addedDistance;

        int xduration = computeAxisDuration(dx, xvel, getWidth());
        int yduration = computeAxisDuration(dy, yvel, 0);

        return (int) (xduration * xweight + yduration * yweight);
    }

    private int clampMag(int value, int absMin, int absMax) {
        final int absValue = Math.abs(value);
        if (absValue < absMin) return 0;
        if (absValue > absMax) return value > 0 ? absMax : -absMax;
        return value;
    }

    private int computeAxisDuration(int delta, int velocity, int motionRange) {
        if (delta == 0) {
            return 0;
        }

        final int width = getWidth();
        final int halfWidth = width / 2;
        final float distanceRatio = Math.min(1f, (float) Math.abs(delta) / width);
        final float distance = halfWidth + halfWidth
                * distanceInfluenceForSnapDuration(distanceRatio);

        int duration;
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            duration = 4 * Math.round(MAX_SETTLE_DURATION * Math.abs(distance / velocity));
        } else {
            final float range = (float) Math.abs(delta) / motionRange;
            duration = (int) ((range + 1) * BASE_SETTLE_DURATION);
        }
        return Math.min(duration, MAX_SETTLE_DURATION);
    }

    private float distanceInfluenceForSnapDuration(float f) {
        f -= 0.5f; // center the values about 0.
        f *= 0.3f * Math.PI / 2.0f;
        return (float) Math.sin(f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, String.format("FlipEventView :: onTouchEvent()"));

        if (event.getAction() == MotionEvent.ACTION_UP) {
            presenter.onUp(event.getX());
            return true;
        }

        return detector.onTouchEvent(event);
    }

    public void setPresenter(FlipCardPresenter presenter) {
        this.presenter = presenter;
    }
}
