package fordjm.cs995.uwm.edu.androidformfiller;

import android.content.Intent;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import formfiller.utilities.TestSetup;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    private FormFillerActivity activity;

    @Before
    public void setUp() {
        TestSetup.setupSampleFormComponents();
        Intent intent = new Intent();
        intent.putExtra("ModalityComponents", new String[]{"AndroidGui", "AndroidTtsInvisible"});
        activity = Robolectric.buildActivity(FormFillerActivity.class).withIntent(intent).create().get();
    }

    @Test
    public void hasTwoEventSinks() {
        //assertThat(EventSinks.itsSinks.size(), is(2));        // Change contains() to take a String and give each sink a name
        //assertThat()  // activity has set expected view?
        //  TODO:   How to test that correct event was captured?
    }

    @Test
    public void onReceivingPushedEvent_ViewComponentsAreEnabled() {

    }

}
