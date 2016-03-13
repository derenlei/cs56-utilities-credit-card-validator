package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** test class for MasterCard
 * @author Angel Rivera @ Stefana Gloginic
 * @version CS56, Winter 2016
 * @see MasterCard
 */


public class MasterCardGenerateTest{
    /*
       test generateCard Method in MasterCard class
    */

    @Test public void testGenerateCard(){

	String GenCard1 = MasterCard.generateCard();
	boolean result = CCValidator.isValid(GenCard1);

	assertEquals(true, result);
    }
}
