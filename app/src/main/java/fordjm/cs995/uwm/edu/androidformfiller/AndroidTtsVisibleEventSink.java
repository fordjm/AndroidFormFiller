package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.usecases.addAnswer.AddAnswerViewModel;
import formfiller.usecases.askQuestion.AskQuestionViewModel;


public class AndroidTtsVisibleEventSink extends AndroidTtsEventSink {

    public AndroidTtsVisibleEventSink(FormFillerActivity activity) {
        super(activity);
    }

    public void receive(AskQuestionViewModel askQuestionViewModel) {
        super.receive(askQuestionViewModel);
        generateViewToDisplay();
    }

    private void generateViewToDisplay() {
        AndroidTtsVisibleView view = new AndroidTtsVisibleView(activity);
        view.generateView();
    }

    public void receive(AddAnswerViewModel addAnswerViewModel) {
        super.receive(addAnswerViewModel);
        generateViewToDisplay();
    }

    public void receive(NotificationViewModel notificationViewModel) {
        super.receive(notificationViewModel);
        generateViewToDisplay();
    }

}
