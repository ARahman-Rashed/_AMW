package workmail.handlers;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.SimpleCard;
import workmail.HandlerHelper;

import java.util.ArrayList;

/**
 * Created by rasabdel on 8/22/2016.
 */
public abstract class IntentHandler {

    HandlerHelper helper;

    public IntentHandler(HandlerHelper helper) {
        this.helper = helper;
    }

    public abstract SpeechletResponse getResponse(ArrayList<Slot> slots);

}
