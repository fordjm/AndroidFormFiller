package fordjm.cs995.uwm.edu.androidformfiller.InteractionManager;

import fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.VisibleVoiceComponent.AndroidAsrVisibleEventSource;
import fordjm.cs995.uwm.edu.androidformfiller.GuiComponent.AndroidGuiEventSource;
import fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.AndroidAsrEventSource;
import formfiller.delivery.event.eventSource.*;

public class AndroidEventSourceFactory implements EventSourceFactory {
    private FormFillerActivity activity;

    public AndroidEventSourceFactory(FormFillerActivity activity) {
        this.activity = activity;
    }

    public EventSource make(String component) {
        if (component.equalsIgnoreCase("AndroidGui"))
            return new AndroidGuiEventSource(activity);
        else if (component.equalsIgnoreCase("AndroidVoiceInvisible"))
            return new AndroidAsrEventSource(activity);
        else if (component.equalsIgnoreCase("AndroidVoiceVisible"))
            return new AndroidAsrVisibleEventSource(activity);
        else
            return null;
    }

}
