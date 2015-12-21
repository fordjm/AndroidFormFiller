package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidGuiAskQuestionView {
    private MainActivity activity;
    private View formComponentView;

    public AndroidGuiAskQuestionView(MainActivity activity) {
        this.activity = activity;
        formComponentView = getFormComponentView();
    }

    private View getFormComponentView() {
        return new FormComponentLayout(activity);
    }

    public void generateView(AskQuestionViewModel askQuestionViewModel) {
        View view = createViewFromViewModel(askQuestionViewModel);
        activity.onReceiveGeneratedView(view);
    }

    private View createViewFromViewModel(AskQuestionViewModel askQuestionViewModel) {
        addQuestionViewToFormComponentView(askQuestionViewModel.message);
        addAnswerViewToFormComponentView(askQuestionViewModel.answerContent);
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
        TextView result = new AlwaysEnabledTextView(activity);
        result.setText(message);
        result.setBackgroundColor(0);
        result.setTextSize(GuiUtilities.getFontSize(activity, 16.0f));
        //result.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        result.setGravity(Gravity.CENTER);
        return result;
    }

    //  TODO:  How to display the object as intended?
    private void addAnswerViewToFormComponentView(Object answerContent) {
        LinearLayout answerContainer = getFormComponentChildView("answerContainer");
        answerContainer.removeAllViews();
        if (isAnswerContentEmpty(answerContent.toString()))
            answerContainer.addView(createEditText());
        else
            answerContainer.addView((createEditText(answerContent.toString())));
    }

    private boolean isAnswerContentEmpty(String answerContent) {
        return answerContent == null || answerContent.length() == 0;
    }

    //  TODO:   Clear hint when touched.
    private EditText createEditText(){
        // FILL_PARENT, WRAP_PARENT, (imeOptions=) actionSend
        final EditText result = new EditText(activity);
        result.setHint("Tap Here");
        result.setTextSize(GuiUtilities.getFontSize(activity, 22.0f));
        result.setHeight(new Double(
                activity.getResources().getDisplayMetrics().heightPixels * 0.375).intValue());
        result.setWidth(activity.getResources().getDisplayMetrics().widthPixels);
        result.setGravity(Gravity.CENTER);
        result.setImeOptions(EditorInfo.IME_ACTION_SEND);
        //result.setOnFocusChangeListener(makeOnFocusListener(result));
        result.setOnEditorActionListener(makeOnEditorActionListener(result));
        return result;
    }

    private View.OnFocusChangeListener makeOnFocusListener(final EditText result) {
        return new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                result.setHint("");
            }
        };
    }

    private EditText createEditText(String message) {
        EditText result = createEditText();
        result.setText(message);
        return result;
    }

    @NonNull
    private TextView.OnEditorActionListener makeOnEditorActionListener(final EditText editText) {
        return new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event){
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    String eventString = "add answer " + editText.getText().toString();
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