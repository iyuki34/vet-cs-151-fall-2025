package Clinic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class BookingUtils {
    private BookingUtils() {} // no instantiation

    private static DayOfWeek parseDayToken(String token) {
        if (token == null) return null;
        token = token.trim().toLowerCase();
        switch (token) {
            case "mon", "monday" -> { return DayOfWeek.MONDAY; }
            case "tue", "tues", "tuesday" -> { return DayOfWeek.TUESDAY; }
            case "wed", "wednesday" -> { return DayOfWeek.WEDNESDAY; }
            case "thu", "thurs", "thursday" -> { return DayOfWeek.THURSDAY; }
            case "fri", "friday" -> { return DayOfWeek.FRIDAY; }
            case "sat", "saturday" -> { return DayOfWeek.SATURDAY; }
            case "sun", "sunday" -> { return DayOfWeek.SUNDAY; }
            default -> { return null; }
        }
    }

    // "Monday, Tuesday, Wednesday, Thursday"
    public static Set<DayOfWeek> parseAvailableDays(String daysStr) {
        Set<DayOfWeek> set = new HashSet<>();
        if (daysStr == null || daysStr.isBlank()) return set;
        String[] parts = daysStr.split(",");
        for (String p : parts) {
            DayOfWeek d = parseDayToken(p); // same parseDayToken as above
            if (d != null) set.add(d);
        }
        return set;
    }

   // Simplified parser for inputs like "8AM" or "8PM"
    private static LocalTime parseClock(String s) {
        if (s == null || s.isBlank()) return LocalTime.MIDNIGHT;

        s = s.trim().toUpperCase(Locale.ROOT);
        boolean isPM = s.endsWith("PM");
        s = s.replaceAll("(AM|PM)", "");

        int hour = Integer.parseInt(s);
        if (hour == 12) hour = isPM ? 12 : 0;
        else if (isPM) hour += 12;

        return LocalTime.of(hour, 0);
    }
    // parse "8AM-3PM" to LocalTime[2]
    public static LocalTime[] parseWorkingHours(String s) {
    if (s == null || !s.contains("-")) {
            return new LocalTime[] { LocalTime.of(9, 0), LocalTime.of(17, 0) };
        }
        String[] parts = s.split("-");
        return new LocalTime[] { parseClock(parts[0].trim()), parseClock(parts[1].trim()) };
    }

    // return next N dates (including today) that match any DayOfWeek in 'available'
    public static List<LocalDate> getNextMatchingDates(Set<DayOfWeek> available, int limit) {
        List<LocalDate> out = new ArrayList<>();
        LocalDate d = LocalDate.now();
        int tries = 0;
        while (out.size() < limit && tries < 60) { // safety cap two months
            if (available.contains(d.getDayOfWeek())) out.add(d);
            d = d.plusDays(1);
            tries++;
        }
        return out;
    }

    // generate slots from start (inclusive) to end (exclusive)
    public static List<LocalTime> generateSlots(LocalTime start, LocalTime end, int slotMinutes) {
        List<LocalTime> slots = new ArrayList<>();
        if (start == null || end == null || !start.isBefore(end)) return slots;
        LocalTime t = start;
        while (!t.isAfter(end.minusMinutes(slotMinutes))) {
            slots.add(t);
            t = t.plusMinutes(slotMinutes);
        }
        return slots;
    }
    // convenience formatter (optional)
    public static String fmtDate(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE yyyy-MM-dd", Locale.ENGLISH);
        return d.format(formatter);
    }

}
