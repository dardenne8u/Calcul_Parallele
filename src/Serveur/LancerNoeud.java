package Serveur;

import raytracer.Scene;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerNoeud {
    public static void main(String[] args) {

        String server = "100.64.80.228";
        int port = 1099;
        String filename = "simple.txt";
        int largeur = 512, hauteur = 512;

        if(args.length > 0) {
            server = args[0];
            if(args.length > 1) {
                port = Integer.parseInt(args[1]);
                if(args.length > 2) {
                    filename = args[2];
                    if(args.length > 3) {
                        largeur = Integer.parseInt(args[3]);
                        if(args.length > 4) {
                            hauteur = Integer.parseInt(args[4]);
                        }
                    }
                }

            }
        }

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(server, port);
        } catch (RemoteException e) {
            System.out.println("Erreur (registry)" + e.getMessage());
            System.exit(1);
        }

        ServiceDistributeur serviceDistributeur = null;
        try {
            serviceDistributeur = (ServiceDistributeur) registry.lookup("Distributeur");
        } catch (Exception e) {
            System.out.println("Erreur (lookup)" + e.getMessage());
            System.exit(1);
        }

        try {
            ServiceCalcul scene = new Scene(filename, largeur, hauteur);
            scene = (ServiceCalcul) UnicastRemoteObject.exportObject(scene, 0);
            serviceDistributeur.enregistrerNoeud(scene);
        } catch (Exception e) {
            System.out.println("Erreur (rebind)" + e.getMessage());
            System.exit(1);
        }
    }
}
