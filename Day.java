public class Day {
    int index;
    String day;
    boolean selected;

    public Day(int index, boolean selected) {
        switch (index) {
            case 1:
                this.day = "Sunday";
                break;
            case 2:
                this.day = "Monday";
                break;
            case 3:
                this.day = "Tuesday";
                break;
            case 4:
                this.day = "Wednesday";
                break;
            case 5:
                this.day = "Thursday";
                break;
            case 6:
                this.day = "Friday";
                break;
            case 7:
                this.day = "Saturday";
                break;
        }
        this.selected = selected;
    }
}