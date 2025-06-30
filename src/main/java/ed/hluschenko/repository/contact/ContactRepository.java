package ed.hluschenko.repository.contact;

import ed.hluschenko.dto.ContactDtoRequest;
import ed.hluschenko.entity.Contact;
import ed.hluschenko.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact, ContactDtoRequest> {
  boolean create(ContactDtoRequest request);
  Optional<List<Contact>> fetchAll();
  Optional<Contact> fetchById(Long id);
  boolean update(Long id, ContactDtoRequest request);
  boolean delete(Long id);
}
