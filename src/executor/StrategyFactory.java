package executor;

import connection.ConnectionPool;

public class StrategyFactory {
    private final ConnectionPool pool;

    public StrategyFactory(ConnectionPool pool) {
        this.pool = pool;
    }

    public JobStrategy getStrategy(String type) {
        return switch (type) {
            case "EMAIL" -> new EmailJob(pool);
            case "DATA" -> new DataProcessingStrategy(pool);
            case "REPORT" -> new ReportGeneration(pool);
            default -> throw new IllegalArgumentException("Unknown job type");
        };
    }
}
