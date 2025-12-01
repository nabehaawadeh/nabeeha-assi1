package executor;
import connection.ConnectionPool;

public class JobExecutor {
    private final StrategyFactory factory;

    public JobExecutor(ConnectionPool pool) {
        this.factory = new StrategyFactory(pool);
    }

    public void run(String type) {
        JobStrategy strategy = factory.getStrategy(type);
        strategy.execute();
    }
}

