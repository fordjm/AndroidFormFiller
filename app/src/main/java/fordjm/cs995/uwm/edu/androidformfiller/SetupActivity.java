package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        enlargeMessage();
    }

    private void enlargeMessage() {
        TextView setupMessage = (TextView) findViewById(R.id.setup_message);
        setupMessage.setText(getResources().getString(R.string.message_activity_setup));
        setupMessage.setTextSize(GuiUtilities.getScaledFontSize(this, 24.0f));
    }

    public void startVoice(View view){
        Intent intent = makeIntent("AndroidVoiceVisible");
        start(intent);
    }

    private Intent makeIntent(String... modalities) {
        Intent result = new Intent(SetupActivity.this, FormFillerActivity.class);
        result.putExtra("ModalityComponents", modalities);
        return result;
    }

    private void start(Intent intent) {
        startActivity(intent);
        finish();
    }

    public void startTouch(View view){
        Intent intent = makeIntent("AndroidGui");
        start(intent);
    }

    public void startTouchAndVoice(View view){
        Intent intent = makeIntent("AndroidGui", "AndroidVoiceInvisible");
        start(intent);
    }

}