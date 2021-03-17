package arquivo;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Pega Digitações
 * @author POO-ProjetoC
 * @date 03-2021
 */
public class Leitura {
	
	/**
	 * Lê a proxima linha que for digitada no teclado
	 * @return texto - texto Digitado
	 * @throws Exception
	 */
	public static String lerLinha() throws Exception{
		String texto = null;
		
		try {
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			texto = teclado.readLine();
		} catch(Exception eX) {
			throw new Exception("Erro leitura(teclado)");
		}
		return texto;
	}

}
