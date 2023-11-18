import java.util.LinkedList;

public class checkCredentials {
    public static boolean check() throws Exception {
        LinkedList<String> data = new database().queries("user_accounts");

        return true;
    }
}
