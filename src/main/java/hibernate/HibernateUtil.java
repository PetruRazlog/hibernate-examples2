package hibernate;


public class HibernateUtil {
    private static final HibernateApplication application = new HibernateApplication();

    public static void main(String[] args) {

//        application.initializeSession();
//        application.listUsers();
        application.userMenu();

    }
}