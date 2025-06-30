package ed.hluschenko.service.contact;

import ed.hluschenko.dto.ContactDtoRequest;
import ed.hluschenko.entity.Contact;
import ed.hluschenko.repository.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository repository;


    public boolean create(ContactDtoRequest request) {
        return repository.create(request);
    }

    @Override
    public List<Contact> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Contact fetchById(Long id) {
        return repository.fetchById(id)
                .orElse(null);
    }

    @Override
    public boolean update(Long id, ContactDtoRequest request) {
        return repository.update(id, request);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Contact> optional = repository.fetchById(id);
        if (optional.isPresent())
            return repository.delete(id);
        else return false;
    }
}

