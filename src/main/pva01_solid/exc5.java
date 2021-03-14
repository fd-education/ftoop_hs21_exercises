/**
 * SOLID principles: Exercise 5 - violates the SOLIDs fifth principle
 * (dependency inversion) If not absolutely necessary, we must avoid having
 * direct dependencies between concrete classes.
 */

// class Post {
// ErrorLogger errorLogger = new ErrorLogger();

// void createPost(Database db, String postMessage) {
// try {
// db.add(postMessage);
// }
// catch (Exception ex)
// {
// errorLogger.log(ex.ToString())
// }
// }
// }

public interface ILogger {
    void log(Exception e);
}

public class ErrorLogger implements ILogger {

    @Override
    public void log(Exception e) {
        // ...
    }
}

public class Post {
    private ILogger logger;

    public Post(ILogger logger) {
        this.logger = logger;
    }

    public void createPost(Database db, String postMessage) {
        try {
            db.add(postMessage);
        } catch (Exception e) {
            this.logger.log(e);
        }
    }
}
