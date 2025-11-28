package entidades.modelos;
import entidades.servico.Ipva;
public class Suv extends Carro implements Ipva{
	private String tracao;
	public Suv(String marca, Double valorDeCusto, String modelo, Integer potencia, String tracao) {
		super(marca, valorDeCusto, modelo, potencia);
		this.tracao = tracao;
	}
	public String getTracao() {
		return tracao;
	}
	public void setTracao(String tracao) {
		this.tracao = tracao;
	}
	// Custo + 15% de margem
	@Override
	public double precoVenda() {
		double valorVenda= valorDeCusto + (valorDeCusto * 1.25);
		return valorVenda;
	}
	
	@Override
	public double calcularIpva() {
		double valorIpva = valorDeCusto * 0.04;
		return valorIpva;
	}
	@Override
	public String toString() { 
		return "Marca=" + marca + ", Modelo=" + modelo + ", Tracao=" + tracao + ", Valor bruto (R$)=" + valorDeCusto +  ", Potencia (Cv)=" + potencia + ".";
	}
	
	
	
	
}