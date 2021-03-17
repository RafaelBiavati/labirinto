package estrutura;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Pilha<X> {
	
	private int topo;
	private Object[] vetor;

	
	public Pilha(int tamanho) throws Exception {
		
		if (tamanho < 2)
			throw new Exception("Capacidade invalida");

		this.vetor = new Object[tamanho];
		this.topo = -1;
	}

	public void empilhar(X conteudo) throws Exception {
		
		if (conteudo == null)
			throw new Exception("conteudo a empilhar está nulo");

		if (this.topo == this.vetor.length - 1)
			throw new Exception("pilha está cheia");

		this.topo++;
		this.vetor[this.topo] = conteudo;
		
		if (conteudo instanceof Cloneable)
			this.vetor[this.topo] = this.cloneForcado (conteudo);
		else
			this.vetor[this.topo] = conteudo;	
	}

	public X desempilhe() throws Exception {

		if (this.topo == -1)
			throw new Exception("Nao ha o que recuperar");

		this.topo--;
		X aux = (X) this.vetor[this.topo];
		
		if (aux instanceof Cloneable)
			this.vetor[this.topo] = this.cloneForcado (aux);
		else
			this.vetor[this.topo] = aux;
		return aux;
	}

	public X consultaTopo() throws Exception {
		if (pilhaVazia())
			throw new Exception("Pilha vazia");
		return (X) this.vetor[this.topo];
	}

	public boolean pilhaCheia() {
		if (this.topo == this.vetor.length - 1)
			return true;
		return false;
	}

	public boolean pilhaVazia() {
		if (this.topo == -1)
			return true;
		return false;
	}

	public int getTopo() {
		return this.topo;
	}

	public void setTopo(int topo) {
		this.topo = topo;
	}

	public Object[] getVetor() {
		return vetor;
	}

	public void setVetor(Object[] vetor) {
		this.vetor = vetor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + topo;
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
		Pilha other = (Pilha) obj;
		if (topo != other.topo)
			return false;
		if (!Arrays.equals(vetor, other.vetor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String ret = "";

		ret += "(";
		for (int i = 0; i <= this.topo; i++) {
			if (i == this.topo - 1) {
				ret += this.vetor[i];
			} else {
				ret += this.vetor[i] + "";
			}
		}
		ret += ")";

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
	
	public Pilha (Pilha<X> modelo) throws Exception{
		if(modelo == null)
			throw new Exception("modelo ausente");
		
		this.vetor = new Pilha [modelo.vetor.length];
		for(int i = 0; i <= modelo.topo; i++){
			this.vetor[i] = this.cloneForcado( (X)modelo.vetor[i]   );
		}
		
	}
	
	public Object Clone(){
		Pilha<X> ret = null;
		try
		{
		
			ret = new Pilha<X> (this);
				
		}catch(Exception erro){}
		
		return ret;
	}
}
