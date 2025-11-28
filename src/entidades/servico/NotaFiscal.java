package entidades.servico;
import entidades.modelos.Carro;
import java.util.Date;
public class NotaFiscal {
	private Date dataCompra;
	private String nomeCliente;
	private Double preco;
	private Carro carro;
	
	public NotaFiscal(Date dataCompra, String nomeCliente, Double preco, Carro carro) {
		this.dataCompra = dataCompra;
		this.nomeCliente = nomeCliente;
		this.preco = preco;
		this.carro = carro;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
}
