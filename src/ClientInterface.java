import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
    public void printAdverts(ArrayList<Advertisement> adList) throws RemoteException;
    public void print(String msg) throws RemoteException;
}
