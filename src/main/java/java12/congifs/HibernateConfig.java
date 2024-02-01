package java12.congifs;

import jakarta.persistence.EntityManagerFactory;
import java12.entities.Address;
import java12.entities.Company;
import java12.entities.Programmer;
import java12.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;



public class HibernateConfig {
    public static SessionFactory getSession() {
        try {
            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/database_name");
            properties.put("hibernate.connection.username", "postgres");
            properties.put("hibernate.connection.password", "nurlan21");
            properties.put("hibernate.hbm2ddl.auto", "create");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.show_sql", "true");
            Configuration configuration = new Configuration();
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Programmer.class);
            configuration.addAnnotatedClass(Project.class);
            return configuration.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return getSession().unwrap(EntityManagerFactory.class);
    }
}

