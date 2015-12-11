package fordjm.cs995.uwm.edu.androidformfiller;

import android.speech.tts.TextToSpeech;

import formfiller.usecases.askQuestion.AskQuestionViewModel;
import formfiller.utilities.StringUtilities;
import root.gast.speech.tts.TextToSpeechInitializer;

public class AndroidTtsAskQuestionView {
    private final MainActivity activity;
    private TextToSpeech textToSpeech;
    private TextToSpeechStartupListenerImpl ttsStartupListener;
    private TextToSpeechInitializer initializer;

    public AndroidTtsAskQuestionView(MainActivity activity) {
        this.activity = activity;
    }

    public void generateView(AskQuestionViewModel askQuestionViewModel) {
        AudioView view = new AudioView(activity, createMessage(askQuestionViewModel));
        activity.onReceiveGeneratedView(view);
    }

    //  TODO:   Handle multiple question formats.
    private String createMessage(AskQuestionViewModel askQuestionViewModel) {
        String questionMessage = askQuestionViewModel.message;
        String answerMessage = makeAnswerMessage(askQuestionViewModel.answerContent.toString());
        return StringUtilities.makeSpacedString(questionMessage, answerMessage);
    }

    //  TODO:   How to handle lists?
    private String makeAnswerMessage(String answerContent) {
        if (answerContent.length() == 0) return "";

        return "You answered: " + answerContent;
    }

}
