package fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.VisibleVoiceComponent;

import android.view.ViewGroup;

import fordjm.cs995.uwm.edu.androidformfiller.GuiComponent.LayoutUtilities;
import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import fordjm.cs995.uwm.edu.androidformfiller.R;
import fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.AndroidAsrEventSource;

public class AndroidAsrVisibleEventSource extends AndroidAsrEventSource {
    public AndroidAsrVisibleEventSource(FormFillerActivity activity) {
        super(activity);
    }

    @Override
    public void disable() {
        super.disable();
        LayoutUtilities.recursivelyDisable((ViewGroup) activity.findViewById(R.id.tts_visible_view));
    }

    @Override
    public void enable() {
        super.enable();
        LayoutUtilities.recursivelyEnable((ViewGroup) activity.findViewById(R.id.tts_visible_view));
    }

}
