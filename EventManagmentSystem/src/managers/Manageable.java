package managers;

//CRUD operations

public interface Manageable<T> {
    void create(T obj);
    T read(String id);
    void update(String id, T updatedObj);
    void delete(String id);
}
    