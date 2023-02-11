package Excep;

import java.io.IOException;

public class DniInvalidoException extends IOException{
	public DniInvalidoException()
	{
		
	}

	@Override
	public String getMessage() {

		return "El DNI solo puede contener numeros";
	}
}
