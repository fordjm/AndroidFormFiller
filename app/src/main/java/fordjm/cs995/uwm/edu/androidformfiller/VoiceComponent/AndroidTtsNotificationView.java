package fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import formfiller.delivery.viewModel.NotificationViewModel;

public class AndroidTtsNotificationView {
    private FormFillerActivity activity;

    public AndroidTtsNotificationView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView(NotificationViewModel notificationViewModel) {
        AudioView view = new AudioView(activity, notificationViewModel.message);
        activity.onReceiveGeneratedView(view);
    }

}
