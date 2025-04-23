package managers;

// makes classes implement functions that are used in the IDManager class
// so they could have IDs

public interface Identifiable {
    String getIdPrefix();
    void generateID();

}
