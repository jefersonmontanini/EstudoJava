package MyProject.domain.repository;

import MyProject.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients {

    private static String INSERT = "INSERT INTO Client (name) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM Client";
    private static String UPDATE = "UPDATE Client set name = ? WHERE id = ?";
    private static String DELETE = "DELETE FROM Client WHERE id = ? ";


    @Autowired
    private JdbcTemplate jdbc;

    public Client save( Client client) {
        jdbc.update( INSERT, new Object[]{client.getName()} );
        return client;
    }

    public List<Client> list() {
        return jdbc.query(SELECT_ALL, new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");

                return new Client(id, name);
            }
        });
    }

    public Client update(Client client) {
        jdbc.update(UPDATE, new Object[]{
                client.getName(), client.getId()});
        return client;
    }

    public void delete(Client client) {
        delete(client.getId());
    }

    public void delete(Integer id) {
        jdbc.update(DELETE, new Object[]{id});
    }
}
