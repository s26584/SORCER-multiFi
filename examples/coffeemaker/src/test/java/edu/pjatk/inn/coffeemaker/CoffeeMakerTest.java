package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;
import sorcer.service.Routine;

import static org.junit.Assert.*;;
import static sorcer.eo.operator.*;
import static sorcer.so.operator.eval;
import static sorcer.so.operator.exec;

import static org.junit.Assert.*;
/**
 * @author Mike Sobolewski
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	private Recipe espresso, mocha, macchiato, americano;

	@Before
	public void setUp() throws ContextException {
		coffeeMaker = new CoffeeMaker();
		inventory = coffeeMaker.checkInventory();

		espresso = new Recipe();
		espresso.setName("espresso");
		espresso.setPrice(50);
		espresso.setAmtCoffee(6);
		espresso.setAmtMilk(1);
		espresso.setAmtSugar(1);
		espresso.setAmtChocolate(0);

		mocha = new Recipe();
		mocha.setName("mocha");
		mocha.setPrice(100);
		mocha.setAmtCoffee(8);
		mocha.setAmtMilk(1);
		mocha.setAmtSugar(1);
		mocha.setAmtChocolate(2);

		macchiato = new Recipe();
		macchiato.setName("macchiato");
		macchiato.setPrice(40);
		macchiato.setAmtCoffee(7);
		macchiato.setAmtMilk(1);
		macchiato.setAmtSugar(2);
		macchiato.setAmtChocolate(0);

		americano = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testAddRecipe() {
		assertTrue(coffeeMaker.addRecipe(espresso));
		assertTrue(coffeeMaker.addRecipe(americano));
		assertTrue(coffeeMaker.addRecipe(macchiato));
        assertFalse(coffeeMaker.addRecipe(mocha));
        
	}


    @Test
    public void testDeleteRecipe() {
        assertTrue(coffeeMaker.addRecipe(mocha));
        assertTrue(coffeeMaker.deleteRecipe(mocha));
        assertEquals(coffeeMaker.getRecipeForName("mocha"), null);
        assertFalse(coffeeMaker.deleteRecipe(mocha));
    }


    @Test
    public void testEditRecipe() {
        Recipe mocha_new = new Recipe();
        mocha_new.setName("mocha");
        mocha_new.setPrice(40);
        mocha_new.setAmtCoffee(7);
        mocha_new.setAmtMilk(1);
        mocha_new.setAmtSugar(2);
        mocha_new.setAmtChocolate(0);

        assertTrue(coffeeMaker.addRecipe(espresso));
        assertTrue(coffeeMaker.addRecipe(mocha));
        assertFalse(coffeeMaker.editRecipe(espresso, mocha_new));
    }


    @Test
    public void testAddandCheckInventory(){
        inventory.setCoffee(50);
        inventory.setSugar(50);
        inventory.setMilk(50);
        inventory.setChocolate(50);
        assertEquals(coffeeMaker.checkInventory().getCoffee(), 50);
        assertEquals(coffeeMaker.checkInventory().getMilk(), 50);
        assertEquals(coffeeMaker.checkInventory().getSugar(), 50);
        assertEquals(coffeeMaker.checkInventory().getChocolate(), 50);

        assertTrue(coffeeMaker.addInventory(20,0,0,0));
        assertEquals(coffeeMaker.checkInventory().getCoffee(), 70);

        assertTrue(coffeeMaker.addInventory(0,20,0,0));
        assertEquals(coffeeMaker.checkInventory().getMilk(), 70);

        assertTrue(coffeeMaker.addInventory(0,0,20,0));
        assertEquals(coffeeMaker.checkInventory().getSugar(), 70);

        assertTrue(coffeeMaker.addInventory(0,0,0,20));
        assertEquals(coffeeMaker.checkInventory().getChocolate(), 70);

		assertFalse(coffeeMaker.addInventory(-20,0,0,0));
		assertFalse(coffeeMaker.addInventory(0,-20,0,0));
		assertFalse(coffeeMaker.addInventory(0,0,-20,0));
		assertFalse(coffeeMaker.addInventory(0,0,0,-20));
    }


    @Test
    public void testMakeCoffe() {
        inventory.setChocolate(50);
        inventory.setCoffee(50);
        inventory.setSugar(50);
        inventory.setMilk(50);

        coffeeMaker.makeCoffee(espresso, 100);
        assertEquals(coffeeMaker.checkInventory().getCoffee(), 50-espresso.getAmtCoffee());

    }


    @Test
    public void testPurchase(){
        inventory.setChocolate(50);
        inventory.setCoffee(50);
        inventory.setSugar(50);
        inventory.setMilk(50);
		assertTrue(coffeeMaker.checkInventory().enoughIngredients(mocha));
        assertEquals(coffeeMaker.makeCoffee(espresso, 100), 50);
        assertEquals(coffeeMaker.makeCoffee(mocha, 100), 0);
    }




	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testContextCofee() throws ContextException {
		assertTrue(espresso.getAmtCoffee() == 6);
	}

	@Test
	public void testContextMilk() throws ContextException {
		assertTrue(espresso.getAmtMilk() == 1);
	}

	@Test
	public void addRecepie() throws Exception {
		coffeeMaker.addRecipe(mocha);
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addContextRecepie() throws Exception {
		coffeeMaker.addRecipe(Recipe.getContext(mocha));
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addServiceRecepie() throws Exception {
		Routine cmt = task(sig("addRecipe", coffeeMaker),
						context(types(Recipe.class), args(espresso),
							result("recipe/added")));

		logger.info("isAdded: " + exec(cmt));
		assertEquals(coffeeMaker.getRecipeForName("espresso").getName(), "espresso");
	}

	@Test
	public void addRecipes() throws Exception {
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		coffeeMaker.addRecipe(americano);

		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
		assertEquals(coffeeMaker.getRecipeForName("macchiato").getName(), "macchiato");
		assertEquals(coffeeMaker.getRecipeForName("americano").getName(), "americano");
	}

	@Test
	public void makeCoffee() throws Exception {
		coffeeMaker.addRecipe(espresso);
		assertEquals(coffeeMaker.makeCoffee(espresso, 200), 150);
		assertEquals(inventory.getCoffee(), 9);
	}

}

