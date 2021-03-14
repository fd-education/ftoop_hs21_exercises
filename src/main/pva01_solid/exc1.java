/**
 * SOLID principles: Exercise 1 - Violates SOLIDs third principle (Liskov)
 * superclasses must be replaceable by any of their subclasses Subclasses must
 * therefore implement all of their superclasse's methods MentionPost does not
 * implement createPost
 */

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

// // Extends Post but does not implement the super method createPost
// class MentionPost extends Post {
// void createMentionPost(Database db, string postMessage) {
// String user = postMessage.parseUser();

// db.notifyUser(user);
// db.overrideExistingMention(user, postMessage);
// base.createPost(db, postMessage);
// }
// }

// Better version:
class MentionPost extends Post {

    // createMentionPost must be renamed createPost
    @Override
    void createPost(Database db, string postMessage) {
        String user = postMessage.parseUser();

        db.notifyUser(user);
        db.overrideExistingMention(user, postMessage);
        base.createPost(db, postMessage); // super.createPost(db, postMessage);???
    }
}

class PostHandler {
    Database database = new Database();

    void handleNewPosts() {
        List<String> newPosts = database.getUnhandledPostsMessages();

        foreach (String postMessage : newPosts) {
            Post post;

            if (postMessage.startsWith("#")) {
                post = new TagPost();
            }
            else if (postMessage.startsWith("@")) {
                post = new MentionPost();
            }
            else {
                post = new Post();
            }

            post.createPost(database, postMessage);
        }
    }
}
