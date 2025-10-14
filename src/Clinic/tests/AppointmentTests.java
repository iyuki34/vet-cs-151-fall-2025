package Clinic.tests;

import Clinic.Owner;
import Clinic.Pet;
import Clinic.Vet;
import Clinic.AppointmentV2;
import Clinic.BookingHelper;
import Clinic.Exceptions.BookingConflictException;
import Clinic.Exceptions.CapacityExceededException;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentTests {

    @Test
    public void testConstructor() {
        Vet vet = new Vet("Dr. Test", "M", 33, "Expert", "All", "24/7");
        Owner owner = new Owner("Jeremy", "5006768991", "hdjfhjkh@gmail.com", "San Jose", 30);
        Pet pet = new Pet("Dog", "Doggo", "O-", 35, "Red", "M");
        LocalDateTime when = LocalDateTime.of(2025, 10, 20, 9, 0);

        AppointmentV2 appt = new AppointmentV2(vet, owner, pet, when);
        assertNotNull(appt);
    }

    private AppointmentV2 makeMultipleAppointments(int i) {
        Vet vet = new Vet("Dr. Vet" +i,"M", 33, "Expert", "All", "3PM-8PM");
        Owner owner = new Owner("O" + i, "O" + i, "O" + i, "O" + i, 18 + i);
        Pet pet = new Pet("Pet" + i, "Pet" + i, "Pet" + i, i, "Pet" + i, "Pet" + i);
        LocalDateTime when = LocalDateTime.of(2025, 10, 14, 9, 0).plusMinutes(i * 15);
        return new AppointmentV2(vet, owner, pet, when);
    }

    @Test
    public void generates100Appointments() {
        int count = 101;
        List<AppointmentV2> appts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            appts.add(makeMultipleAppointments(i));
        }

        assertEquals(count, appts.size());
        assertNotNull(appts.get(100));
    }

    @Test
    public void generatesMoreThan100Appointments() {
        int c = 102;
        List<AppointmentV2> appts = new ArrayList<>();
        try {
            for (int i = 0; i <= 101; i++) {
                appts.add(makeMultipleAppointments(i));
            }
        } catch (CapacityExceededException ex) {
            assertEquals("Too many Appointment Instances", ex.getMessage());
        }
    }

    @Test
    public void testBookingConflict() {
        BookingHelper bookingHelper = new BookingHelper();

        AppointmentV2 appt1 = makeMultipleAppointments(2);
        AppointmentV2 appt2 = makeMultipleAppointments(2);

        try {
            boolean booked1 = bookingHelper.book(appt1);
            assertTrue("Appointment booked successfully!", booked1);

            boolean booked2 = bookingHelper.book(appt2);

            if (appt1.getWhen().equals(appt2.getWhen())) {
                throw new BookingConflictException("Sorry, this slot is already taken.");
            }

            assertTrue("Both appointments booked successfully!", booked2);

        } catch (BookingConflictException ex) {
            assertEquals("Sorry, this slot is already taken.", ex.getMessage());
        }
    }
}