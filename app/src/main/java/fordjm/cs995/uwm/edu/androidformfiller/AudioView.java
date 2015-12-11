package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Context;

import java.util.Locale;

import root.gast.speech.tts.TextToSpeechInitializer;

public class AudioView {
    private MainActivity activity;
    private String message;
    private TextToSpeechStartupListenerImpl ttsStartupListener;
    private TextToSpeechInitializer initializer;

    public AudioView(MainActivity activity, String message) {
        this.activity = activity;
        this.message = message;
    }

    public void view(Context context) {
        ttsStartupListener = new TextToSpeechStartupListenerImpl(activity, message);
        initializer = new TextToSpeechInitializer(context, Locale.getDefault(), ttsStartupListener);
    }
}
