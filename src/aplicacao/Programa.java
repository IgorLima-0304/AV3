package aplicacao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import entidades.modelos.Carro;
import entidades.modelos.Sedan;
import entidades.modelos.Suv;
import entidades.modelos.Esporte;
import entidades.servico.Ipva;
import entidades.servico.NotaFiscal;
public class Programa {
	
	public static void main(String[] args) throws ParseException{
		Scanner sc = new Scanner(System.in);
		List<Carro> carros = new ArrayList<>();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		cadastrarSedans(carros);
		cadastrarSuvs(carros);
		cadastrarEsportes(carros);
		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\n===== MENU PARA ACESSAR GARAGEM DA LOJA =====");
			System.out.println("1 - Listar os carros da garagem;");
			System.out.println("2 - Buscar por modelo do carro;");
			System.out.println("3 - Vender um carro;");
			System.out.println("4 - Receber um novo carro na garagem;");
			System.out.println("5 - Alterar preço bruto;");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");
			opcao = sc.nextInt();
			sc.nextLine();
			switch (opcao) {
			case 1:				
				Collections.sort(carros, new Comparator<Carro>() {
					public int compare(Carro c1, Carro c2) {
						return c1.getValorDeCusto().compareTo(c2.getValorDeCusto());
					}
				});
				System.out.println("Carros listados e ordenados por valor bruto (R$):");
				for (Carro c : carros) {
					System.out.println(c);
				}
				break;
				
			case 2:
				// Busca um carro pelo modelo digitado
				System.out.print("Digite o modelo que deseja encontrar: ");
				String modeloBusca = sc.nextLine();
				boolean encontrado = false;
				for (Carro c : carros) {
					if (c.getModelo().equalsIgnoreCase(modeloBusca)) {
						System.out.println(c);
						encontrado = true;
						
						if (c instanceof Ipva) {
							Ipva valor = (Ipva)c;
							double ipvaCalculado = valor.calcularIpva();
							System.out.printf ("Ipva do veiculo: R$ %.2f ",ipvaCalculado);	
						}
					}
				}
				
				if (!encontrado) {
					System.out.println("Carro não encontrado.");
				}
				
				
				break;
			case 3:
    System.out.print("Digite o modelo que deseja vender: ");
    String modeloExclusao = sc.nextLine();
    encontrado = false;
    Carro carroParaRemover = null;

    // FOR EACH pra achar o carro
    for (Carro c : carros) {
        if (c.getModelo().equalsIgnoreCase(modeloExclusao)) {
            System.out.println(c);
            encontrado = true;
            
            if (c instanceof Carro) {
                double precoDeVenda = c.precoVenda();
                System.out.printf("Preço de venda: R$ %.2f \n", precoDeVenda);
                
                System.out.println("\n--- Gerando Nota Fiscal ---");
                System.out.print("Nome do cliente comprador: ");
                String nomeCliente = sc.nextLine();
                
                //Pegando a data/hora atual do PC automaticamente
                Date dataAtual = new Date();
                
                //Formatador para mostrar data E hora
                SimpleDateFormat sdfHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                NotaFiscal novaNota = new NotaFiscal(dataAtual, nomeCliente, precoDeVenda, c);
                
                //Montando o texto da nota em uma String
                StringBuilder notaTexto = new StringBuilder();
                notaTexto.append("--------------------------------------------------\n");
                notaTexto.append("             NOTA FISCAL DE VENDA                 \n");
                notaTexto.append("--------------------------------------------------\n");
                notaTexto.append("Data da Venda: " + sdfHora.format(novaNota.getDataCompra()) + "\n");
                notaTexto.append("Cliente: " + novaNota.getNomeCliente() + "\n");
                notaTexto.append("Veículo: " + novaNota.getCarro().toString() + "\n");
                notaTexto.append(String.format("Valor Total: R$ %.2f\n", novaNota.getPreco()));
                notaTexto.append("--------------------------------------------------\n");
                
                //Imprime na tela
                System.out.println(notaTexto.toString());
                System.out.println("\n>>> Venda concluída <<<");

                //Salvando em arquivo
                
                String caminhoNota = "C:\\Users\\Igor\\OneDrive\\Área de Trabalho\\AV3\\src\\temp\\nota_" + nomeCliente + ".txt";
                
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoNota))) {
                    bw.write(notaTexto.toString());
                    System.out.println("Nota fiscal salva em: " + caminhoNota);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar nota: " + e.getMessage());
                }

                //Marca para remover
                carroParaRemover = c;
            }
            break; //Para o loop
        }
    }

    //Remove da lista
    if (carroParaRemover != null) {
        carros.remove(carroParaRemover);
    } else {
        System.out.println("Modelo não encontrado.");
    }
    break;
			//Inclui carros na garagem
			case 4:
				System.out.println("Digite quantos carros deseja inserir na garagem: ");
				int num = sc.nextInt();
				for (int i=1; i<=num; i++) {
					System.out.println("Dados do produto #" +i+ ":");
					System.out.println("Informe a marca: ");
					sc.nextLine();
					String marca = sc.nextLine();
					sc.nextLine();
					System.out.println("Informe o modelo: ");
					String modelo = sc.nextLine();
					sc.nextLine();
					System.out.println("Informe o preco bruto: ");
					double precoBruto = sc.nextDouble();
					System.out.println("Informe a potencia do carro (Cv): ");
					int potencia = sc.nextInt();
					sc.nextLine();
					System.out.println("Informe o tipo de carro (Suv, Sedan e Esporte) ");
					String tipoCarro = sc.nextLine();
					if (tipoCarro.equalsIgnoreCase("Sedan")) {
						System.out.println("Informe, em litro, o tamanho do porta malas: ");
						int mala = sc.nextInt();
						carros.add(new Sedan(marca, precoBruto, modelo, potencia, mala));
					}
					else if (tipoCarro.equalsIgnoreCase("Suv")) {
						System.out.println("Informe o tipo de tracao: ");
						String tracao = sc.nextLine();
						carros.add(new Suv(marca, precoBruto, modelo, potencia, tracao));	
						
				} else {
					System.out.println("Informe a velocidade maxima do carro: ");
					int velocidade = sc.nextInt();	
					System.out.println("Informe o tempo medio que o carro levar pra chegar na velocidade maxima: ");
					double tempo = sc.nextDouble();
					carros.add(new Esporte(marca, precoBruto, modelo, potencia, velocidade, tempo));
				}
					System.out.println("Carro adicionado!!");
					
				}
				break;
				
			//Altera preço bruto dos carros
			case 5:
				System.out.println("Informe o modelo do carro que deseja alterar o valor bruto: ");
				String modeloAlteracao = sc.nextLine();
				for (Carro c : carros) {
					if (c.getModelo().equalsIgnoreCase(modeloAlteracao)) {
						System.out.println(c);
						encontrado = true;
						if (c instanceof Carro) {
							Carro preco = (Carro)c;
							System.out.printf ("Preco bruto atual desse carro é R$ %.2f ",preco.getValorDeCusto());
							System.out.println();
							System.out.println ("Informe o novo preco para alterar: ");
							double novoPreco = sc.nextDouble();
							preco.setValorDeCusto(novoPreco);
							System.out.printf ("O novo preco de venda para %s está R$ %.2f", modeloAlteracao, novoPreco);
				}}}
				
				break;
			case 0:
				//Finaliza o programa
				System.out.println("Encerrando o programa.");
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}
		sc.close();
	}
	// Métodos pra cadastrar 7 carros
	public static void cadastrarSedans(List<Carro> carros) {
	    carros.add(new Sedan("Toyota", 50000.0, "Corolla", 140, 470));
	    carros.add(new Sedan("Honda", 52000.0, "Civic", 150, 480));
	    carros.add(new Sedan("Hyundai", 51000.0, "Elantra", 147, 460));
	    carros.add(new Sedan("Chevrolet", 49000.0, "Cruze", 153, 450));
	    carros.add(new Sedan("Nissan", 53000.0, "Sentra", 140, 460));
	    carros.add(new Sedan("Volkswagen", 54000.0, "Virtus", 128, 440));
	    carros.add(new Sedan("Renault", 47000.0, "Fluence", 143, 430));
	}
	public static void cadastrarSuvs(List<Carro> carros) {
	    carros.add(new Suv("Toyota", 85000.0, "RAV4", 176, "AWD"));
	    carros.add(new Suv("Honda", 82000.0, "HR-V", 139, "FWD"));
	    carros.add(new Suv("Hyundai", 87000.0, "Creta", 155, "AWD"));
	    carros.add(new Suv("Chevrolet", 80000.0, "Tracker", 133, "FWD"));
	    carros.add(new Suv("Jeep", 90000.0, "Renegade", 185, "AWD"));
	    carros.add(new Suv("Nissan", 83000.0, "Kicks", 114, "FWD"));
	    carros.add(new Suv("Ford", 88000.0, "EcoSport", 137, "AWD"));
	}
	public static void cadastrarEsportes(List<Carro> carros) {
	    carros.add(new Esporte("Ferrari", 300000.0, "F8", 710, 340, 2.9));
	    carros.add(new Esporte("Porsche", 280000.0, "911", 450, 330, 3.2));
	    carros.add(new Esporte("Lamborghini", 320000.0, "Huracan", 640, 325, 3.1));
	    carros.add(new Esporte("Chevrolet", 250000.0, "Corvette", 495, 312, 3.4));
	    carros.add(new Esporte("BMW", 220000.0, "M4", 503, 290, 3.8));
	    carros.add(new Esporte("Audi", 230000.0, "R8", 602, 330, 3.3));
	    carros.add(new Esporte("Mercedes", 210000.0, "AMG GT", 523, 310, 3.5));
	}
}

//Aqui fica a aplicação principal com menu interativo
