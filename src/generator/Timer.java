package generator;

public class Timer {
    double startTime;
    double endTime;
    public void start() {
        this.startTime = System.nanoTime();
        System.out.println("Timer started!");
    }
    public void end() {
        this.endTime = System.nanoTime();
        double timeTaken = this.endTime - this.startTime;
        System.out.println(String.format("Timer finished! time taken: %s",this.formatTime(timeTaken)));
    }
    public String formatTime(double nanoTime) {
        String formattedTime;
        if(nanoTime < Math.pow(10,3)) {
            formattedTime = String.format("%.2f ns",nanoTime);
        }
        else if(nanoTime < Math.pow(10,6)) {
            formattedTime = String.format("%.2f us",nanoTime/Math.pow(10,3));
        }
        else if(nanoTime < Math.pow(10,9)) {
            formattedTime = String.format("%.2f ms",nanoTime/Math.pow(10,6));
        }
        else {
            formattedTime = String.format("%.2f s",nanoTime/Math.pow(10,9));
        }
        return formattedTime;
    }
}
