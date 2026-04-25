import java.util.ArrayList;
import java.util.Scanner;

public class Calendar {
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
                        Day newDay = new Day(index, times[0], times[1], true);
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

}
