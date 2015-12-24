package fordjm.cs995.uwm.edu.androidformfiller;

import android.widget.TextView;

public class AlwaysEnabledTextView extends TextView {
    public AlwaysEnabledTextView(FormFillerActivity activity) {
        super(activity);
    }

    @Override
    public void setEnabled(boolean enabled) {
        return;
    }
}
