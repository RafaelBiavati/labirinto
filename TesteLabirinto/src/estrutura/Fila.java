package estrutura;
import java.lang.reflect.Method;
import java.util.Arrays;



public class Fila<X>{

	private Object[] vetor;
	private int fim;

	public Fila(int tamanho) throws Exception {
		if (tamanho < 2)
			throw new Exception ("tamanho de fila inválida");
		
		this.vetor = new Object[tamanho];
		this.fim = 0;
	}
	
	
	public void enfilere(X conteudo) throws Exception {
		if (conteudo == null)
			throw new Exception ("valor nulo");
		
		if (this.fim == this.vetor.length)
			throw new Exception("Pilha Cheia");

		this.vetor[this.fim] = conteudo;
		this.fim++;
		
		if (conteudo instanceof Cloneable)
			this.vetor[this.fim] = this.cloneForcado (conteudo);
		else
			this.vetor[this.fim] = conteudo;
	}

	
	public X desenfilere() throws Exception {
		if (this.fim == 0)
			throw new Exception("Pilha vazia");
		X valorRetorno = (X)this.vetor[0];
		int fimDaFila = this.fim ;
		
		
		for (int i = 0 ; i < fimDaFila; i++) {
			if (i == fimDaFila-1){
				this.vetor[i] = null;
			} else {
			this.vetor[i] = this.vetor[i+1];
			}
		}
		this.fim--;
		
		
		if (valorRetorno instanceof Cloneable)
			this.vetor[this.fim] = this.cloneForcado (valorRetorno);
		else
			this.vetor[this.fim] = valorRetorno;
		
		return valorRetorno;
	}

	public X recuperaElemento() throws Exception{
		if (this.fim == 0)
			throw new Exception ("Pilha Vazia");
		
		return (X)this.vetor[this.fim];
	}
	
	public int getFim() {
		return this.fim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fim;
		result = prime * result + Arrays.hashCode(vetor);
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
		Fila other = (Fila) obj;
		if (fim != other.fim)
			return false;
		if (!Arrays.equals(vetor, other.vetor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret="";
		
		ret+=(this.fim)+" elementos";
		
		if (this.fim>0)
		{
		    ret+=" (Ultimo: "+this.vetor[this.fim];
	    	ret+=")";
		}
		
		return ret;
	}
	
	private X cloneForcado(X x) {
		
		Class<?> classe = x.getClass();
		Class<?> parmNull = null;
		
		Method metodo = null;
		
		try
		{
			metodo = classe.getMethod("clone", parmNull);
			
		}catch (Exception erro)
		{}
		
		Object[] parmReal = null;

		X ret = null;
		
		try
		{
			ret = (X)metodo.invoke(x,parmReal);
		}catch(Exception erro){}
		
		return null;
	}
	
	public Object Clone(){
		Fila<X> ret = null;
		try
		{
			ret = new Fila<X> (this);
				
		}catch(Exception erro){}
		
		return ret;
	}
	
	
	public Fila (Fila<X> modelo) throws Exception{
		if(modelo == null)
			throw new Exception("modelo ausente");
		
		this.vetor = new Fila [modelo.vetor.length];
		for(int i = 0; i <= modelo.fim; i++){
			this.vetor[i] = this.cloneForcado( (X)modelo.vetor[i]   );
		}
		
	}
	


}
