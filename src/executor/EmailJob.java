package executor;

import connection.ConnectionPool;
import connection.IConnection;

public class EmailJob implements JobStrategy {
    private final ConnectionPool pool;

    public EmailJob(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void execute() {
        try {
            IConnection conn = pool.acquire();
            System.out.println("[EmailJobStrategy] Sending Email...");
            conn.executeQuery("INSERT INTO email_log VALUES ('sent')");
            pool.release(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
