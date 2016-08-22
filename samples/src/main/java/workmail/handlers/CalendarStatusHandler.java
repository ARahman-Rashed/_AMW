package workmail.handlers;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import workmail.HandlerHelper;

import java.util.ArrayList;

import static workmail.HandlerHelper.NO_APPOINTMENTS;
import static workmail.HandlerHelper.TODAY;
import static workmail.HandlerHelper.YOU_HAVE_APPOINTMENTS;

/**
 * Created by rasabdel on 8/22/2016.
 */
public class CalendarStatusHandler extends IntentHandler {


    public CalendarStatusHandler(HandlerHelper helper) {
        super(helper);
    }

    @Override
    public SpeechletResponse getResponse(ArrayList<Slot> slots) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        String speechText = "";
        SimpleCard card = new SimpleCard();
        boolean busy = helper.dayHasAppointments(TODAY);
        if (!busy) {
            return noAppointmentsResponse();
        } else {
            speech.setText(YOU_HAVE_APPOINTMENTS);
            card.setTitle("Your schedule today");
            card.setContent(speechText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(speech);
            return SpeechletResponse.newAskResponse(speech, reprompt);
        }
    }

    public static SpeechletResponse noAppointmentsResponse() {

        String speechText = NO_APPOINTMENTS;
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(NO_APPOINTMENTS);
        SimpleCard card = new SimpleCard();
        card.setTitle("Your schedule today");
        card.setContent(speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }
}
