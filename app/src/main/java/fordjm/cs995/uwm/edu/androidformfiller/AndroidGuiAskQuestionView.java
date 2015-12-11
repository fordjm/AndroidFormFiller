package fordjm.cs995.uwm.edu.androidformfiller;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidGuiAskQuestionView {
    private MainActivity activity;
    private View formComponentView;

    public AndroidGuiAskQuestionView(MainActivity activity) {
        this.activity = activity;
        formComponentView = getFormComponentView();
    }

    private View getFormComponentView() {
        return LayoutInflater.from(activity).inflate(R.layout.form_component_view, null);
    }

    public void generateView(AskQuestionViewModel askQuestionViewModel) {
        View view = createViewFromViewModel(askQuestionViewModel);
        activity.onReceiveGeneratedView(view);
    }

    private View createViewFromViewModel(AskQuestionViewModel askQuestionViewModel) {
        addQuestionViewToFormComponentView(askQuestionViewModel.message);
        addAnswerViewToFormComponentView(askQuestionViewModel);
        return formComponentView;
    }

    private void addQuestionViewToFormComponentView(String message) {
        LinearLayout questionContainer = getFormComponentChildView("questionContainer");
        questionContainer.removeAllViews();
        TextView questionView = createTextView(message);
        questionContainer.addView(questionView);
    }

    private LinearLayout getFormComponentChildView(String tag) {
        return (LinearLayout) formComponentView.findViewWithTag(tag);
    }

    @NonNull
    private TextView createTextView(String message) {
        TextView result = new TextView(activity);
        result.setText(message);
        return result;
    }

    //  TODO: Make this method create proper fragments.
    private void addAnswerViewToFormComponentView(AskQuestionViewModel askQuestionViewModel) {
        LinearLayout answerContainer = getFormComponentChildView("answerContainer");
        answerContainer.removeAllViews();
        // TODO:  How to display the object as intended?
        TextView answerView = createTextView("Placeholder answer content");
        answerContainer.addView(answerView);
    }

}