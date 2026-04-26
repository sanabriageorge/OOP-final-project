public class index {
    public static void main(String[] args) {
        // Example usage    
        Day day1 = new Day(1, "08:00", "10:00");
        Day day2 = new Day(3, "12:00", "14:00");
        Day[] temp = {day1, day2};
        
        Slot slot1 = new Slot("Meeting", temp, "Team meeting");
        Calendar.slots.add(slot1);

        Calendar.printCalendar();
    }
}

/*
javac index.java
java index
*/