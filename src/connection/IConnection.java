
package connection;

public interface IConnection {
    String getId();
    void executeQuery(String q);
}