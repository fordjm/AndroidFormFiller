package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.event.eventSink.*;

public class AndroidEventSinkFactory implements EventSinkFactory {
    private FormFillerActivity activity;

    public AndroidEventSinkFactory(FormFillerActivity mainActivity) {
        activity = mainActivity;
    }

    public EventSink make(String name) {
        if (name.equalsIgnoreCase("AndroidGui"))
            return new AndroidGuiEventSink(activity);
        else if (name.equalsIgnoreCase("AndroidVoiceInvisible"))
            return new AndroidTtsEventSink(activity);
        else if (name.equalsIgnoreCase("AndroidVoiceVisible"))
            return new AndroidTtsVisibleEventSink(activity);
        else
            return null;
    }

}