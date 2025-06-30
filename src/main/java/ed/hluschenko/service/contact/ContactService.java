package ed.hluschenko.service.contact;

import ed.hluschenko.dto.ContactDtoRequest;
import ed.hluschenko.entity.Contact;
import ed.hluschenko.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact, ContactDtoRequest> {
    boolean create(ContactDtoRequest request);
    List<Contact> fetchAll();
    Contact fetchById(Long id);
    boolean update(Long id, ContactDtoRequest request);
    boolean delete(Long id);
}