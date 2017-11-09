package edu.ucsb.cs56.projects.utilities.credit_card_validator;
import spark.*;
import static spark.Spark.*;

public class WebMenu {
    public static void main(String[] args) {
	get("/hello", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    return "Hello World";
		}
	    });
	
	get("/CCValidator/:cardNumber", new Route(){
		@Override
		public Object handle(Request request, Response response){
		    String cardNumber = request.params(":cardNumber");
		    Boolean isValid = CCValidator.isValid(cardNumber);
		    if(isValid){
			String cardType = CCValidator.getCardType(cardNumber);
			return cardNumber + " is a valid " + cardType;
			
		    }
		    else{
			return cardNumber  + " is not valid";
			}
		}
	    });
	
       	get("/menu", (request, response) ->
	   "<!DOCTYPE html>" +
	    "<html>" +
	    "<body>" +
	    "<h1>Generate Credit Card</h1>" +
	    "<p><a href= '/generateCard/MasterCard'>MasterCard</a></p>" +
	    "<p><a href= '/generateCard/Visa'>Visa</a></p>" +
	    "<p><a href= '/generateCard/Discover'>Discover</a></p>" +
	    "<p><a href= '/generateCard/AmericanExpress'>AmericanExpress</a></p>" +
	    "<form action='/CCValidator' method='post' target='blank'>" +
	    "<h1>Validate Credit Card</h1>" +
	    "Credit Card Number: <input type='text' name='cardNumber' id='cardNumber'><br>" +
	    "<input type='submit' value='Submit'> " +
	    "</form>" +
	    "</body>" +
	    "</html>"
	    );

	post("/CCValidator", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    String cardNumber = "NULL";
		    try{
			cardNumber = request.queryParams("cardNumber");
		    }catch(Exception e){}
		    Boolean isValid = CCValidator.isValid(cardNumber);
		    if(isValid){
			String cardType = CCValidator.getCardType(cardNumber);
			return cardNumber + " is a valid " + cardType;
		    }
		    else{
			return cardNumber  + " is not valid";
			}
		}
	    });


	
	get("/generateCard/MasterCard", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    String GenCard = MasterCard.generateCard();
		    return GenCard;
		}
	    });

	get("/generateCard/Visa", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    String GenCard = Visa.generateCard();
		    return GenCard;
		}
	    });
	
	get("/generateCard/Discover", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    String GenCard = Discover.generateCard();
		    return GenCard;
		}
	    });
	
	get("/generateCard/AmericanExpress", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    String GenCard = AmericanExpress.generateCard();
		    return GenCard;
		}
	    });
    }
}
