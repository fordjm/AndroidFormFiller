package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.event.eventSource.*;

public class AndroidEventSourceFactory implements EventSourceFactory {
    private MainActivity activity;

    public AndroidEventSourceFactory(MainActivity activity) {
        this.activity = activity;
    }

    public EventSource make(String component) {
        if (component.equalsIgnoreCase("AndroidGui"))
            return new AndroidGuiEventSource(activity);
        else if (component.equalsIgnoreCase("AndroidVoiceInvisible"))
            return new AndroidAsrEventSource(activity);
        else
            return null;
    }
}
