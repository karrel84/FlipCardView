package flipview.com.karrel.flipviewsample.presenter;

/**
 * Created by Rell on 2017. 12. 1..
 */

public interface FlipCardPresenter {
    void onScroll(float pressedX, float moveX);

    void onDown(float x);

    void onUp(float x);

    interface View {

        void rotateY(float rotateY);

        void showCard1(boolean b);
    }
}
