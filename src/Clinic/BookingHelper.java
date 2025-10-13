package Clinic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Booking helper provides:
 *  - check for free slots
 *  - book an appointment
 *  - list / cancel appointments
 */
public class BookingHelper {
    public final List<AppointmentV2> bookings = new ArrayList<>();

    // check if a specific datetime is free for this vet
    public boolean isSlotFree(Vet vet, LocalDateTime dateTime) {
        for (AppointmentV2 a : bookings) {
            if (a.getVet().equals(vet) && a.getWhen().equals(dateTime)) return false;
        }
        return true;
    }

    // attempt to book, return true if success
    public boolean book(AppointmentV2 appt) {
        if (!isSlotFree(appt.getVet(), appt.getWhen())) return false;
        bookings.add(appt);
        return true;
    }

    // cancel by exact appointment
    public boolean cancel(AppointmentV2 appt) {
        return bookings.remove(appt);
    }

    // list all appointments for a vet on a given date
    public List<AppointmentV2> getAppointmentsFor(Vet vet, LocalDate date) {
        List<AppointmentV2> out = new ArrayList<>();
        for (AppointmentV2 a : bookings) {
            if (a.getVet().equals(vet) && a.getWhen().toLocalDate().equals(date)) {
                out.add(a);
            }
        }
        out.sort(Comparator.comparing(AppointmentV2::getWhen));
        return out;
    }

    // list all bookings (debug)
    public List<AppointmentV2> allBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
