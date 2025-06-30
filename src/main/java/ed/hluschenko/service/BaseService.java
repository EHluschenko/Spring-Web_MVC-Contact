package ed.hluschenko.service;


import java.util.List;

public interface BaseService<T,S>{
    boolean create(S request);
    List<T> fetchAll();
    T fetchById(Long id);
    boolean update(Long id, S request);
    boolean delete(Long id);
}
