package edu.ucsb.cs56.projects.utilities.credit_card_validator;
import spark.*;
import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
	get("/hello", new Route() {
		@Override
		public Object handle(Request request, Response response) {
		    return "Hello World";
		}
	    });
    }
}
