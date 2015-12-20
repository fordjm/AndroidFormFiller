package fordjm.cs995.uwm.edu.androidformfiller;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import formfiller.usecases.addAnswer.AddAnswerViewModel;

public class AndroidGuiAddAnswerView {
    private MainActivity activity;

    public AndroidGuiAddAnswerView(MainActivity activity) {
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
