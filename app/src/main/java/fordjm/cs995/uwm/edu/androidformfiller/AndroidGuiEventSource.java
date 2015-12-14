package fordjm.cs995.uwm.edu.androidformfiller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import fordjm.cs995.uwm.edu.androidformfiller.utilities.ViewAccessor;
import formfiller.delivery.event.eventSource.EventSource;

import static fordjm.cs995.uwm.edu.androidformfiller.R.layout.form_component_view;

public class AndroidGuiEventSource implements EventSource {
    private MainActivity activity;
    public AndroidGuiEventSource(MainActivity activity) {
        this.activity = activity;
    }

    //  TODO:   Make this work with whole form_component_view layout.
    public void disable() {
        LinearLayout answerContainer = getAnswerContainer();
        recursivelyDisable(answerContainer);
    }

    private LinearLayout getAnswerContainer() {
        return (LinearLayout) activity.findViewById(R.id.answer_container);
    }

    public void enable() {
        LinearLayout answerContainer = getAnswerContainer();
        recursivelyEnable(answerContainer);
    }

    //  From:       http://stackoverflow.com/questions/19800422/disabling-all-child-views-inside-the-layout
    //  By:         Haldean Brown
    //  Retrieved:  12/14/2015
    private static void recursivelyDisable(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursivelyDisable((ViewGroup) child);
            } else {
                child.setEnabled(false);
            }
        }
    }

    private void recursivelyEnable(ViewGroup layout) {
        layout.setEnabled(true);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                recursivelyEnable((ViewGroup) child);
            } else {
                child.setEnabled(true);
            }
        }
    }

}
