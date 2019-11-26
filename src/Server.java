import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter server IP: ");
        String IP = scan.nextLine();
        System.out.println("Enter registry port: ");
        int port = scan.nextInt();
        System.out.println("Enter registry record: ");
        scan.nextLine();
        String record = scan.nextLine();
        AdvertServiceImplementation service = new AdvertServiceImplementation();
        AdvertService stub = (AdvertService)UnicastRemoteObject.exportObject(service, 19000);
        Registry reg = LocateRegistry.createRegistry(port);
        Naming.rebind("rmi://" + IP + ":" + port + "/" + record, stub);
        System.out.println("Server is ready.");
    }
}
