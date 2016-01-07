package fordjm.cs995.uwm.edu.androidformfiller.GuiComponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.ForegroundView;
import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import formfiller.usecases.askQuestion.AskQuestionViewModel;

public class AndroidGuiAskQuestionView {
    private FormFillerActivity activity;
    private View formComponentView;

    public AndroidGuiAskQuestionView(FormFillerActivity activity) {
        this.activity = activity;
        formComponentView = getFormComponentView();
    }

    private View getFormComponentView() {
        return new FormComponentLayout(activity);
    }

    public void generateView(AskQuestionViewModel askQuestionViewModel) {
        View view = createViewFromViewModel(askQuestionViewModel);
        ForegroundView result = new ForegroundView(view);
        activity.onReceiveGeneratedView(result);
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
        result.setTextSize(GuiUtilities.getScaledFontSize(activity, 16.0f));
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
        else{
            String viewContent = removeQuotes(answerContent.toString());
            answerContainer.addView((createEditText(viewContent)));
        }
    }

    private String removeQuotes(String s) {
        return s.replaceAll("\"", "");
    }

    private boolean isAnswerContentEmpty(String answerContent) {
        return answerContent == null || answerContent.length() == 0;
    }

    //  TODO:   Clear hint when touched.
    private EditText createEditText(){
        final EditText result = new EditTextWithHardSend(activity);
        result.setHint("Tap Here");
        result.setTextSize(GuiUtilities.getScaledFontSize(activity, 22.0f));
        result.setHeight(new Double(
                activity.getResources().getDisplayMetrics().heightPixels * 0.375).intValue());
        result.setWidth(activity.getResources().getDisplayMetrics().widthPixels);
        result.setGravity(Gravity.CENTER);
        result.setCursorVisible(false);
        result.setImeOptions(EditorInfo.IME_ACTION_SEND);
        result.setOnClickListener(makeOnClickListener(result));
        result.setOnEditorActionListener(makeOnEditorActionListener(result));
        return result;
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
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    closeSoftKeyboard();
                    return handleSendAction(editText);
                }
                return false;
            }
        };
    }

    private boolean handleSendAction(EditText editText) {
        editText.setCursorVisible(false);
        String eventString = "add answer " + editText.getText().toString();
        activity.onReceivePushedEvent(eventString);
        return true;
    }

    // From:
    // http://stackoverflow.com/questions/2342620/how-to-hide-keyboard-after-typing-in-edittext-in-android
    // Retrieved:	05/25/2015
    private void closeSoftKeyboard(){
        Context context = activity;
        InputMethodManager inputManager =
                (InputMethodManager) context.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                activity.getWindow().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private TextView.OnClickListener makeOnClickListener(final EditText editText) {
        return new TextView.OnClickListener() {
            public void onClick(View view) {
                editText.setCursorVisible(true);
                editText.setHint("");
            }
        };
    }

    private class EditTextWithHardSend extends EditText {
        public EditTextWithHardSend(Context context) {
            super(context);
        }

        public boolean onKeyUp(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER)
                return handleSendAction(this);
            else
                return super.onKeyUp(keyCode, event);
        }
    }
}