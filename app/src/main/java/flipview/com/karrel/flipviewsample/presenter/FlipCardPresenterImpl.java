package flipview.com.karrel.flipviewsample.presenter;

import android.util.Log;

/**
 * Created by Rell on 2017. 12. 1..
 */

public class FlipCardPresenterImpl implements FlipCardPresenter {
    private String TAG = FlipCardPresenterImpl.class.getSimpleName();
    private FlipCardPresenter.View view;

    public FlipCardPresenterImpl(View view) {
        this.view = view;
    }

    private float rotateY;

    @Override
    public void onScroll(float pressedX, float moveX) {
        Log.d(TAG, String.format("onScroll(%s, %s)", pressedX, moveX));

        rotateY = moveX - pressedX;
        rotateY /= 2f;
        // 360도를 넘어가면 1도부터시작
        rotateY %= 360f;

        view.rotateY(rotateY);

        Log.d(TAG, String.format("rotateY : %s", rotateY));
        Log.d(TAG, String.format("rotateY abs : %s", Math.abs(rotateY)));
        view.showCard1(Math.abs(rotateY) < 180f);
    }

    @Override
    public void onDown(float x) {
        Log.d(TAG, String.format("onDown(%s)", x));
        // 마지막에 위치한 값을 저장한다
    }

    @Override
    public void onUp(float x) {
        Log.d(TAG, String.format("onUp(%s)", rotateY));
    }
}
