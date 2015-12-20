package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

//  TODO:   Make child views visible
public class FormComponentLayout extends LinearLayout {
    private MainActivity activity;
    private SwipeGestureListener gestureListener;

    public FormComponentLayout(MainActivity activity) {
        super(activity);
        this.activity = activity;
        gestureListener = new SwipeGestureListener(activity);
        setUpLayout(activity);
    }

    private void setUpLayout(MainActivity activity) {
        setId(R.id.form_component_container);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(VERTICAL);
        addView(makeQuestionContainer(activity));
        addView(makeAnswerContainer(activity));
    }

    private LinearLayout makeQuestionContainer(Context context) {
        LinearLayout result = new LinearLayout(context);
        result.setId(R.id.question_container);
        result.setTag("questionContainer");
        result.setOrientation(HORIZONTAL);
        result.setLayoutParams(makeLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.625f));
        return result;
    }

    private LinearLayout.LayoutParams makeLayoutParams(int width, int height, float weight) {
        return new LinearLayout.LayoutParams(width, height, weight);
    }

    private LinearLayout makeAnswerContainer(Context context) {
        LinearLayout result = new LinearLayout(context);
        result.setId(R.id.answer_container);
        result.setTag("answerContainer");
        result.setOrientation(HORIZONTAL);
        result.setLayoutParams(makeLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.375f));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isGestureDetectorHandling = gestureListener.getDetector().onTouchEvent(event);
        return isGestureDetectorHandling;
        //return super.onTouchEvent(event);
    }

    //  Adapted from:   http://androidtuts4u.blogspot.com/2013/03/swipe-or-onfling-event-android.html
    //  Retrieved:      12/18/2015
    class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener implements
            View.OnTouchListener {
        Context context;
        GestureDetector gDetector;
        static final int SWIPE_MIN_DISTANCE = 120;
        static final int SWIPE_MAX_OFF_PATH = 250;
        static final int SWIPE_THRESHOLD_VELOCITY = 200;

        public SwipeGestureListener() {
            super();
        }

        public SwipeGestureListener(Context context) {
            this(context, null);
        }

        public SwipeGestureListener(Context context, GestureDetector gDetector) {

            if (gDetector == null)
                gDetector = new GestureDetector(context, this);

            this.context = context;
            this.gDetector = gDetector;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        //  TODO:   Clean up
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            /*final int position = lvCountry.pointToPosition(
                    Math.round(e1.getX()), Math.round(e1.getY()));*/

            String countryName = "";

            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH
                        || Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {
                    Toast.makeText(context, "bottomToTop" + countryName,
                            Toast.LENGTH_SHORT).show();
                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {
                    Toast.makeText(context,
                            "topToBottom  " + countryName, Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
                    return false;
                }
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
                    activity.onReceivePushedEvent("ask question next");
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
                    activity.onReceivePushedEvent("ask question previous");
                }
            }
            return true;

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //activity.toastNotification("onTouch");
            gDetector.onTouchEvent(event);
            return true;
        }

        public GestureDetector getDetector() {
            return gDetector;
        }

    }
}
