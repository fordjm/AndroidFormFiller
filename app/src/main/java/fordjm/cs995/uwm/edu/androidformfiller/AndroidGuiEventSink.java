package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.EventSink;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidGuiEventSink implements EventSink {
    private MainActivity activity;

    public AndroidGuiEventSink(MainActivity activity) {
        this.activity = activity;
    }

    public void receive(AskQuestionViewModel askQuestionViewModel) {
        AndroidGuiAskQuestionView view = new AndroidGuiAskQuestionView(activity);
        view.generateView(askQuestionViewModel);
    }

}
