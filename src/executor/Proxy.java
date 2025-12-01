package executor;

import connection.ConnectionPool;
import connection.IConnection;
import user.User;
import executor.JobExecutor;

public class Proxy {
    private final JobExecutor executor;
    private final ConnectionPool pool;
    private final User user;

    public Proxy(JobExecutor executor, ConnectionPool pool, User user) {
        this.executor = executor;
        this.pool = pool;
        this.user = user;
    }

    public void executeJob(String type) {
        if (!user.hasPermission(type)) {
            System.out.println("[Proxy] Permission denied for job: " + type);
            return;
        }

        System.out.println("[Proxy] Job started: " + type);
        long start = System.currentTimeMillis();

        try {
            IConnection conn = pool.acquire();
            executor.run(type);
            pool.release(conn);
            long end = System.currentTimeMillis();
            System.out.println("[Proxy] Job finished, time = " + (end - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // internal unprotected execution
    public void executeJobInternal(String type) {
        executor.run(type);
    }
}
