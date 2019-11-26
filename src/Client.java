import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements ClientInterface {

    public AdvertService stub;

    private String name;
    private String email;

    Client(String name, String email, String host, int registryPort, String registryRecord) throws RemoteException, NotBoundException, MalformedURLException {
        this.name = name;
        this.email = email;
        this.stub = connect(host, registryPort, registryRecord);
        printAdverts(stub.getAdverts());
        System.out.println("Connected to host.");
    }

    private AdvertService connect(String host, int port, String registryRecord) throws RemoteException, NotBoundException, MalformedURLException {
        AdvertService stub = (AdvertService)Naming.lookup("rmi://" + host + ":" + port + "/" + registryRecord);
        ClientInterface client = (ClientInterface)UnicastRemoteObject.exportObject(this, 2091);
        stub.addClient(client);
        return stub;
    }

    private void getCommand() throws RemoteException {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.print("Enter command (add, remove, list): ");
            String command = scan.nextLine();
            switch (command.toLowerCase()){
                case "add":
                    System.out.print("Enter advert title: ");
                    String title = scan.nextLine();
                    System.out.print("Enter advert body: ");
                    String body = scan.nextLine();
                    stub.postAdvert(new Advertisement(name, email, title, body));
                    break;
                case "remove":
                    System.out.println("Enter advert ID: ");
                    int index = scan.nextInt();
                    try{
                        stub.removeAdvert(index - 1);
                        System.out.printf("Advert %d is removed.\n", index);
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("No advert with such ID.");
                    }
                    break;
                case "list":
                    stub.sendToClients();
                    break;
            }
        }
    }

    public void printAdverts(ArrayList<Advertisement> adList){
        if (!adList.isEmpty()) {
            for (Advertisement ad : adList) {
                int index = adList.indexOf(ad) + 1;
                String name = ad.getName();
                String email = ad.getEmail();
                String title = ad.getTitle();
                String body = ad.getBody();
                LocalDateTime time = ad.getTime();
                System.out.printf("%d. Author:\t%s\n" +
                        "Email:\t%s\n" +
                        "Title:\t%s\n" +
                        "Body:\t%s\n" +
                        "Time created:\t%s\n", index, name, email, title, body, time);
            }
        }   else System.out.println("No adverts.");
    }

    @Override
    public void print(String msg) throws RemoteException {
        System.out.println(msg);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        System.out.print("Enter your email: ");
        String email = scan.nextLine();
        System.out.print("Enter host: ");
        String host = scan.nextLine();
        System.out.print("Enter registry port: ");
        int port = scan.nextInt();
        System.out.print("Enter registry record: ");
        scan.nextLine();
        String record = scan.nextLine();
        Client client = new Client(name, email, host, port, record);
        client.getCommand();
    }
}
