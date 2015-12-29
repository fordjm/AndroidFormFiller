package fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import formfiller.delivery.event.eventSink.EventSink;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidTtsEventSink implements EventSink {
    protected FormFillerActivity activity;

    public AndroidTtsEventSink(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void receive(final AskQuestionViewModel askQuestionViewModel) {
        AndroidTtsAskQuestionView view = new AndroidTtsAskQuestionView(activity);
        view.generateView(askQuestionViewModel);
    }

    public void receive(AddAnswerViewModel addAnswerViewModel) {
        AndroidTtsAddAnswerView view = new AndroidTtsAddAnswerView(activity);
        view.generateView(addAnswerViewModel);
    }

    public void receive(NotificationViewModel notificationViewModel) {
        AndroidTtsNotificationView view = new AndroidTtsNotificationView(activity);
        view.generateView(notificationViewModel);
    }

}
