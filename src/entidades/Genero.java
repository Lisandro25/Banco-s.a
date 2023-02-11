package entidades;

public class Genero {
	
	//ATRIBUTOS
			private int Cod_genero;
			private String descripcion;
			
			//CONSTRUCTORES
			public Genero(){}
			
			public Genero(int Cod_genero, String descripcion)
			{
				this.Cod_genero = Cod_genero;
				this.descripcion = descripcion;
			}
			
			///GETTERS & SETTERS
			public String getDescripcion() {
				return descripcion;
			}

			public void setDescripcion(String descripcion) {
				this.descripcion = descripcion;
			}
			
			public int getCod_genero() {
				return Cod_genero;
			}

			public void setCod_genero(int Cod_genero) {
				this.Cod_genero = Cod_genero;
			}

			///METODO TOSTRING()
			@Override
			public String toString() {
				return descripcion;
			}
			

}
