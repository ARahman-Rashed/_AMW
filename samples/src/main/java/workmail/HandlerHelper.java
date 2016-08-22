package workmail;

import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.property.complex.availability.CalendarEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rasabdel on 8/22/2016.
 */
public class HandlerHelper {

    public static final String NO_APPOINTMENTS = "No, you don't have meetings for today";
    public static final String YOU_HAVE_APPOINTMENTS = "Yes, you have meetings for today" +
            "Would you like to hear your meetings?";
    public static final String TODAY = "today";
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static ExchangeServiceClient client = null;

    public HandlerHelper() {
        client = new ExchangeServiceClient();
    }

    public boolean dayHasAppointments(String date) {
        List<CalendarEvent> dayEvents = new ArrayList<>();
        try {
            dayEvents = date.equals(TODAY) ? client.getAppointmentsForDay(formatter.format(new Date())) :
                    client.getAppointmentsForDay(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayEvents.size() != 0;
    }

    public List<CalendarEvent> getAppointmentsForDay(String date) {
        date = date.equals(TODAY) ? formatter.format(new Date()) : date;
        List<CalendarEvent> dayEvents = new ArrayList<>();
        if (dayHasAppointments(date)) {
            try {
                dayEvents = client.getAppointmentsForDay(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dayEvents;
    }

    public List<EmailMessage> getEmails(int n) {
        List<EmailMessage> emails = new ArrayList<>();
        try {
            for (Item item : client.getEmails(n))
                emails.add((EmailMessage) item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emails;
    }
}
