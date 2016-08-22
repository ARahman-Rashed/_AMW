package workmail.handlers;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import workmail.HandlerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rasabdel on 8/22/2016.
 */
public class EmailHandler extends IntentHandler {

    public EmailHandler(HandlerHelper helper) {
        super(helper);
    }

    @Override
    public SpeechletResponse getResponse(ArrayList<Slot> slots) {

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        StringBuilder speechText = new StringBuilder("");
        SimpleCard card = new SimpleCard();
        card.setContent("Emails:");

        int emailsNumber = getNumberOfEmails(slots);

        List<EmailMessage> emails = helper.getEmails(emailsNumber);
        for (EmailMessage email : emails) {
            try {
                speechText.append(String.format("EmailHandler subject: %s \n" +
                        "from: %s \n" +
                        "says: %s \n \n", email.getSubject(), email.getSender(), email.getBody()));
            } catch (ServiceLocalException e) {
                e.printStackTrace();
            }
        }
        speech.setText(speechText.toString());
        card.setContent(speechText.toString());
        return SpeechletResponse.newTellResponse(speech, card);
    }

    private int getNumberOfEmails(ArrayList<Slot> slots) {
        if (slots.size() == 0 || slots.get(0).getValue().equals("Last"))
            return 1;
        return Integer.parseInt(slots.get(0).getValue());
    }


}
