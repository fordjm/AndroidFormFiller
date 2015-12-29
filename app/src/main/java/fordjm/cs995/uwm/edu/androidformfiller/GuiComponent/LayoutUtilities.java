package fordjm.cs995.uwm.edu.androidformfiller.GuiComponent;

import android.view.View;
import android.view.ViewGroup;

public class LayoutUtilities {

    //  From:       http://stackoverflow.com/questions/19800422/disabling-all-child-views-inside-the-layout
    //  By:         Haldean Brown
    //  Retrieved:  12/14/2015
    public static void recursivelyDisable(ViewGroup layout) {
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

    public static void recursivelyEnable(ViewGroup layout) {
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
