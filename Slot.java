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
        for(int i = 0; i<days.length; i++) {
            this.days[days[i].index - 1] = days[i];
        }
        this.description = description;
    }

    public void editTitle(String newTitle) {
        this.title = newTitle;
    }

    public void editDays(int[] days) {
        // clear days
        for(int i = 0; i < this.days.length; i++) {
            this.days[i] = false;
        }

        // set new days
        for(int i = 0; i < days.length; i++) {
            this.days[days[i] - 1] = true;
        }
    }

    public void editTime(String start, String end) {
        this.startTime = start;
        this.endTime = end;
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public boolean checkConflict(Slot newSlot) {
        // Check if the days overlap from file storage
        return false;
    }
}