package managers;

public interface Manageable<T> {
    void create(T obj);
    T read(String ID);
    void update(String ID, T updatedObj);
    void delete(String ID);
}
