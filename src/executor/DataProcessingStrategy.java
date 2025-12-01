package executor;

import connection.ConnectionPool;
import connection.IConnection;

public class DataProcessingStrategy implements JobStrategy {
    private final ConnectionPool pool;

    public DataProcessingStrategy(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void execute() {
        try {
            IConnection conn = pool.acquire();
            System.out.println("[DataProcessingJob] Processing Data...");
            conn.executeQuery("SELECT * FROM data_table");
            conn.executeQuery("INSERT INTO processed_data VALUES ('done')");
            pool.release(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
