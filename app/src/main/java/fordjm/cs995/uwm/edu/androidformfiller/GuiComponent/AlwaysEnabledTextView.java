package fordjm.cs995.uwm.edu.androidformfiller.GuiComponent;

import android.widget.TextView;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;

public class AlwaysEnabledTextView extends TextView {
    public AlwaysEnabledTextView(FormFillerActivity activity) {
        super(activity);
    }

    @Override
    public void setEnabled(boolean enabled) {
        return;
    }
}
