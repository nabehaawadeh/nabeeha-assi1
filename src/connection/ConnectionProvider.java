package connection;

public class ConnectionProvider implements IConnectionProvider {
    private static int counter = 0;

    @Override
    public IConnection create() {
        return new Connection("Conn-" + (++counter));
    }
}
