package fordjm.cs995.uwm.edu.androidformfiller.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import fordjm.cs995.uwm.edu.androidformfiller.MainActivity;
import fordjm.cs995.uwm.edu.androidformfiller.R;

public class ViewAccessor {
    //  TODO:   Fix duplication between views
    public static View getFormComponentView(MainActivity activity) {
        return LayoutInflater.from(activity).inflate(R.layout.form_component_view, null);
    }

    public static LinearLayout getChildView(View parent, String tag) {
        return (LinearLayout) parent.findViewWithTag(tag);
    }
}
