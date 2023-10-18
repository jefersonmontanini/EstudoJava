package MyProject.domain.repository;

import MyProject.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClientsJPA {

    //    private static String INSERT = "insert into Client (name) VALUES (?)";
    //    private static String GetByName = "SELECT * FROM Client WHERE name = ? ORDER BY id DESC";                     //uso em JDBC TEMPLATE
    //    private static String ListAll = "SELECT * FROM Client";
    //    private  static String Update = "UPDATE Client SET name = ? WHERE id = ?";
    //    private static String Delete = "DELETE FROM Client WHERE id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client save(Client client) {
        entityManager.persist(client);
        return getByName(client.getName());
    }

    @Transactional
    public Client update(Client client) {
        entityManager.merge(client);
        return client;
    }

    @Transactional
    public void delete(Client client) {
        if (!entityManager.contains(client)) {
            client = entityManager.merge(client);
        }
        entityManager.remove(client);
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        delete(client);
    }

    @Transactional(readOnly = true)
    public Client getByName(String name) {
        String jpql = "SELECT c FROM Client c WHERE c.name = :name";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", name);
        return query.getResultList().get(0);
    }

    @Transactional
    public List<Client> ListAll() {
        TypedQuery<Client> query = entityManager.createQuery("from Client", Client.class);
        return query.getResultList();
    }


}
    //USO DE JDBC
//
//    public Client saveJDBC( Client client) {
//        jdbc.update( INSERT, new Object[]{client.getName()} );
//        return client;
//    }

//    public List<Client> list() {
//        return jdbc.query(SELECT_ALL, new RowMapper<Client>() {
//            @Override
//            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Integer id = rs.getInt("id");
//                String name = rs.getString("name");
//
//                return new Client(id, name);
//            }
//        });
//    }
//
//    public Client update(Client client) {
//        jdbc.update(UPDATE, new Object[]{
//                client.getName(), client.getId()});
//        return client;
//    }
//
//    public void delete(Client client) {
//        delete(client.getId());
//    }

//    public void delete(Integer id) {
//        jdbc.update(DELETE, new Object[]{id});
//    }

