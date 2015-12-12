package fordjm.cs995.uwm.edu.androidformfiller;

import android.speech.tts.TextToSpeech;

import formfiller.delivery.event.eventSink.EventSink;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidTtsEventSink implements EventSink {
    private MainActivity activity;

    public AndroidTtsEventSink(MainActivity activity) {
        this.activity = activity;
    }

    public void receive(final AskQuestionViewModel askQuestionViewModel) {
        AndroidTtsAskQuestionView view = new AndroidTtsAskQuestionView(activity);
        view.generateView(askQuestionViewModel);
    }

}
