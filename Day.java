public class Day {
    int index;
    String day;
    String startTime;
    String endTime;

    public Day(int index, String startTime, String endTime) {
        switch (index) {
            case 1 -> this.day = "Sunday";
            case 2 -> this.day = "Monday";
            case 3 -> this.day = "Tuesday";
            case 4 -> this.day = "Wednesday";
            case 5 -> this.day = "Thursday";
            case 6 -> this.day = "Friday";
            case 7 -> this.day = "Saturday";
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }
}