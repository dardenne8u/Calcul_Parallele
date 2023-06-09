package Serveur;

import java.rmi.Remote;

public interface ServiceDistributeur extends Remote {

    void enregistrerNoeud(ServiceCalcul client) throws Exception;

    ServiceCalcul getNoeud();
}
