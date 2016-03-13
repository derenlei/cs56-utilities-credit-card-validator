package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for Discover
 * @author Angel Rivera @ Stefana Gloginic
 * @version CS56, Winter 2016
 * @see Discover
 */


public class DiscoverGenerateTest{
    /*
       test generateCard Method in Discover class
    */

    @Test public void testGenerateCard(){

	String GenCard1 = Discover.generateCard();
	boolean result = CCValidator.isValid(GenCard1);

	assertEquals(true, result);
    }
}
