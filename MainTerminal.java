import java.util.Scanner;
public class MainTerminal {
  //Command Line Menu
  public static void displaySlots() {
    if (Calendar.slots.size() == 0) {
      System.out.println("No slots available.");
      return;
  }

  for (int i = 0; i < Calendar.slots.size(); i++) {
      Slot s = Calendar.slots.get(i);
      System.out.println(i + ": " + s.title + " - " + s.description);
  }
}
  public static void main(String[] args) {
      Calendar.loadFromFile();
      Scanner sc = new Scanner(System.in);

      while (true) {
          System.out.println("\n1. Add Slot");
          System.out.println("2. View Calendar");
          System.out.println("3. Exit");

          System.out.print("Choose option: ");

          int choice = Integer.parseInt(sc.nextLine());

          if (choice == 1) {
              System.out.print("Title: ");
              String title = sc.nextLine();

              System.out.print("Day 1-7: ");
              int dayIndex = Integer.parseInt(sc.nextLine());

              System.out.print("Start time, example 8:00: ");
              String start = sc.nextLine();

              System.out.print("End time, example 10:00: ");
              String end = sc.nextLine();

              System.out.print("Description: ");
              String description = sc.nextLine();

              Day day = new Day(dayIndex, start, end);
              Day[] days = { day };

              Slot slot = new Slot(title, days, description);
              Calendar.saveSlot(slot);
          }
          else if (choice == 2) {
              Calendar.printCalendar();

              System.out.println("1. Edit Slot");
              System.out.println("2. Delete Slot");
              System.out.println("3. Back");
              System.out.print("Choose option: ");
              int calChoice = Integer.parseInt(sc.nextLine());

              if (calChoice == 1) {
                  displaySlots();
                  System.out.print("Select slot index: ");
                  int index = Integer.parseInt(sc.nextLine());
                  Slot slot = Calendar.slots.get(index);

                  System.out.println("1. Edit Title");
                  System.out.println("2. Edit Day");
                  System.out.println("3. Edit Start Time");
                  System.out.println("4. Edit End Time");
                  System.out.println("5. Edit Description");
                  System.out.println("6. Cancel");
                  System.out.print("Choose Option: ");
                  int option = Integer.parseInt(sc.nextLine());
                  if (option != 6) {
                      Calendar.editSlot(slot, option);
                  }
              } else if (calChoice == 2) {
                  displaySlots();
                  System.out.print("Select slot index to delete: ");
                  int index = Integer.parseInt(sc.nextLine());
                  Slot slot = Calendar.slots.get(index);
                  Calendar.deleteSlot(slot);
                  System.out.println("Slot deleted.");
              }
          }
          else if (choice == 3) {
            System.out.println("Goodbye!");
            break;
        }
          else {
              System.out.println("Invalid option.");
          }
      }

      sc.close();
  }
}

/*
javac MainTerminal.java
java MainTerminal

cmd shell
javac MainTerminal.java && java MainTerminal
*/