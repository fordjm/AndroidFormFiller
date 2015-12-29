package fordjm.cs995.uwm.edu.androidformfiller.InteractionManager;

import formfiller.delivery.event.EventParser;
import formfiller.delivery.event.TfIdfSimilarityEventParsingStrategy;
import formfiller.delivery.event.impl.FormEventParser;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.delivery.router.Router;

public class AndroidEventHandler {
    private EventParser parser;
    private Router router;

    public AndroidEventHandler(Router router) {
        this.parser = new FormEventParser(new TfIdfSimilarityEventParsingStrategy());
        this.router = router;
    }

    public void handleEvent(String event) {
        ParsedEvent parsedEvent = parser.parse(event);
        router.route(parsedEvent);
    }

}
