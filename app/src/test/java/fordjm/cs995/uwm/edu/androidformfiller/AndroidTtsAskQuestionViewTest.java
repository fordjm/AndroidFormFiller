package fordjm.cs995.uwm.edu.androidformfiller;

import android.support.annotation.NonNull;

import org.easymock.Mock;
import org.junit.Test;

import formfiller.usecases.askQuestion.AskQuestionViewModel;

import static org.easymock.EasyMock.createMock;

public class AndroidTtsAskQuestionViewTest {
    @Mock
    FormFillerActivity activity;

    @Test
    public void nothing() {
        activity = createMock(FormFillerActivity.class);
        AndroidTtsAskQuestionView view = new AndroidTtsAskQuestionView(activity);
        AskQuestionViewModel viewModel = makeAskQuestionViewModel();

        view.generateView(viewModel);

        //  TODO:   What am I testing?
    }

    @NonNull
    private AskQuestionViewModel makeAskQuestionViewModel() {
        AskQuestionViewModel result = new AskQuestionViewModel();
        result.id = "id";
        result.message = "message";
        result.format = "Unstructured";
        result.answerContent = "answerContent";
        return result;
    }

}
