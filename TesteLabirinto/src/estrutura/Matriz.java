package estrutura;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Manipulação da Matriz
 * 
 * @author POO-ProjetoC
 * @date 03-2021
 */

public class Matriz {
	private String[][] matriz;
	private int line;
	private int column;

	/**
	 * Cria matriz.
	 * 
	 * @param line - Quantidade de linhas que a matriz deve ter.
	 * @param column - Quantidade de colunas que a matriz deve ter.
	 * @throws Exception - Linhas/colunas Insufiscientes para criar uma matriz.
	 */
	public Matriz(final int line, final int column) throws Exception {
		if (line < 2 || column < 2)
			throw new Exception("Linha/Coluna Insufiscientes para criar matriz.");
		this.line = line;
		this.column = column;

		this.matriz = new String[this.line][this.column];
	}


	/**
	 * Preenche a matriz a partir de uma lista de string.
	 * 
	 * @param contentList - Lista de String que vai popular a matriz.
	 * @param linha - Quantidade de linhas.
	 * @param coluna - Quantidade de colunas.
	 * @throws Exception - Quando o labirinto sair deformado.
	 */
	public void fillMatriz(ArrayList<String> contentList, int linha, int coluna) throws Exception {
		int aux = 0;
		for (int i = 2; i<linha+2 ; i++, aux++) {
			for (int j = 0; j < coluna; j++) {
				try{
					this.matriz[aux][j] = "" + contentList.get(i).charAt(j);
				}catch(Exception ex){
					System.out.println("Labirinto deformado.");
				}
			}
		}
		
	}
	
	public String[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(String[][] matriz) {
		this.matriz = matriz;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + line;
		result = prime * result + Arrays.deepHashCode(matriz);
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
		Matriz other = (Matriz) obj;
		if (column != other.column)
			return false;
		if (line != other.line)
			return false;
		if (!Arrays.deepEquals(matriz, other.matriz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i < this.line; i++) {
			for (int j = 0; j < this.column; j++) {
				ret = ret + this.matriz[i][j];
			}
			ret = ret + "\n";
		}
		return ret;
	}

	public Matriz(Matriz modelo) throws Exception {
		if (modelo==null)
			throw new Exception ("Modelo ausente");
		
		this.line = modelo.getLine();
		this.column = modelo.getColumn();
		
		for(int i=0; i<= modelo.getLine(); i++){
			for(int j=0; j <= modelo.getColumn(); j++){
				
				this.matriz[i][j] = modelo.matriz[i][j];
			}
		}
	}

	public Object clone ()
	{
		Matriz matriz = null;
		try
		{
			matriz = new Matriz (this);
		}
		catch (Exception erro)
		{}
		return matriz;
	}
}
