package fordjm.cs995.uwm.edu.androidformfiller;

import android.widget.Toast;

import formfiller.delivery.viewModel.NotificationViewModel;

public class AndroidGuiNotificationView {
    private FormFillerActivity activity;

    public AndroidGuiNotificationView(FormFillerActivity activity) {
        this.activity = activity;
    }

    public void generateView(NotificationViewModel notificationViewModel) {
        activity.toastNotification(notificationViewModel.message);
    }

    //  TODO:   Fix duplication in MainActivity.java
    public void toastNotification(final String notification) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, (CharSequence) notification, Toast.LENGTH_LONG).show();
            }
        });
    }

}
