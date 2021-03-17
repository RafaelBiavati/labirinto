package arquivo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

	/**
	 * Manipulação Arquivo
	 * @author POO-ProjetoC
	 * @date 03-2021
	 */
	public class Arquivo {
	
	public static BufferedReader abrirArquivo(String nomeArquivo) throws Exception{
		BufferedReader arquivo = null;
		try {
			arquivo = new BufferedReader(new FileReader(nomeArquivo));
		} catch (Exception error){
			throw new Exception("Arquivo não Encontrado");
		}
		return arquivo;
	}
	
	/**
	 * Conteudo do Arquivo vai para um Array
	 * @param arquivo - Arquivo a ir para o Array
	 * @return ArrayList<String> - Array com o Conteudo do Arquivo
	 * @throws Exception - Arquivo vazio
	 */
	public static ArrayList<String> toArrayList(BufferedReader arquivo) throws Exception {
		ArrayList<String> linhasArquivo = new ArrayList<String>();
		
		try{
			while(arquivo.ready()){
				linhasArquivo.add(arquivo.readLine());
			}
		} catch (Exception ex){
			throw new Exception("Arquivo sem Linhas");
		}
		return linhasArquivo;
	}
	
	
	/**
	 * Leitura das duas primeiras linhas do .txt (Linha e Coluna)
	 * @param listaArquivo - Lista de String do arquivo
	 * @return listaPrimSeg - Lista com a primeira e segunda linha
	 * @throws Exception - Primeira/segunda não são números inteiros
	 */
	public static ArrayList<Integer> getFirstAndSecondLineAsInt(ArrayList<String> listaArquivo) throws Exception {
		ArrayList<Integer> listaPrimSeg = new ArrayList<Integer>();
		
		try{
			String linha = listaArquivo.get(0);
			listaPrimSeg.add(Integer.parseInt(linha));
			
			String column = listaArquivo.get(1);
			listaPrimSeg.add(Integer.parseInt(column));
		}catch (Exception ex){
			throw new Exception("Primeira/Segunda não são Inteiros");
		}
			
		return listaPrimSeg;
	}
	
}
