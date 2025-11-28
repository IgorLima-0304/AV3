package entidades.modelos;

// O abstract serve pra dizer que essa classe é apenas um modelo base.
// Ninguém pode criar um 'new Carro()', só 'new Sedan()', etc.
public abstract class Carro {
    
    // Usamos 'protected' para que as classes filhas (Sedan, Suv, Esporte) consigam acessar essas variáveis diretamente.
    protected String marca;
    protected Double valorDeCusto;
    protected String modelo;
    protected Integer potencia; // Aqui simplificamos a potência como um número inteiro
    
    // Construtor: Prepara o carro com os dados básicos quando ele nasce
    public Carro(String marca, Double valorDeCusto, String modelo, Integer potencia) {
        this.marca = marca;
        this.valorDeCusto = valorDeCusto;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    // Getters e Setters (pra pegar ou alterar valores de fora)

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getValorDeCusto() {
        return valorDeCusto;
    }

    // Esse método é importante pra opção de alterar preço funcionar
    public void setValorDeCusto(double novoPreco) {
        this.valorDeCusto = novoPreco;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    //Método Abstrato
    //Como é abstrato, ele não tem código aqui
    //Ele serve para obrigar as classes filhas a criarem a lógica de venda delas.
    public abstract double precoVenda();
}