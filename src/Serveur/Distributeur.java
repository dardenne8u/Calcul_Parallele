package Serveur;

import raytracer.Image;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur {

    List<Image> images;
    List<ServiceCalcul> clients;

    int i = 0;

    public Distributeur(){
        this.clients = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public void enregistrerNoeud(ServiceCalcul client) throws Exception{
        this.clients.add(client);
    }

    public ServiceCalcul getNoeud(){
        ServiceCalcul res = this.clients.get(i);
        i = (i < this.clients.size()-1) ? i+1 : 0;
        return res;
    }
}
