package fordjm.cs995.uwm.edu.androidformfiller;

import static junit.framework.Assert.assertNotNull;
import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import fordjm.cs995.uwm.edu.androidformfiller.InteractionManager.FormFillerActivity;
import fordjm.cs995.uwm.edu.androidformfiller.VoiceComponent.AndroidAsrEventSource;
import formfiller.delivery.event.eventSource.EventSource;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AndroidAsrEventSourceTest extends EasyMockSupport {
    private AndroidAsrEventSource eventSource;

    @Mock
    private FormFillerActivity mockActivity;

    @Before
    public void setUp() {
        mockActivity = makeMockActivity();
        eventSource = new AndroidAsrEventSource(mockActivity);
    }

    private FormFillerActivity makeMockActivity() {
        FormFillerActivity result = mock(FormFillerActivity.class);
        expect(result.getPackageName()).andReturn("PackageName");
        expect(result.getPackageName()).andReturn("PackageName");
        replay(result);
        return result;
    }

    @Test
    public void canCreate() {
        assertThat((eventSource instanceof EventSource), is(true));
    }

    @Test
    public void enablingActivatesSpeechRecognition() {
        eventSource.enable();
    }

}
