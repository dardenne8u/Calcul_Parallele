package Serveur;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeur extends Remote {

    void enregistrerNoeud(ServiceCalcul client) throws RemoteException;

    void lancerNoeuds(int debutIp, int finIp) throws IOException;

    ServiceCalcul getNoeud() throws RemoteException;

    void exitPrograms() throws RemoteException;
}
