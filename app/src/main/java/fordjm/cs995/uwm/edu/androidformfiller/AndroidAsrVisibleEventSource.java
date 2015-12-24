package fordjm.cs995.uwm.edu.androidformfiller;

import android.view.ViewGroup;

public class AndroidAsrVisibleEventSource extends AndroidAsrEventSource {
    public AndroidAsrVisibleEventSource(FormFillerActivity activity) {
        super(activity);
    }

    @Override
    public void disable() {
        super.disable();
        LayoutUtils.recursivelyDisable((ViewGroup) activity.findViewById(R.id.tts_visible_view));
    }

    @Override
    public void enable() {
        super.enable();
        LayoutUtils.recursivelyEnable((ViewGroup) activity.findViewById(R.id.tts_visible_view));
    }

}
