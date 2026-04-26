public class Day {
    int index;
    String day;
    float startTime;
    float endTime;

    public Day(int index, String startTime, String endTime) {
        this.index = index;
        switch (index) {
            case 1 -> this.day = "Sunday";
            case 2 -> this.day = "Monday";
            case 3 -> this.day = "Tuesday";
            case 4 -> this.day = "Wednesday";
            case 5 -> this.day = "Thursday";
            case 6 -> this.day = "Friday";
            case 7 -> this.day = "Saturday";
        }

        if(startTime.contains(":")) {
            String[] time = startTime.split(":");
            this.startTime = Integer.parseInt(time[0]);
            if(Integer.parseInt(time[1]) == 30) {
                this.startTime += 0.5;
            }
        }
        else {
            this.startTime = Integer.parseInt(startTime) * 100;
        }

        if(endTime.contains(":")) {
            String[] time = endTime.split(":");
            this.endTime = Integer.parseInt(time[0]);
            if(Integer.parseInt(time[1]) == 30) {
                this.endTime += 0.5;
            }
        }
        else {
            this.endTime = Integer.parseInt(endTime) * 100;
        }
    }
}