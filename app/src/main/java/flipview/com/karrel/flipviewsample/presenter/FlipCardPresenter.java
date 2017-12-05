package flipview.com.karrel.flipviewsample.presenter;

import android.view.MotionEvent;

/**
 * Created by Rell on 2017. 12. 1..
 */

public interface FlipCardPresenter {
    void onScroll(float pressedX, float moveX);

    void onDown(float x);

    void onUp(float x);

    void swipeRight(long duration);

    void swipeLeft(long duration);

    interface View {

        void showFrontCard(boolean isShowFrontCard);

        void rotateY(boolean isShowFrontCard, float rotateY);

        void rotateAnimation(long duration, boolean isShowFrontCard, float startY, float endY);
    }
}
