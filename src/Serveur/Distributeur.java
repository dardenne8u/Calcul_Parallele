package Serveur;

import raytracer.Image;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur implements ServiceDistributeur{

    List<ServiceCalcul> noeuds;
    List<Process> processus;

    int i = 0;

    public Distributeur() throws IOException{
        this.noeuds = new ArrayList<>();
        this.processus = new ArrayList<>();
        for(int j = 224; j<247; i++) {
            String[] cmd = new String[2];
            cmd[0] = "../lancerNoeud.sh";
            cmd[1] = String.valueOf(j);
            this.processus.add(Runtime.getRuntime().exec(cmd));
        }
    }

    public void enregistrerNoeud(ServiceCalcul client) throws RemoteException {
        this.noeuds.add(client);
    }

    public void exitPrograms(){
        for (Process process : processus) {
            process.destroy();
        }
    }

    public ServiceCalcul getNoeud() throws RemoteException {
        ServiceCalcul res = this.noeuds.get(i);
        i = (i < this.noeuds.size()-1) ? i+1 : 0;
        return res;
    }
}
