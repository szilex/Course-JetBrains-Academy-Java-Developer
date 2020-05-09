class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (hours < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(hours);
        stringBuilder.append(":");

        if (minutes < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minutes);
        stringBuilder.append(":");

        if (seconds < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(seconds);

        return stringBuilder.toString();
    }
}