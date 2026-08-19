import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Background task that periodically persists all system data to disk.
 * Runs as a daemon thread so it does not block application exit.
 */
public class AutoSaveTask implements Runnable {
    private final StudentManagementSystem system;
    private final int intervalSeconds;
    private volatile boolean running = true;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public AutoSaveTask(StudentManagementSystem system, int intervalSeconds) {
        this.system = system;
        this.intervalSeconds = intervalSeconds;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(intervalSeconds * 3000L);
                if (running) {
                    system.saveData();
                    String time = LocalTime.now().format(TIME_FORMATTER);
                    System.out.printf("\n[Auto-Save @ %s] System data successfully backed up to disk.%nChoose an option: ", time);
                }
            } catch (InterruptedException e) {
                // Thread interrupted on shutdown
                break;
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
