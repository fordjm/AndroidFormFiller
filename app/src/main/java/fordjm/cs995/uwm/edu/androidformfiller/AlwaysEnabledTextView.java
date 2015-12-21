package fordjm.cs995.uwm.edu.androidformfiller;

import android.widget.TextView;

public class AlwaysEnabledTextView extends TextView {
    public AlwaysEnabledTextView(MainActivity activity) {
        super(activity);
    }

    @Override
    public void setEnabled(boolean enabled) {
        return;
    }
}
