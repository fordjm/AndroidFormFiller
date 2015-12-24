package fordjm.cs995.uwm.edu.androidformfiller;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TtsDisplayView extends RelativeLayout {
    FormFillerActivity activity;

    public TtsDisplayView(FormFillerActivity activity) {
        super(activity);
        this.activity = activity;
        setupLayout();
    }

    private void setupLayout() {
        setId(R.id.tts_visible_view);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setPadding(R.dimen.activity_horizontal_margin, R.dimen.activity_vertical_margin, R.dimen.activity_horizontal_margin,
                R.dimen.activity_vertical_margin);
        setBackgroundColor(255, 255, 0);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled)
            setEnabled();
        else
            setDisabled();
    }

    private void setEnabled() {
        setBackgroundGreen();
    }

    private void setBackgroundGreen() {
        setBackgroundColor(0, 255, 0);
    }

    private void setBackgroundColor(final int red, final int green, final int blue){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
    }

    private void setDisabled() {
        setBackgroundRed();
    }

    private void setBackgroundRed() {
        setBackgroundColor(255, 0, 0);
    }

}
