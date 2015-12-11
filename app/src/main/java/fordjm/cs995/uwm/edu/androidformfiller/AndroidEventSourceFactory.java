package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.event.EventSource;

/**
 * Created by Jason on 12/9/2015.
 */
public class AndroidEventSourceFactory {
    private MainActivity activity;

    public AndroidEventSourceFactory(MainActivity activity) {
        this.activity = activity;
    }

    public EventSource make(String component) {
        if (component.equalsIgnoreCase("AndroidVoiceInvisible"))
            return new AndroidAsrEventSource(activity);
        else
            return null;
    }
}
