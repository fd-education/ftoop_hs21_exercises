/**
 * SOLID principles: Excersize 4 - Violates SOLIDs second principle (open/close)
 * A class must be open for extension, but closed for modification. In other
 * words extension must be possible, without the need of modifying an existing
 * class.
 */

class Post {
    void createPost(Database db, String postMessage) {
        if (postMessage.startsWith("#")) {
            db.addAsTag(postMessage);
        } else {
            db.add(postMessage);
        }
    }
}

// Inheritance allows to use a type-specific choice of methods
// If a Post is a TagPost, the createPost method of the class TagPost will be
// used
class Post {
    void createPost(Database db, String postMessage) {
        db.add(postMessage);
    }
}

class TagPost extends Post {
    @Override
    void createPost(Database db, String postMessage) {
        db.addAsTag(postMessage);
    }
}
