package dariocecchinato.s18l3_spring_web_data.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String msg){
		super(msg);
	}
}
