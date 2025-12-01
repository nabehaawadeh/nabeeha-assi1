package connection;

public class Connection implements IConnection {
    private final String id;

    public Connection(String id) {
        this.id = id;
    }

    @Override
    public void executeQuery(String q) {
        System.out.printf("[Connection %s] Executing: %s%n", id, q);
    }

    @Override
    public String getId() {
        return id;
    }
}
