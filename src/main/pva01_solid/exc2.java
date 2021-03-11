/**
 * SOLID principles: Excersize 2 - Violates SOLIDs fourth principle (Interface
 * Segregation) A class should never be forced to implement functionallity that
 * it does not require, for the sake of having less interfaces. Keep interfaces
 * minimal!
 */

// // Alte Version
// interface IPost {
// void createPost();
// }

// // Neue Version
// // New interface has two different functionallities; need to keep them
// seperated
// interface IPostNew {
// void createPost();

// void readPost();
// }

// New, better version
interface IPostCreate {
    void createPost();
}

interface IPostRead {
    void readPost();
}
