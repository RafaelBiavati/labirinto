package main;
import arquivo.Labirinto;
import arquivo.Leitura;
import estrutura.Coordenada;
import estrutura.Matriz;


public class run {

	public static void main(String[] args) {
		try {
			System.out.println("POO - Projeto C\n");
			System.out.println("Nome do Labirinto: ");
			String fileName = Leitura.lerLinha();
			Matriz labirinto = Labirinto.carregarLabirinto(fileName + ".txt"); //inclui extensao .txt para abertura do arquivo
			Labirinto.carregarLabirintoFinal(labirinto);		
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

	}

}
