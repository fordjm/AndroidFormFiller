package fordjm.cs995.uwm.edu.androidformfiller;

import formfiller.usecases.addAnswer.AddAnswerViewModel;

public class AndroidTtsAddAnswerView {
    private FormFillerActivity activity;

    public AndroidTtsAddAnswerView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView(AddAnswerViewModel addAnswerViewModel) {
        AudioView view = new AudioView(activity, createMessage(addAnswerViewModel));
        activity.onReceiveGeneratedView(view);
    }

    private String createMessage(AddAnswerViewModel addAnswerViewModel) {
        return "You added the answer, " + addAnswerViewModel.answerContent.toString();
    }

}
