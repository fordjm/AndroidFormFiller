package fordjm.cs995.uwm.edu.androidformfiller.GuiComponent;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import formfiller.delivery.event.eventSink.EventSink;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidGuiEventSink implements EventSink {
    private FormFillerActivity activity;

    public AndroidGuiEventSink(FormFillerActivity activity) {
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

    @Override
    public void receive(NotificationViewModel notificationViewModel) {
        AndroidGuiNotificationView view = new AndroidGuiNotificationView(activity);
        view.generateView(notificationViewModel);
    }

}
