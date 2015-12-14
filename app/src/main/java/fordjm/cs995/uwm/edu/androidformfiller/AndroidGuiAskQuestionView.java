package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.util.ArrayList;

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

    //  TODO:  Make this method create proper fragments?
    //  TODO:  How to display the object as intended?
    private void addAnswerViewToFormComponentView(AskQuestionViewModel askQuestionViewModel) {
        LinearLayout answerContainer = getFormComponentChildView("answerContainer");
        answerContainer.removeAllViews();
        if (isAnswerContentEmpty(askQuestionViewModel.answerContent.toString()))
            answerContainer.addView(createEditText());
        else
            answerContainer.addView((createTextView(askQuestionViewModel.answerContent.toString())));
    }

    private boolean isAnswerContentEmpty(String answerContent) {
        return answerContent == null || answerContent.length() == 0;
    }

    //  TODO:   Clear hint when touched.
    private EditText createEditText(){
        // FILL_PARENT, WRAP_PARENT, (imeOptions=) actionSend
        final EditText result = new EditText(activity);
        //result.setBackgroundColor(Util.getButtonColors()[0]);
        result.setHint("tap here");
        //result.setTextSize(Util.getFontSize(activity, 22.0f));
        result.setHeight(new Double(
                activity.getResources().getDisplayMetrics().heightPixels * 0.375).intValue());
        result.setWidth(activity.getResources().getDisplayMetrics().widthPixels);
        result.setGravity(Gravity.CENTER);
        result.setImeOptions(EditorInfo.IME_ACTION_SEND);
        String enteredText = result.getText().toString();
        result.setOnEditorActionListener(makeOnEditorActionListener(enteredText));
        return result;
    }

    @NonNull
    private TextView.OnEditorActionListener makeOnEditorActionListener(final String enteredText) {
        return new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event){
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    String eventString = "add answer" + enteredText;
                    activity.onReceivePushedEvent(eventString);
                    handled = true;
                    closeKeyboard();
                }
                return handled;
            }
        };
    }

    // From:
    // http://stackoverflow.com/questions/2342620/how-to-hide-keyboard-after-typing-in-edittext-in-android
    // Retrieved:	05/25/2015
    private void closeKeyboard(){
        Context context = activity;
        InputMethodManager inputManager =
                (InputMethodManager) context.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                activity.getWindow().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}