package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

/**
 * Created by Nader Bouaziz.
 */
public interface CoffeePayment {

    public Context getPoint(Context context) throws RemoteException, ContextException;

}
