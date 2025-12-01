package connection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final int MAX = 10;
    private final BlockingQueue<IConnection> pool;
    private final IConnectionProvider provider;
    private int created = 0;

    public ConnectionPool(IConnectionProvider provider) {
        this.provider = provider;
        this.pool = new ArrayBlockingQueue<>(MAX);
    }

    public synchronized IConnection acquire() throws InterruptedException {
        if (created < MAX) {
            IConnection conn = provider.create();
            pool.put(conn);
            created++;
        }
        IConnection conn = pool.take();
        return conn;
    }

    public synchronized void release(IConnection conn) throws InterruptedException {
        pool.put(conn);
    }
}
