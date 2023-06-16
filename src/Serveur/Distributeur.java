package Serveur;

import raytracer.Image;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur implements ServiceDistributeur{

    List<ServiceCalcul> noeuds;

    int i = 0;

    public Distributeur() throws IOException{
        this.noeuds = new ArrayList<>();
        System.out.println("Ip du serveur : "+InetAddress.getLocalHost());
        /**for(int j = ipDebut; j<ipFin; j++) {
            String[] cmd = new String[3];
            cmd[0] = "./lancerNoeud.sh";
            cmd[1] = String.valueOf(j);
            cmd[2] = String.valueOf(InetAddress.getLocalHost());
            System.out.print("Création d'un noeud avec comme adresse 100.64.80." + j);
            this.processus.add(Runtime.getRuntime().exec(cmd));
            System.out.println(", fait");
        }
        System.out.println(this.noeuds);*/
    }

    public void enregistrerNoeud(ServiceCalcul client) throws RemoteException {
        this.noeuds.add(client);
    }

    public void exitPrograms () throws RemoteException {
        /**for (Process process : processus) {
            process.destroy();
        }*/
    }

    public ServiceCalcul getNoeud() throws RemoteException {
        ServiceCalcul res = this.noeuds.get(i);
        i = (i < this.noeuds.size()-1) ? i+1 : 0;
        return res;
    }
}
