package ed.hluschenko.repository.contact;

import ed.hluschenko.dto.ContactDtoRequest;
import ed.hluschenko.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository{
@Autowired
    private SessionFactory sessionFactory;

public boolean create(ContactDtoRequest request){
    Session session = sessionFactory.getCurrentSession();
    String hql= " INSERT INTO Contact(firstName, lastName, phone)"+
            "VALUES(:firstName, :lastName, :phone)";
    MutationQuery query = session.createMutationQuery(hql);
    query.setParameter("firstName", request.firstName());
    query.setParameter("lastName", request.lastName());
    query.setParameter("phone", request.phone());
    return query.executeUpdate() > 0;
}

    public Optional<List<Contact>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Contact> list = session.createQuery("FROM Contact",
                    Contact.class).list();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, ContactDtoRequest request) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Contact SET firstName = :firstName, " +
                    "lastName = :lastName, phone = :phone " +
                    "WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("firstName", request.firstName());
            query.setParameter("lastName", request.lastName());
            query.setParameter("phone", request.phone());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.byId(Contact.class).load(id);
            session.remove(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        Optional<Contact> optional;
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Contact c WHERE c.id = :id";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("id", id);
            optional = query.uniqueResultOptional();
            return optional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
