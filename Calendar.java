import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Calendar {
    private static final String FILE_NAME= "calendar.csv";

    public static void saveToFile(){
        //Saves slot to a file 
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            for (Slot slot : slots) {
                for (Day day : slot.days) {
                    if (day != null) {
                        writer.write(
                            slot.uuid + "," +
                            slot.title + "," +
                            day.index + "," +
                            formatTimeForFile(day.startTime) + "," +
                            formatTimeForFile(day.endTime) + "," +
                            slot.description + "\n"
                        );
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error ");
        }
    }
    //Loads slot info from File storage
    public static void loadFromFile() {
        slots.clear();
        try {
            Scanner fileReader = new Scanner(new File(FILE_NAME));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
    
                String title= parts[1];
                int dayIndex= Integer.parseInt(parts[2]);
                String startTime = parts[3];
                String endTime = parts[4];
                String description = parts[5];
    
                Day day = new Day(dayIndex, startTime, endTime);
                Day[] days = new Day[7];
                days[dayIndex - 1] = day;

                Slot slot = new Slot(title, days, description);
                slots.add(slot);
            }
            fileReader.close();
    
        } catch (FileNotFoundException e) {
            System.out.println("No saved calendar file");
        }
    }

    private static String formatTimeForFile(float time) {
        //Format time to be put in file
        int hour= (int) time;
    
        if (time % 1 == 0) {
            return hour + ":00";
        } else {
            return hour + ":30";
        }
    }
    
    private static final String BAR = "-".repeat(121) + "\n";
    public static ArrayList<Slot> slots = new ArrayList<>();

    public static ArrayList<Slot> retrieveSlots() {
        // Array of slots read from file storage
        // typically after new update/edit/delete
        return slots;
    }
    public static boolean saveSlot(Slot slot){
        for(Slot existingSlot : slots){
            if(existingSlot.checkConflict(slot)){
                System.out.println("Conflict");
                return false;
            }
        }
        slots.add(slot);
        saveToFile();
        System.out.println("Slot added");
        return true;
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
        1 - change title 
        2 - chnage the day 
        3 - change start time
        4 - change end time 
        5 - change description
        */
       Scanner sc = new Scanner(System.in);
        switch(option) {
            case 1:
                System.out.println("New title:");
                slot.editTitle(sc.nextLine());
                break;
            
            case 2:
                int currentIndex = slot.getCurrentDayIndex();

                if (currentIndex == -1) {
                System.out.println("No day exists for this slot.");
                    break;
                }

                Day oldDay = slot.days[currentIndex - 1];
                System.out.print("New day index (1-7): ");
                int newIndex = Integer.parseInt(sc.nextLine());

                Day newDay = new Day(newIndex, formatTimeForFile(oldDay.startTime), formatTimeForFile(oldDay.endTime));
                slot.removeDay(currentIndex);
                slot.editDay(newDay);
                break;

            case 3:
                int startIndex = slot.getCurrentDayIndex();

                if (startIndex == -1) {
                    System.out.println("No day exists for this slot.");
                    break;
                }

                Day startDay = slot.days[startIndex - 1];

                System.out.print("New start time: ");
                String newStart = sc.nextLine();

                Day updatedStartDay = new Day(startIndex, newStart, formatTimeForFile(startDay.endTime));
                slot.editDay(updatedStartDay);
                break;

            case 4:
                int endIndex = slot.getCurrentDayIndex();

                if (endIndex == -1) {
                    System.out.println("No day exists for this slot.");
                    break;
                }

                Day endDay = slot.days[endIndex - 1];

                System.out.print("New end time: ");
                String newEnd = sc.nextLine();

                Day updatedEndDay = new Day(endIndex, formatTimeForFile(endDay.startTime), newEnd );
                slot.editDay(updatedEndDay);
                break;

            case 5:
                System.out.println("New description");
                slot.editDescription(sc.nextLine());
                break;
            
            default:
                System.out.println("invalid Option");
            }
            saveToFile();
        }

    public static void deleteSlot(Slot slot) {
        // Remove slot from file storage
        slots.remove(slot);
        saveToFile();
    }

    // simplfiying the print calendar to print from 8am-8pm
    public static void printCalendar() {
        String calendar = "Time\t|\tSun\t |\tMon\t |\tTue\t |\tWed\t |\tThu\t |\tFri\t |\tSat\t |\n";
        for(float hour = 8; hour <= 20; hour+=0.5) {
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
            for(Slot slot : slots) {
                Day d = slot.days[day - 1];
                if(d != null && slotTaken(d, hour)) {
                    timeSlot += String.format("%-10s\t |", slot.title);
                }
                else {
                    timeSlot += "\t \t |";
                }
            }
        }
        return timeSlot + "\n";
    }

    private static float formatTime(String time) {
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

    private static boolean slotTaken(Day day, float time) {
        return day.startTime <= time && day.endTime > time;
    }
}

