package fordjm.cs995.uwm.edu.androidformfiller;

import android.view.View;

public class AndroidTtsVisibleView {
    private FormFillerActivity activity;

    public AndroidTtsVisibleView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView() {
        View result = new TtsDisplayView(activity);
        activity.onReceiveGeneratedView(result);
    }

}
