package fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent;

import android.annotation.SuppressLint;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.HashMap;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import root.gast.speech.tts.TextToSpeechStartupListener;
import root.gast.speech.tts.TextToSpeechUtils;

public class TextToSpeechStartupListenerImpl implements TextToSpeechStartupListener {
    private static final String TAG = "TTSStartupListenerImpl";
    private FormFillerActivity activity;
    private String speech;
    private TextToSpeech tts;
    private boolean isDone;

    public TextToSpeechStartupListenerImpl(FormFillerActivity activity, String speech) {
        this.activity = activity;
        this.speech = speech;
    }

    public void onSuccessfulInit(TextToSpeech tts) {
        this.tts = tts;
        setTtsListener();
        activity.disableUi();
        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, makeParams());
    }

    private HashMap<String, String> makeParams() {
        return TextToSpeechUtils.makeParamsWith(speech);
    }

    /**
     * Adapted from TextToSpeechDemo.java, Retrieved 12/5/2015
     * set the TTS listener to call {@link #onDone(String)} depending on the
     * Build.Version.SDK_INT
     */
    @SuppressLint("NewApi")
    private void setTtsListener()
    {
            int listenerResult =
                    tts.setOnUtteranceProgressListener(new UtteranceProgressListener()
                    {
                        @Override
                        public void onDone(String utteranceId)
                        {
                            Log.d(TAG, "utterance completed: " + utteranceId);
                            TextToSpeechStartupListenerImpl.this.onDone(utteranceId);
                        }

                        @Override
                        public void onError(String utteranceId)
                        {
                            Log.e(TAG, "TTS error");
                        }

                        @Override
                        public void onStart(String utteranceId)
                        {
                            Log.d(TAG, "TTS start");
                        }
                    });
            if (listenerResult != TextToSpeech.SUCCESS)
            {
                Log.e(TAG, "failed to add utterance progress listener");
            }
    }

    public void onRequireLanguageData() {

    }

    public void onWaitingForLanguageData() {

    }

    private void onDone(final String utteranceId) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                isDone = true;
                activity.enableUi();
                tts.stop();
                tts.shutdown();
                Log.d(TAG, "tts shutdown after: " + utteranceId);
            }
        });
    }

    public void onFailedToInit() {

    }

    public boolean hasTtsObject() {
        return tts != null;
        //  TODO:   Interface that extends TTSStartupListener
    }

    public boolean isDone() {
        return isDone;
    }   //  TODO:   Interface that extends TTSStartupListener

}