package fordjm.cs995.uwm.edu.androidformfiller;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;

import root.gast.speech.tts.TextToSpeechStartupListener;
import root.gast.speech.tts.TextToSpeechUtils;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextToSpeechStartupListenerImplTest {
    private TextToSpeechStartupListenerImpl listener;

    @Mock
    private TextToSpeech mockTextToSpeech;

    @Mock
    private MainActivity mockActivity;

    @Before
    public void setUp() {
        mockActivity = EasyMock.createMock(MainActivity.class);
        listener = new TextToSpeechStartupListenerImpl(mockActivity, "What is your name?");
    }

    @Test
    public void implementsCorrectInterface() {
        assertThat(listener, instanceOf(TextToSpeechStartupListener.class));
    }

    @Test
    public void testOnSuccessfulInit() {
        String speech = "What is your name?";
        mockTextToSpeech = createMock(TextToSpeech.class);
        expect(mockTextToSpeech.setOnUtteranceProgressListener(EasyMock.anyObject(UtteranceProgressListener.class))).andReturn(TextToSpeech.SUCCESS);
        expect(mockTextToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, TextToSpeechUtils.makeParamsWith(speech))).andReturn(TextToSpeech.SUCCESS);

        replay(mockTextToSpeech);
        listener.onSuccessfulInit(mockTextToSpeech);

        assertThat(listener.hasTtsObject(), is(true));
        //assertThat(listener.isDone(), is(true));    //  How to make test work?
        verify(mockTextToSpeech);
    }

}
