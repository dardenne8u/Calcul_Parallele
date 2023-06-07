package Serveur;

import java.rmi.Remote;

public interface ServiceDistributeur extends Remote {

    void enregistrerClient(ServiceCalcul client) throws Exception;

    ServiceCalcul getNoeud();
}
