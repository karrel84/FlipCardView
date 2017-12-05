package flipview.com.karrel.flipviewsample.presenter;

import android.util.Log;

/**
 * Created by Rell on 2017. 12. 1..
 */

public class FlipCardPresenterImpl implements FlipCardPresenter {
    private String TAG = FlipCardPresenterImpl.class.getSimpleName();
    private FlipCardPresenter.View view;
    private boolean isShowFrontCard = true; // 처음은 앞면!
    private float revisionY = 0f;

    public FlipCardPresenterImpl(View view) {
        this.view = view;
    }

    private float rotateY;

    @Override
    public void onScroll(float pressedX, float moveX) {
        rotateY = moveX - pressedX;
        rotateY /= 1.2f;
        // 현재의 각도를 더한다.
        rotateY += revisionY;
        // 360도를 넘어가면 1도부터시작
        rotateY %= 360f;

        Log.d(TAG, String.format("rotateY 1 : %s", rotateY));

        // 0에서 -180도까지만 돌려줄거다 카드가 완전히 돌아가는것을 방지한다.
        if (rotateY > 0) {
            rotateY = 0f;
        } else if (rotateY < -180) {
            rotateY = -180f;
        }

        Log.d(TAG, String.format("rotateY 2 : %s", rotateY));
        // 절대값 90도에서 270도 사이면 카드B를 보여준다.
        final float absRotate = Math.abs(rotateY);

        Log.d(TAG, String.format("absRotate : %s", absRotate));

        // 절대값이 90도 이하이거나 270도를 초과하면 앞면에 대해서 이벤트를 적용한다.
        isShowFrontCard = absRotate <= 90f || absRotate > 270f;
        view.showFrontCard(isShowFrontCard);

        // 뒷면의 경우는 180도를 돌린 상태로 시작한다(이렇게 하지않으면 반전된 이미지가 돌아간다.)
        if (!isShowFrontCard) rotateY += 180f;
        view.rotateY(isShowFrontCard, rotateY);
    }

    @Override
    public void onDown(float x) {
        Log.d(TAG, String.format("onDown(%s)", x));
        // 현재 B면을 보여주고있으면 보정을 해야한다.
        if (isShowFrontCard) {
            revisionY = 0f;
        } else {
            revisionY = -180f;
        }
    }

    @Override
    public void onUp(float x) {
        Log.d(TAG, String.format("onUp(%s)", rotateY));
        // 애니메이션 진행 >> 해당 화면 카드 A or B 를 끝까지 이동시키는 애니메이션을 진행한다..

        float endY = 0f;
        if (!isShowFrontCard) endY = 180f;

        // 남은 각도를 계산해서 duration을 구한다
        long duration = (long) (Math.abs(rotateY) * 4f);
        view.rotateAnimation(duration, isShowFrontCard, rotateY, endY);
    }

    @Override
    public void swipeRight(long duration) {
        isShowFrontCard = false;
        view.rotateAnimation(duration, isShowFrontCard, rotateY, 180f);
    }

    @Override
    public void swipeLeft(long duration) {
        isShowFrontCard = true;
        view.rotateAnimation(duration, isShowFrontCard, rotateY, 0f);
    }
}
