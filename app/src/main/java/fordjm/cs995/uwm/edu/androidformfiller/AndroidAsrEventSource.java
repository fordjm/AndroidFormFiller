package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;

import formfiller.delivery.event.eventSource.EventSource;
import root.gast.speech.RecognizerIntentFactory;

public class AndroidAsrEventSource implements EventSource, RecognitionListener {
    private MainActivity activity;
    private SpeechRecognizer recognizer;

    public AndroidAsrEventSource(MainActivity activity) {
        this.activity = activity;
    }

    public void disable() {
        if (recognizer == null) return;

        recognizer.stopListening();
    }

    public void enable() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Intent recognizerIntent = createRecognizerIntent();
                recognizeDirectly(recognizerIntent);
            }
        });
    }

    private Intent createRecognizerIntent(){
        Intent recognizerIntent = RecognizerIntentFactory.getBlankRecognizeIntent();
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                activity.getApplication().getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1000);
        return recognizerIntent;
    }

    //  Start copied GAST SpeechRecognitionAcvitity.java code
    public void recognizeDirectly(Intent recognizerIntent)
    {
        // SpeechRecognizer requires EXTRA_CALLING_PACKAGE, so add if it's not
        // here
        if (!recognizerIntent.hasExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE))
        {
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                    "com.dummy");
        }
        SpeechRecognizer recognizer = getSpeechRecognizer();
        recognizer.startListening(recognizerIntent);
    }

    private SpeechRecognizer getSpeechRecognizer()
    {
        if (recognizer == null)
        {
            recognizer = SpeechRecognizer.createSpeechRecognizer(activity);
            recognizer.setRecognitionListener(this);
        }
        return recognizer;
    }
    //  End copied GAST Speech Recognition Acvitity code

    public void onReadyForSpeech(Bundle params) {
        //activity.toastNotification("Ready for speech");
    }

    public void onBeginningOfSpeech() {
        //activity.toastNotification("Beginning of speech");
    }

    public void onRmsChanged(float rmsdB) {
        //activity.toastNotification("RMS changed");
    }

    public void onBufferReceived(byte[] buffer) {
        //activity.toastNotification("Buffer received");
    }

    public void onEndOfSpeech() {
        //activity.toastNotification("End of speech");
    }

    public void onError(int error) {
        //activity.toastNotification(getErrorMessage(error));
    }

    @NonNull
    private String getErrorMessage(int error) {
        switch(error){
            case 1: return "Network operation timed out.";
            case 2: return "Unspecified network-related error.";
            case 3: return "Audio recording error.";
            case 4: return "Server sends error status.";
            case 5: return "Unspecified client side error";
            case 6: return "No speech input.";
            case 7: return "No recognition result matched.";
            case 8: return "RecognitionService busy.";
            case 9: return "Server sends error status.";
            default: return "Unrecognized error code.";
        }
    }

    public void onResults(final Bundle results) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                disable();
                String topResult = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0);
                activity.onReceivePushedEvent(topResult);
            }
        });
    }

    public void onPartialResults(Bundle partialResults) {

    }

    public void onEvent(int eventType, Bundle params) {

    }

}
