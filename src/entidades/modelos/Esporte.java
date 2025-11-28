package entidades.modelos;

import entidades.servico.Ipva;

// extends Carro: Ela é filha de Carro, então herda marca, modelo, etc.
// implements Ipva : Ela obriga a ter o método de calcular imposto.

public class Esporte extends Carro implements Ipva {
    
    //Esses atributos são exclusivos de carros esportivos (Sedan e SUV não têm isso)
    private int velocidadeMax;
    private double tempo; //Tempo de 0 a 100 km/h
    
    public Esporte(String marca, Double valorDeCusto, String modelo, Integer potencia, int velocidadeMax, double tempo) {
        //O 'super' joga os dados básicos lá pra classe pai (Carro)
        super(marca, valorDeCusto, modelo, potencia);
        //Os dados específicos a gente guarda aqui mesmo
        this.velocidadeMax = velocidadeMax;
        this.tempo = tempo;
    }

    //Getters e Setters pros atributos privados daqui
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
    
    //Sobrescrita do método abstrato do pai.
    //Aqui definimos a regra de negócio: Esportivos são caros, então o lucro é 40%.
    @Override
    public double precoVenda() {
        double valorVenda = valorDeCusto + (valorDeCusto * 1.40);
        return valorVenda;
    }
    
    // Implementação da Interface Ipva.
    //O contrato obriga a ter esse método. Esportivos pagam 7% sobre o custo.
    @Override
    public double calcularIpva() {
        double valorIpva = this.valorDeCusto * 0.07;
        return valorIpva;
    }

    //O toString junta os dados que herdamos (marca, modelo) com os daqui
    @Override
    public String toString() {
        return "Marca=" + marca + 
               ", Modelo=" + modelo +
               ", Velocidade maxima (m/s)=" + velocidadeMax + 
               ", Valor bruto (R$)=" + valorDeCusto + 
               ", Tempo (s)=" + tempo + 
               ", Potencia (Cv)=" + potencia + ".";
    }   
}