package managers;

public interface Manageable<T> {
    void create(T obj);
    T read(int ID);
    void update(int ID, T updatedObj);
    void delete(int ID);
}
