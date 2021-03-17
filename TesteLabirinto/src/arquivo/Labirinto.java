package arquivo;

import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import estrutura.Coordenada;
import estrutura.Fila;
import estrutura.Matriz;
import estrutura.Pilha;

/**
 * Manipulação do Labirinto
 * @author POO-ProjetoC
 * @date 03-2021
 */

public class Labirinto {

	/**
	 * Inicia o labirinto.
	 * 
	 * @param fileName - Nome do arquivo txt.
	 * @return matriz - Matriz do Labirinto.
	 * @throws Exception - Caso não consiga criar a matriz.
	 */
	public static Matriz carregarLabirinto(String fileName) throws Exception {
		BufferedReader file = Arquivo.abrirArquivo(fileName);
		Matriz matriz = null;

		try {
			ArrayList<String> fileList = Arquivo.toArrayList(file);
			ArrayList<Integer> lineAndColumn = Arquivo.getFirstAndSecondLineAsInt(fileList);
			int line = lineAndColumn.get(0);
			int column = lineAndColumn.get(1);

			matriz = new Matriz(line, column);
			matriz.fillMatriz(fileList, line, column);
			System.out.println(matriz.toString());

		} catch (Exception ex) {
			throw new Exception("Erro ao criar a matriz");
		}
		return matriz;
	}

	/**
	 * Procura "E" nas bordas do labirinto.
	 * @param labirinto - Labirinto criado do txt.
	 * @return Coordenada(linha, coluna) - Posicao da entrada no labirinto.
	 * @throws Exception
	 *             - Caso tenha duas ou nenhuma entrada.
	 */
	private static Coordenada verificarEntrada(Matriz labirinto) throws Exception {
		String[][] m = labirinto.getMatriz();
		int linha = 0;
		int coluna = 0;
		int count = 0;

		for (int i = 0; i < labirinto.getLine(); i++) {
			for (int j = 0; j < labirinto.getColumn(); j++) {
				if ((m[i][j].equals("E"))
						&& (i == 0 || j == 0 || i == labirinto.getLine() - 1 || j == labirinto.getColumn() - 1)) {
					if (count == 0) {
						linha = i;
						coluna = j;
						count++;
					} else
						throw new Exception("O labirinto contem duas entradas.");

				}
			}
		}
		if (count == 1)
			return new Coordenada(linha, coluna);
		else
			throw new Exception("Nao foi encontrado uma entrada.");
	}

	/**
	 * Procura "S" nas bordas do labirinto.
	 * 
	 * @param labirinto - Labirinto criado do txt.
	 * @return Coordenada(linha, coluna) - Posicao Saida
	 * @throws Exception - Caso tenha duas ou nenhuma saida.
	 */
	private static Coordenada verificarSaida(Matriz labirinto) throws Exception {
		String[][] matriz = labirinto.getMatriz();
		int linha = 0;
		int coluna = 0;
		int count = 0;

		for (int i = 0; i < labirinto.getLine(); i++) {
			for (int j = 0; j < labirinto.getColumn(); j++) {
				if ((matriz[i][j].equals("S"))
						&& (i == 0 || i == labirinto.getLine() - 1 || j == 0 || j == labirinto.getColumn() - 1)) {
					if (count == 0) {
						count++;
						linha = i;
						coluna = j;
					} else
						throw new Exception("O labirinto contem duas saidas.");
				}
			}
		}
		if (count == 1)
			return new Coordenada(linha, coluna);
		else
			throw new Exception("Nao foi encontrado uma saida.");

	}

	/**
	 * Verifica Caracteres #, E, S ou " ".
	 * 
	 * @param labirinto - Labirinto criado do txt.
	 * @throws Exception - Caso encontre um caractere diferente de #, E, S ou " ".
	 */
	private static void verificarCaracteres(Matriz labirinto) throws Exception {
		String[][] matriz = labirinto.getMatriz();

		for (int i = 0; i < labirinto.getLine(); i++) {
			for (int j = 0; j < labirinto.getColumn(); j++) {
				if (!(matriz[i][j].equals("#") || matriz[i][j].equals("E") || matriz[i][j].equals("S")
						|| matriz[i][j].equals(" ")))
					throw new Exception("Caracteres fora dos padrões");
			}
		}
	}

	/**
	 * Procura o caminho para a saida do labirinto.
	 * 
	 * @param labirinto - Labirinto criado do txt.
	 * @return caminho - Retorna o caminho para a saida do labirinto.
	 * @throws Exception
	 */
	public static Pilha<Coordenada> procurarSaida(Matriz labirinto) throws Exception {

		String[][] matriz = labirinto.getMatriz();
		int capacidade = verificarTamanhoMatriz(labirinto);
		Pilha<Coordenada> caminho = new Pilha<Coordenada>(capacidade);
		Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>>(capacidade);
		verificarCaracteres(labirinto);
		Coordenada atual = verificarEntrada(labirinto);
		Coordenada saida = verificarSaida(labirinto);

		do {
			Fila<Coordenada> fila = verificarOrdem(atual, matriz, labirinto);
			if (fila == null) {
				return caminho;
			}

			try {
				atual = fila.desenfilere();
				matriz[atual.getLine()][atual.getColumn()] = "*";
				caminho.empilhar(atual);
				possibilidades.empilhar(fila);
			} catch (Exception ex) {
				if (atual != null) {
					matriz[atual.getLine()][atual.getColumn()] = "!"; //sinal ! por onde esta voltando
					atual = caminho.desempilhe();
					fila = possibilidades.desempilhe();
				}
			}
			labirinto.setMatriz(matriz);
			
			System.out.println(labirinto.toString());
			
		} while (!atual.equals(saida));

		return caminho;
	}

