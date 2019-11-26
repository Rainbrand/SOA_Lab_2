import java.rmi.RemoteException;
import java.util.ArrayList;

public class AdvertServiceImplementation implements AdvertService {
    private ArrayList<Advertisement> adList;
    private ArrayList<ClientInterface> clientList;

    AdvertServiceImplementation() {
        super();
        adList = new ArrayList<>();
        clientList = new ArrayList<>();
    }

    @Override
    public ArrayList<Advertisement> getAdverts() {
        return adList;
    }

    @Override
    public void addClient(ClientInterface client) throws RemoteException {
        clientList.add(client);
    }

    public void postAdvert(Advertisement ad) throws RemoteException{
        adList.add(ad);
        sendToClients();
    }

    @Override
    public void removeAdvert(int index) throws RemoteException {
        adList.remove(index);
    }

    @Override
    public void sendToClients() throws RemoteException {
        for (var client : clientList) {
            client.print("New adverts");
            client.printAdverts(adList);
        }
    }
}
