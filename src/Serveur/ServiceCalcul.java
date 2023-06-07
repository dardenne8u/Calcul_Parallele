package Serveur;

import raytracer.Image;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalcul extends Remote {

    Image compute(int x0, int y0, int l, int h) throws RemoteException;
}