	/**
	 * Metodo para retornar o tamanho/Capacidade do labirinto.
	 * 
	 * @param labirinto
	 * @return capacidade
	 */
	private static int verificarTamanhoMatriz(Matriz labirinto) {
		int capacidade = labirinto.getLine() * labirinto.getColumn();
		return capacidade;
	}

	/**
	 * Mantem um padrao em sentido horario.
	 * 
	 * @param labirinto - Labirinto criado do txt.
	 * @param coordenada - Coordenada a verificar.
	 * @param matriz
	 * @return caminho - Retorna o caminho para a saida do labirinto.
	 * @throws Exception
	 */
	public static Fila<Coordenada> verificarOrdem(Coordenada coordenada, String[][] matriz, Matriz labirinto)
			throws Exception {
		Fila<Coordenada> fila = new Fila<Coordenada>(3);

		Coordenada cima = verificarCima(coordenada, matriz);
		if (cima != null) {
			if (cima.getLine() == -1)
				return null;
			
			fila.enfilere(cima);
		}

		Coordenada direita = verificarDireita(coordenada, matriz, labirinto);
		if (direita != null) {
			if (direita.getLine() == -1)
				return null;
			
			fila.enfilere(direita);
		}

		Coordenada baixo = verificarBaixo(coordenada, matriz, labirinto);
		if (baixo != null) {
			if (baixo.getLine() == -1)
				return null;
			
			fila.enfilere(baixo);
		}

		Coordenada esquerda = verificarEsquerda(coordenada, matriz);
		if (esquerda != null) {
			if (esquerda.getLine() == -1)
				return null;
			
			fila.enfilere(esquerda);
		}

		return fila;
	}

	/**
	 * Verifica Cima.
	 * 
	 * @param coordenada - Coordenada a verificar.
	 * @param matriz
	 * @return Coordenada(linha, coluna) caso encontrar um " ".
	 * @return Coordenada(-1, -1) caso encontrar um "S".
	 * @throws Exception
	 */
	private static Coordenada verificarCima(Coordenada coordenada, String[][] matriz) throws Exception {
		int linha = coordenada.getLine() - 1;
		int coluna = coordenada.getColumn();

		if (linha < 0)
			return null;

		if (matriz[linha][coluna].contains(" "))
			return new Coordenada(linha, coluna);

		if (matriz[linha][coluna].contains("S"))
			return new Coordenada(-1, -1);

		return null;
	}

	/**
	 * Verifica Direita.
	 * 
	 * @param coordenada - Coordenada a verificar.
	 * @param matriz
	 * @param labirinto - Labirinto criado do txt.
	 * @return Coordenada(linha, coluna) caso encontrar um " ".
	 * @return Coordenada(-1, -1) caso encontrar um "S".
	 * @throws Exception
	 */
	private static Coordenada verificarDireita(Coordenada coordenada, String[][] matriz, Matriz labirinto) throws Exception {
		int linha = coordenada.getLine();
		int coluna = coordenada.getColumn() + 1;

		if (coluna >= labirinto.getColumn())
			return null;

		if (matriz[linha][coluna].contains(" ")) {
			return new Coordenada(linha, coluna);
		}
		if (matriz[linha][coluna].contains("S"))
			return new Coordenada(-1, -1);

		return null;
	}

	/**
	 * Verifica Baixo.
	 * 
	 * @param coordenada - Coordenada a verificar.
	 * @param matriz
	 * @param labirinto - Labirinto criado do txt.
	 * @return Coordenada(linha, coluna) caso encontrar um " ".
	 * @return Coordenada(-1, -1) caso encontrar um "S".
	 * @throws Exception
	 */
	private static Coordenada verificarBaixo(Coordenada coordenada, String[][] matriz, Matriz labirinto) throws Exception {
		int linha = coordenada.getLine() + 1;
		int coluna = coordenada.getColumn();

		if (linha >= labirinto.getLine())
			return null;

		if (matriz[linha][coluna].contains(" ")) {
			return new Coordenada(linha, coluna);
		}
		if (matriz[linha][coluna].contains("S"))
			return new Coordenada(-1, -1);

		return null;
	}

	/**
	 * Verifica Esquerda.
	 * 
	 * @param coordenada - Coordenada a verificar.
	 * @param matriz
	 * @return Coordenada(linha, coluna) caso encontrar um " ".
	 * @return Coordenada(-1, -1) caso encontrar um "S".
	 * @throws Exception
	 */
	private static Coordenada verificarEsquerda(Coordenada coordenada, String[][] matriz) throws Exception {
		
		int linha = coordenada.getLine();
		int coluna = coordenada.getColumn() - 1;

		if (coluna < 0)
			return null;

		if (matriz[linha][coluna].contains(" "))
			return new Coordenada(linha, coluna);

		if (matriz[linha][coluna].contains("S"))
			return new Coordenada(-1, -1);

		return null;
	}
/**
 * Exibe o labirinto finalizado sem o caminho errado
 * @param labirinto
 * @throws Exception
 */
	public static void carregarLabirintoFinal(Matriz labirinto) throws Exception {
		Pilha<Coordenada> caminho = procurarSaida(labirinto);
		String[][] matriz = labirinto.getMatriz();		

		System.out.println("Labirinto Finalizado\n");
		for (int i = 0; i < labirinto.getLine(); i++) {
			
			for (int j = 0; j < labirinto.getColumn(); j++) {
				if (matriz[i][j].contains("!")) {
					matriz[i][j] = " ";
				}
			}
		}
		System.out.println(labirinto.toString());
		System.out.println("\t\t\t\t\tCaminho Percorrido\n" + caminho.toString());

	}
}
