package entidades.modelos;
import entidades.servico.Ipva;
public class Esporte extends Carro implements Ipva {
	private int velocidadeMax;
	private double tempo;
	
	public Esporte(String marca, Double valorDeCusto, String modelo, Integer potencia, int velocidadeMax, double tempo) {
		super(marca, valorDeCusto, modelo, potencia);
		this.velocidadeMax = velocidadeMax;
		this.tempo = tempo;
	}
	public int getVelocidadeMax() {
		return velocidadeMax;
	}
	public void setVelocidadeMax(int velocidadeMax) {
		this.velocidadeMax = velocidadeMax;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	
	public double precoVenda() {
		double valorVenda= valorDeCusto + (valorDeCusto * 1.40);
		return valorVenda;
	}
	
	public double calcularIpva() {
		double valorIpva = this.valorDeCusto * 0.07;
		return valorIpva;
	}
	@Override
	public String toString() {
		return "Marca=" + marca + ", Modelo=" + modelo +", Velocidade maxima (m/s)=" + velocidadeMax + ", Valor bruto (R$)="
				+ valorDeCusto + ", Tempo (s)=" + tempo + ", Potencia (Cv)=" + potencia + ".";
	}
	
	
	
}