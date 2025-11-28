package entidades.modelos;
import entidades.servico.Ipva;
public class Sedan extends Carro implements Ipva {
	private int tamPortaMalas;
	public Sedan(String marca, Double valorDeCusto, String modelo, Integer potencia, int tamPortaMalas) {
		super(marca, valorDeCusto, modelo, potencia);
		this.tamPortaMalas = tamPortaMalas;
	}
	
	public int getTamPortaMalas() {
		return tamPortaMalas;
	}
	public void setTamPortaMalas(int tamPortaMalas) {
		this.tamPortaMalas = tamPortaMalas;
	}
	// Custo + 15% de margem
	@Override
	public double precoVenda() {
		double valorVenda= valorDeCusto + (valorDeCusto * 1.15);
		return valorVenda;
	}
	
	@Override
	public double calcularIpva() {
		double valorIpva= valorDeCusto * 0.03;
		return valorIpva;
	}

	@Override
	public String toString() {
		return "Marca=" + marca + ", Modelo=" + modelo +", Tamanho do portas malas (L)=" + tamPortaMalas + ", Valor bruto (R$)=" + valorDeCusto
				+ ", Potencia (Cv)=" + potencia + ".";
	}

	
	
	
}