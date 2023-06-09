package Serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeur extends Remote {

    void enregistrerNoeud(ServiceCalcul client) throws RemoteException;

    ServiceCalcul getNoeud() throws RemoteException;
}
