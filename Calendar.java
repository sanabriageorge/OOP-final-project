import java.util.ArrayList;
import java.util.Scanner;

public class Calendar {
    private static final String BAR = "-".repeat(121) + "\n";
    public static ArrayList<Slot> slots = new ArrayList<>();

    public static ArrayList<Slot> retrieveSlots() {
        // Array of slots read from file storage
        // typically after new update/edit/delete
        return slots;
    }

    // User Flow:
    // User browse calendar/all slots
    // User selects slot 
    // User can edit or delete slot
    // If edit, User can edit title, days, time, description
    // If days, we ask user to input int(start-end) or int(delete)
    // Any untouched will remain the same.
    public static void editSlot(Slot slot, int option) {
        /*
        1 - title
        2 - days
        3 - description
        */
       Scanner sc = new Scanner(System.in);
        switch(option) {
            case 1 -> slot.editTitle(sc.nextLine());
            case 2 -> {
                String input = sc.nextLine();
                String[] daysToEdit = input.split(",");
                
                for(String day : daysToEdit) {
                    if(day.contains("delete")) {
                        int dayToDelete = Integer.parseInt(day.trim().substring(0,1));
                        slot.removeDay(dayToDelete);
                    }
                    else {
                        String time = day.trim().substring(day.indexOf("("), day.indexOf(")"));
                        String[] times = time.split("-");
                        int index = Integer.parseInt(day.trim().substring(0,1));
                        Day newDay = new Day(index, times[0], times[1]);
                        slot.editDay(newDay);
                    }
                }
            }
            case 3 -> slot.editDescription(sc.nextLine());
        }
    }

    public static void deleteSlot(Slot slot) {
        // Remove slot from file storage
        slots.remove(slot);
    }

    // simplfiying the print calendar to print from 8am-8pm
    public static void printCalendar() {
        String calendar = "Time\t| \tSun\t| \tMon\t| \tTue\t| \tWed\t| \tThu\t| \tFri\t| \tSat\t|\n";
        for(float hour = 8; hour <= 12; hour+=0.5) {
            calendar += BAR;
            calendar += formatTime(hour);
        }

        for(float hour = 1; hour <= 8; hour+=0.5) {
            calendar += BAR;
            calendar += formatTime(hour);
        }
        System.out.println(calendar);
    }

    private static String formatTime(float hour) {
        String timeSlot = "";
        if(hour % 1 == 0) {
            timeSlot = (int)hour + ":00";
        }
        else {
            timeSlot = (int)hour + ":30";
        }
        timeSlot += "\t|";

        for(int day = 1; day <= 7; day++) {
            // finish here to check slots if slot here is taken
            if(false) {
                timeSlot += "\ttitle\t|";
            }
             else {
                timeSlot += "\t \t|";
            }
        }
        return timeSlot + "\n";
    }

    private static float convertToFloat(String time) {
        if(time.contains(":")) {
            String[] splitTime = time.split(":");
            float hour = Integer.parseInt(splitTime[0]);
            if(Integer.parseInt(splitTime[1]) == 30) {
                hour += 0.5;
            }
            return hour;
        }
        else {
            return Integer.parseInt(time);
        }
    }

    private static String slotTaken(Day day, float time) {
        if(day.startTime <= time && day.endTime > time) {
            return day.day;
        }
        return "";
    }
}

