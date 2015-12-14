package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import formfiller.EventSinks;
import formfiller.EventSources;
import formfiller.delivery.event.eventSink.EventSinkFactory;
import formfiller.delivery.router.Router;
import formfiller.utilities.TestSetup;

public class MainActivity extends AppCompatActivity {
    private AndroidEventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:    What to store in savedInstanceState?
        if (savedInstanceState == null) setUp();
        handleStartEvent();
        //  TODO:   Enable both push and pull event sources?  Let callbacks enable?
    }

    public void setUp() {
        setContentView(R.layout.activity_main);
        //setUpFields(getIntent());   // Intent comes from ModalitySetupActivity (TODO)
        setUpFields(createTempIntent());
    }

    private Intent createTempIntent() {
        Intent intent = new Intent();
        //  TODO:   Can have at most one visible event source per screen (will need priority ordering)
        intent.putExtra("ModalityComponents", new String[]{"AndroidGui", "AndroidVoiceInvisible"});
        return intent;
    }

    private void setUpFields(Intent intent) {
        String[] modalityComponents = intent.getStringArrayExtra("ModalityComponents");
        setupEventSources(modalityComponents);
        setupEventSinks(modalityComponents);
        TestSetup.setupSampleFormComponents();
        setupClassVariables();
    }

    private void setupEventSources(String[] modalityComponents) {
        AndroidEventSourceFactory factory = new AndroidEventSourceFactory(this);
        for (String component : modalityComponents)
            EventSources.add(factory.make(component));
    }

    private void setupEventSinks(String[] modalityComponents) {
        EventSinkFactory factory = new AndroidEventSinkFactory(this);
        for (String component : modalityComponents)
            EventSinks.add(factory.make(component));
    }

    private void setupClassVariables() {
        Router router = PlaceholderAndroidRouterFactory.makeRouter();
        eventHandler = new AndroidEventHandler(router); //  TODO:   Remove temp solution
    }

    private void handleStartEvent() {
        eventHandler.handleEvent("ask question next");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //  TODO:   Start add methods to new interface
    public void onReceiveGeneratedView(View view) { setContentView(view); }

    //  TODO:   BackgroundView?
    //          Disable UI (But when to enable it again?)
    public void onReceiveGeneratedView(final AudioView view) {
        runOnUiThread(new Runnable() {
            public void run() {
                view.view(MainActivity.this);
            }
        });
    }

    public void toastNotification(final String notification){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, (CharSequence) notification, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void disableUi() {
        EventSources.disable();
    }

    public void enableUi() {
        EventSources.enable();
    }

    public void onReceivePushedEvent(String event) {
        toastNotification("Received event: " + event);  //TODO: Remove
        eventHandler.handleEvent(event);
    }
    //  TODO:   End add methods to new interface

}
