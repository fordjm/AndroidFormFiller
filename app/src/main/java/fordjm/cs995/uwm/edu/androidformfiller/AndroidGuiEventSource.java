package fordjm.cs995.uwm.edu.androidformfiller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import formfiller.delivery.event.eventSource.EventSource;

public class AndroidGuiEventSource implements EventSource {
    private FormFillerActivity activity;
    public AndroidGuiEventSource(FormFillerActivity activity) {
        this.activity = activity;
    }

    //  TODO:   Exempt question_container from disabling (subclass TextView?)
    public void disable() {
        recursivelyDisable(getFormComponentContainer());
    }

    private LinearLayout getFormComponentContainer() {
        return (LinearLayout) activity.findViewById(R.id.form_component_container);
    }

    public void enable() {
        recursivelyEnable(getFormComponentContainer());
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
