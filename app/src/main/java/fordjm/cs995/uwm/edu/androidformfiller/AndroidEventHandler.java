package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.delivery.router.Router;

//  TODO:   Refactor and delete hacky workaround
public class AndroidEventHandler {
    private AndroidEventParser androidParser;
    private Router router;

    public AndroidEventHandler(Router router) {
        this.router = router;
    }

    public void handleEvent(String event) {
        ParsedEvent parsedEvent = androidParser.parse(event);
        router.route(parsedEvent);
    }
}
