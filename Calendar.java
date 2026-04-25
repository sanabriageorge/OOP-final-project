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
    public static void editSlot(Slot slot, int option) {
        /*
        1 - title
        2 - days
        3 - time
        4 - description
        */
       Scanner sc = new Scanner(System.in);
        switch(option) {
            case 1:
                slot.editTitle(sc.nextLine());
                break;
            case 2:
                // String[] days = sc.nextLine().split(",");
                // boolean[] newDays    = new boolean[7];
                // for(int i = 0; i < days.length; i++) {
                //     int day = Integer.parseInt(days[i].trim());
                //     daysArr[day - 1] = true;
                // }

                // slot.editDays(daysArr);
                break;
            case 3:
                slot.editTitle(sc.nextLine());
                break;
            case 4:
                slot.editTitle(sc.nextLine());
                break;
        }
    }

    public static void deleteSlot(Slot slot) {
        // Remove slot from file storage
        slots.remove(slot);
    }

}
