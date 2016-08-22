package workmail.handlers;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import workmail.HandlerHelper;

import java.util.ArrayList;

import static workmail.HandlerHelper.TODAY;
import static workmail.HandlerHelper.YOU_HAVE_APPOINTMENTS;
import static workmail.handlers.CalendarStatusHandler.noAppointmentsResponse;

/**
 * Created by rasabdel on 8/22/2016.
 */
public class TodayScheduleHandler extends IntentHandler {

    public TodayScheduleHandler(HandlerHelper helper) {
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


}
