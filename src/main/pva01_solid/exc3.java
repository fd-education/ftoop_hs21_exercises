/**
 * SOLID principles: Excersize 3 - violates SOLIDs first principle (single
 * responsibility) The User Class creates posts and writes errors to files Two
 * different functionalities must be realised in two different classes
 */

// class User {
// void createPost(Database db, String postMessage) {
// try {
// db.add(postMessage);
// }
// catch (Exception ex) {
// db.logError("An error occured: ", ex.toString());
// file.writeAllText("\LocalErrors.txt", ex.toString());
// }
// }
// }

// SOLID Version
class Post {
    private Logger logger = new Logger();

    public void createPost(Database db, String postMessage) {
        try {
            db.add(postMessage);
        } catch (Exception e) {
            logger.logError(e);
        }
    }
}

class Logger {
    public void logError(e){
        errorToDB(e);
        errorToLogFile(e);
    }

    private void errorToDB(e){
        db.logError("An error occured: ", e.toString());
    }

    private void errorToLogFile(e){
        file.writeAllText("\LocalErrors.txt", e.toString());
    }
}
