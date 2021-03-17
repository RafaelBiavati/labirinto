package estrutura;
import java.util.Arrays;



/**
 * Manipulação da coordenada
 * @author POO-ProjetoC
 * @date 03-2021
 */
public class Coordenada {
	
	
	private int[] coordenada = new int[2];
	
/**
 * Cria a Coordenada
 * @param line
 * @param column
 */
	public Coordenada (final int line, final int column) throws Exception{
		
		this.coordenada[0] = line;
		this.coordenada[1] = column;
	}


	public int[] getCoordenada() {
		return coordenada;
	}	
	public void setCoordenada(int[] coordenada) {
		this.coordenada = coordenada;
	}
	public int getLine() {
		return this.coordenada[0];
	}
	public void setLine(int line) {
		this.coordenada[0] = line;
	}
	public int getColumn() {
		return this.coordenada[1];
	}
	public void setColumn(int column) {
		this.coordenada[1] = column;
	}



		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.coordenada);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (!Arrays.equals(this.coordenada, other.coordenada))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + this.coordenada[0] + ", " + this.coordenada[1] + ")";
	}

	public Coordenada(Coordenada modelo) throws Exception {
		if(modelo ==  null)
			throw new Exception ("Modelo ausente");
		 
		Coordenada[] c = new Coordenada [modelo.coordenada.length];
		for(int i =0; i < c.length; i++)
			this.coordenada[i] = modelo.coordenada[i];
		
	}
	
	public Object clone ()
	{
		Coordenada ret = null;
		
		try
		{
			ret = new Coordenada (this);
		}
		catch (Exception erro)
		{}
		return ret;
	}
}
