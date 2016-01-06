package fordjm.cs995.uwm.edu.androidformfiller.InteractionManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fordjm.cs995.uwm.edu.androidformfiller.R;
import formfiller.EventSinks;
import formfiller.EventSources;
import formfiller.delivery.event.eventSink.EventSinkFactory;
import formfiller.delivery.router.Router;
import formfiller.utilities.TestSetup;

public class FormFillerActivity extends AppCompatActivity {
    private AndroidEventHandler eventHandler;
    private UiDisableCallTracker uiDisableCallTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:    What to store in savedInstanceState?
        if (savedInstanceState == null) setUp();
        uiDisableCallTracker = new UiDisableCallTracker();
        handleStartEvent();
    }

    public void setUp() {
        setContentView(R.layout.activity_main);
        setUpFields(getIntent());
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
        onReceivePushedEvent("ask question current");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onReceiveGeneratedView(ForegroundView view) {
        setContentView(view.getView());
    }

    public void onReceiveGeneratedView(final BackgroundView view) {
        runOnUiThread(new Runnable() {
            public void run() {
                view.view(FormFillerActivity.this);
            }
        });
    }

    public void disableUi() {
        //uiDisableCallTracker.addDisable();
        EventSources.disable();
    }

    public void enableUi() {
        /*uiDisableCallTracker.addEnable();

        if (uiDisableCallTracker.shouldEnable())*/
            EventSources.enable();
    }

    public void onReceivePushedEvent(final String event) {
        runOnUiThread(new Runnable() {
            public void run() {
                eventHandler.handleEvent(event);
            }
        });
    }

}
