package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Recipe class
 *
 * @author   Sarah & Mike
 */
public class Recipe implements Serializable {
	/**
	 * Name of recipe: string
	 */
	private String name;
	/**
	 * Price of recipe: int
	 */
    private int price;
	/**
	 * amount of coffee for a single recipe: int
	 */
    private int amtCoffee;
	/**
	 * amount of milk for a single recipe: int
	 */
    private int amtMilk;
	/**
	 * amount of sugar for a single recipe: int
	 */
    private int amtSugar;
	/**
	 * amount of chocolate for a single recipe: int
	 */
    private int amtChocolate;

	/**
	 * Recipe class constructor
	 * Initialize each attribute
	 */
    public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }

	/**
	 * Get amount of chocolate
	 * @return int Returns the amtChocolate.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}

	/**
	 * Add the amtChocolate if it is more than 0
	 * @param amtChocolate The amtChocolate to setValue.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		} 
	}

	/**
	 * Get amount of coffee
	 * @return int Returns the amtCoffee.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}

	/**
	 * Add the amtCoffee if it is more than 0
	 * @param amtCoffee The amtCoffee to setValue.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		} 
	}

	/**
	 * Get amount of milk
	 * @return int Returns the amtMilk.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}

	/**
	 * Add the amtMilk if it is more than 0
	 * @param amtMilk The amtMilk to setValue.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		} 
	}

	/**
	 * Get amount of sugar
	 * @return int Returns the amtSugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}

	/**
	 * Add the amtSugar if it is more than 0
	 * @param amtSugar The amtSugar to setValue.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		} 
	}

	/**
	 * Get name of recipe
	 * @return string Returns the key.
	 */
    public String getName() {
		return name;
	}

	/**
	 * Set the recipe name if it is not null
	 * @param name The key to setValue.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}

	/**
	 * Get price
	 * @return int Returns the price.
	 */
    public int getPrice() {
		return price;
	}

	/**
	 * Set the price name if it is more than 0
	 * @param price The price to setValue.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		} 
	}

	/**
	 * Returns true if there is already the same name in coffee maker,
	 * otherwise returns false
	 * @param r Recipe object
	 * @return boolean
	 */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

	/**
	 * Returns the converted name
	 * @return string
	 */
    public String toString() {
    	return name;
    }

	/**
	 * Create a recipe object,
	 * catch RemoteException and list it in throws clause
	 * @param context The context of recipe object.
	 * @return Recipe
	 * @throws ContextException Throws ContextException if RemoteException found
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		try {
			r.name = (String)context.getValue("key");
			r.price = (int)context.getValue("price");
			r.amtCoffee = (int)context.getValue("amtCoffee");
			r.amtMilk = (int)context.getValue("amtMilk");
			r.amtSugar = (int)context.getValue("amtSugar");
			r.amtChocolate = (int)context.getValue("amtChocolate");
		} catch (RemoteException e) {
			throw new ContextException(e);
		}
		return r;
	}

	/**
	 * Get context and assign values to each attribute in Recipe class
	 * @param recipe Newly created recipe object
	 * @return Context
	 * @throws ContextException Throws ContextException if RemoteException found
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("key", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		return cxt;
	}


}
