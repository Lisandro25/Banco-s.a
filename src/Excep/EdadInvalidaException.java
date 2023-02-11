package Excep;

import java.io.IOException;

public class EdadInvalidaException extends IOException{
	public EdadInvalidaException()
	{
		
	}

	@Override
	public String getMessage() {

		return "La Edad tiene que ser entre 0 a 100 años";
	}
}
