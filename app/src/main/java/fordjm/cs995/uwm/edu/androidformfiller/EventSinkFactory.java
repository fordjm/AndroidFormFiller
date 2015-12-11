package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.EventSink;

//  TODO:   Move into pure java FormFiller module.
public interface EventSinkFactory {
    public EventSink make(String eventSinkName);
}
