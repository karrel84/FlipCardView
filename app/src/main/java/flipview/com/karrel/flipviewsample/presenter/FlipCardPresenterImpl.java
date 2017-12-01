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

    @Override
    public void onScroll(float pressedX, float moveX) {
        Log.d(TAG, String.format("onScroll(%s, %s)", pressedX, moveX));

        float rotateY = moveX - pressedX;
        view.rotateY(rotateY);
    }
}
