package fordjm.cs995.uwm.edu.androidformfiller.GuiComponent;

import android.widget.EditText;
import android.widget.LinearLayout;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import fordjm.cs995.uwm.edu.androidformfiller.R;
import formfiller.usecases.addAnswer.AddAnswerViewModel;

public class AndroidGuiAddAnswerView {
    private FormFillerActivity activity;

    public AndroidGuiAddAnswerView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView(AddAnswerViewModel addAnswerViewModel) {
        LinearLayout answerView = (LinearLayout) activity.findViewById(R.id.answer_container);
        EditText editText = (EditText) answerView.getChildAt(0);    //  TODO:   Fix brittle code
        String content = removeQuotes(addAnswerViewModel.answerContent.toString());
        editText.setText(content);
        editText.invalidate();
    }

    private String removeQuotes(String s) {
        return s.replaceAll("\"", "");
    }

}
