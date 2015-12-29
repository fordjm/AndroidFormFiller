package fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.VisibleVoiceComponent;

import android.view.View;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.ForegroundView;
import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;

public class AndroidTtsVisibleView {
    private FormFillerActivity activity;

    public AndroidTtsVisibleView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView() {
        View view = new TtsDisplayView(activity);
        ForegroundView result = new ForegroundView(view);
        activity.onReceiveGeneratedView(result);
    }

}
