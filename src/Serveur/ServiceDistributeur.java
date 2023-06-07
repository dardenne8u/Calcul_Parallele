package Serveur;

import java.rmi.Remote;

public interface ServiceDistributeur extends Remote {

    void enregistrerClient(ServiceCalcul client) throws Exception;

    void compute(int x0, int y0, int l, int h) throws Exception;
}
