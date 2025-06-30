package ed.hluschenko.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository <T,S>{
  boolean create(S request);
  Optional <List<T>> fetchAll();
  Optional <T> fetchById(Long id);
  boolean update(Long id, S request);
  boolean delete(Long id);

}
