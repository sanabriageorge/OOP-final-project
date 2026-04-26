public class MainTerminal {
  // Submit a slot through
  // int(start-end), int(start-end) ...
  // we parse and turn this into a day for a slot

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
javac MainTerminal.java
java MainTerminal

cmd shell
javac MainTerminal.java && java MainTerminal
*/