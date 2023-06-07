package Serveur;

import raytracer.Image;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur {

    List<Image> images;
    List<ServiceCalcul> clients;

    public Distributeur(){
        this.clients = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public void enregistrerClient(ServiceCalcul client) throws Exception{
        this.clients.add(client);
    }

    public void compute(int x0, int y0, int l, int h) throws RemoteException {
        for(ServiceCalcul client : this.clients){
            client.compute(x0, y0, l, h);
        }
    }
}
