import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AdvertService extends Remote {
    void postAdvert(Advertisement ad) throws RemoteException;
    void removeAdvert(int index) throws RemoteException;
    void addClient(ClientInterface client) throws RemoteException;
    ArrayList<Advertisement> getAdverts() throws RemoteException;
    void sendToClients() throws RemoteException;
}
