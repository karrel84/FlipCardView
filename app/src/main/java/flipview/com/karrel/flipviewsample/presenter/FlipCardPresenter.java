package flipview.com.karrel.flipviewsample.presenter;

/**
 * Created by Rell on 2017. 12. 1..
 */

public interface FlipCardPresenter {
    void onScroll(float pressedX, float moveX);

    interface View {

        void rotateY(float rotateY);
    }
}
