package hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

@SuppressWarnings("unchecked")
public class HibernateApplication {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;
    private static final Scanner scanner = new Scanner(System.in);
    private ConsoleColors cc;
    private static final Logger logger = Logger.getLogger(HibernateUtil.class);
    private static Integer userID;

    public void initializeSession(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
        logger.info(ConsoleColors.YELLOW +"Session initialized"+ ConsoleColors.RESET +"\n");
    }

    public void closeSession() {
        if (sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    public void userMenu(){
        initializeSession();
        while(true){
            System.out.println("\t\t\t"+ ConsoleColors.GREEN_BACKGROUND_BRIGHT +"Menu list:"
                    + ConsoleColors.RESET +"\r\n0.Exit 1.Add 2.List 3.Update 4.Delete");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    addUser();
                    break;
                case 2:
                    listUsers();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                default:
                    System.out.println(ConsoleColors.RED +"Invalid input!!!"+ ConsoleColors.RESET);
            }
        }
    }

    public User getInput() {
        System.out.println("Insert user: ");
        String line = scanner.nextLine();
        String[] data = line.split(" ");
        String fname = data[0];
        String lname = data[1];
        String bdate = data[2];
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setBirthDate(bdate);

        return user;
    }

    public void addUser(){
        session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();


//            User user = new User(fname,lname,bdate);
            User user = getInput();
            userID = (Integer) session.save(user);
            transaction.commit();
            scanner.close();
            logger.info(ConsoleColors.BLUE +"New user added in database"+ ConsoleColors.RESET +"\n");
        } catch (ArrayIndexOutOfBoundsException | HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back!!!");
            }
        } finally {
            session.close();
        }
    }

    public void listUsers(){
        session = sessionFactory.openSession();
        transaction = null;

        try {
            transaction = session.beginTransaction();
            var users = session.createQuery("FROM User").list();
            users.forEach(System.out::println);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back!!!");
            }
        } finally {
            session.close();
        }
    }

    public void updateUser(){
        session = sessionFactory.openSession();
        transaction = null;
        try {
            transaction = session.beginTransaction();
            System.out.print("Choose userID to update user: ");
            userID = scanner.nextInt();
            System.out.print("Update data: ");
            String bdate = scanner.nextLine();
            User user = session.get(User.class,userID);
            user.setBirthDate(bdate);
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back!!!");
            }
        } finally {
            scanner.close();
            session.close();
        }
    }

    public void deleteUser() {
        session = sessionFactory.openSession();
        transaction = null;
        try {
            System.out.println("Choose userID to update user: ");
            userID = scanner.nextInt();
            transaction = session.beginTransaction();
            User user = session.get(User.class,userID);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("Transaction rolled back!!!");
            }
        } finally {
            session.close();
        }
    }


}
