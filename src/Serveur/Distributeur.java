package Serveur;

import raytracer.Image;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur implements ServiceDistributeur{

    List<ServiceCalcul> noeuds;

    int i = 0;

    public Distributeur(){
        this.noeuds = new ArrayList<>();
    }

    public void enregistrerNoeud(ServiceCalcul client) throws RemoteException {
        this.noeuds.add(client);
    }

    public ServiceCalcul getNoeud() throws RemoteException {
        ServiceCalcul res = this.noeuds.get(i);
        i = (i < this.noeuds.size()-1) ? i+1 : 0;
        return res;
    }
}
