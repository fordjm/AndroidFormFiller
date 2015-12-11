package fordjm.cs995.uwm.edu.androidformfiller;

import java.util.Arrays;

import formfiller.delivery.event.EventParser;
import formfiller.delivery.event.impl.ParsedEvent;

public class AndroidEventParser implements EventParser {
    //  TODO:   Remove temp hack.
    public ParsedEvent parse(String s) {
        ParsedEvent result = new ParsedEvent();
        result.method = "ask question";
        result.parameters = Arrays.asList("next");
        return result;
    }
}
