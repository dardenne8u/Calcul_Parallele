import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.Duration;

import Serveur.ServiceCalcul;
import Serveur.ServiceDistributeur;
import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;

public class LancerRaytracer {

    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";

    public static void main(String args[]) {

        // Le fichier de description de la scène si pas fournie
        String fichier_description = "./simple.txt";

        // largeur et hauteur par défaut de l'image à reconstruire
        String server = "localhost";
        int port = 1099;
        int largeur = 512, hauteur = 512;
        int nbDivisions = 2;

        if (args.length > 0) {
            largeur = Integer.parseInt(args[0]);
            if (args.length > 1) {
                hauteur = Integer.parseInt(args[1]);
                if (args.length > 2) {
                    nbDivisions = Integer.parseInt(args[2]);
                    if (args.length > 3) {
                        server = args[3];
                        if (args.length > 4) {
                            port = Integer.parseInt(args[4]);
                        }
                    }
                }
            } else {
                System.out.println(aide);
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

        // création d'une fenêtre
        Disp disp = new Disp("Raytracer", largeur, hauteur);

        // Initialisation d'une scène depuis le modèle
        Scene scene = new Scene(fichier_description, largeur, hauteur);

        Instant debut = Instant.now();
        for (int i = 0; i < nbDivisions; i++) {
            for (int j = 0; j < nbDivisions; j++) {
                new ThreadImg(
                        i * largeur / nbDivisions,
                        j * hauteur / nbDivisions,
                        largeur / nbDivisions,
                        hauteur / nbDivisions,
                        serviceDistributeur,
                        disp
                ).start();
            }
        }
        /**Instant fin = Instant.now();
        long duree = Duration.between(debut, fin).toMillis();
        System.out.println("Temps d'affichage de l'image : " + duree);

        try {
            serviceDistributeur.exitPrograms();
        } catch (RemoteException e) {
            System.out.println("Erreur (registry)" + e.getMessage());
            System.exit(1);
        }*/
    }


    private static class ThreadImg extends Thread {
        private final Disp disp;
        private final int x, y, largeur, hauteur;
        private final ServiceDistributeur calculateur;

        public ThreadImg(int x, int y, int largeur, int hauteur, ServiceDistributeur calculateur, Disp disp) {
            this.x = x;
            this.y = y;
            this.largeur = largeur;
            this.hauteur = hauteur;
            this.calculateur = calculateur;
            this.disp = disp;
        }

        @Override
        public void run() {
            try {
                Image img = calculateur.getNoeud().compute(x, y, largeur, hauteur);
                disp.setImage(img, x, y);
            } catch (RemoteException e) {
                System.out.println("Erreur (compute)" + e.getMessage());
                System.exit(1);
            }
        }
    }

}


