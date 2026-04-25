import java.util.UUID;

public class Slot {
    String uuid;
    String title;
    Day[] days = new Day[7];
    // 1-Sun --> 7-Sat
    String description;

    // Days will be an array of int, for the days of the week 1-7. 
    // The indexes correlated to the days will be true
    public Slot(String title, Day[] days, String description) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        for(Day day : days) {
            this.days[day.index - 1] = day;
        }
        this.description = description;
    }

    public void editTitle(String newTitle) {
        this.title = newTitle;
    }

    public void editDay(Day day) {
        this.days[day.index - 1] = day;
    }

    public void removeDay(int dayIndex) {
        this.days[dayIndex - 1] = null;
    }


    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public boolean checkConflict(Slot newSlot) {
        // Check if the days overlap from file storage
        return false;
    }
}