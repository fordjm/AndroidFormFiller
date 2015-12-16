package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.event.eventSink.EventSink;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
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

    public void receive(AddAnswerViewModel addAnswerViewModel) {
        AndroidGuiAddAnswerView view = new AndroidGuiAddAnswerView(activity);
        view.generateView(addAnswerViewModel);
    }

}
