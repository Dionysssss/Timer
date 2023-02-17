import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class timer {
    private DateFormat simple;
    private long startTime = 0;
    private long pauseTime = 0;
    private long timeInterval = 0;
    private enum states {WAIT,RUN};
    private states state;
    public static void main(String args[]) throws InterruptedException {

        timer test = new timer();
        test.Timer_start();
        Thread.sleep(1000);

        System.out.println(test);
        test.Timer_pause();
        Thread.sleep(1000);
        test.Timer_resume();
        Thread.sleep(1000);
        System.out.println(test);



    }


    public timer(){
        this.simple = new SimpleDateFormat("H:mm:ss:SSS" );
        this.state = states.WAIT;
        startTime = 0;
        pauseTime = 0;
        timeInterval = 0;
    }

    public timer(String pattern){
        this.simple = new SimpleDateFormat( pattern); //String pattern="H:mm:ss:SSS"
        this.state = states.WAIT;
          startTime = 0;
          pauseTime = 0;
          timeInterval = 0;
    }

    public String Timer_start(){
        startTime = getPresentInMill();
        this.state = states.RUN;
        pauseTime = 0;
        timeInterval = 0;
        return this.toString();
    }

    public String Timer_pause(){
        this.pauseTime = getPresentInMill();
        this.state = states.WAIT;
        return this.toString();
    }

    public String Timer_resume(){
        if(this.state == states.WAIT){
            this.timeInterval += getPresentInMill()-pauseTime;
        }
        return toString();

    }

    public long timeFromStart()
    {
        return  getPresentInMill() - startTime - timeInterval;
    }


    public String toString(){
        long dur = getPresentInMill() - startTime - timeInterval;
        return printTime(dur);
    }

    public static long getPresentInMill(){
        Calendar cal = Calendar.getInstance();
        //System.out.println("calendar = "+cal.getTimeInMillis());
        return cal.getTimeInMillis();
    }

    public void setFormat(String pattern){
        this.simple = new SimpleDateFormat(pattern );
    }


    public String printTime(long milliSec){

        simple.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(milliSec);
        // Formatting Date according to th
        // given format
        return simple.format(result);
//             System.out.println(simple.format(result));

    }

}