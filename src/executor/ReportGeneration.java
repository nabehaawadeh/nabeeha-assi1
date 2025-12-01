package executor;

import connection.ConnectionPool;
import connection.IConnection;

public class ReportGeneration implements JobStrategy {
    private final ConnectionPool pool;

    public ReportGeneration(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void execute() {
        try {
            IConnection conn = pool.acquire();
            System.out.println("[ReportGenerationJob] Generating Report...");
            conn.executeQuery("SELECT * FROM report_table");
            conn.executeQuery("INSERT INTO reports VALUES ('created')");
            pool.release(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
