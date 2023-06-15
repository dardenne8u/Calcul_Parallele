package Serveur;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {
    public static void main(String[] args) {
        int port = 1099;
        int ipDebut = 224;
        int ipFin = 246;

        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
            if(args.length > 1) {
                ipDebut = Integer.parseInt(args[1]);
                if(args.length > 2) {
                    ipFin = Integer.parseInt(args[2]);
                }
            }
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(port);
        } catch (Exception e) {
            System.out.println("Erreur (getRegistry): " + e.getMessage());
            System.exit(1);
        }

        ServiceDistributeur distributeur = null;
        try {
            distributeur = (ServiceDistributeur) UnicastRemoteObject.exportObject(new Distributeur(ipDebut, ipFin+1), 0);
        } catch (Exception e) {
            System.out.println("Erreur (exportObject): " + e.getMessage());
            System.exit(1);
        }

        try {
            registry.rebind("Distributeur", distributeur);
        } catch (Exception e) {
            System.out.println("Erreur (rebind): " + e.getMessage());
            System.exit(1);
        }

    }
}
