import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o modo de execução:");
        System.out.println("1. Console");
        System.out.println("2. Interface Gráfica (Swing)");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();

        if (opcao == 1) {
            executarConsole();
        } else if (opcao == 2) {
            InterfaceSwing.main(null);
        } else {
            System.out.println("Opção inválida! Saindo...");
        }

        scanner.close();
    }

    private static void executarConsole() {
        Planilha planilha = new Planilha();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Ano");
            System.out.println("2. Adicionar Categoria");
            System.out.println("3. Adicionar Ramo (Subcategoria)");
            System.out.println("4. Remover Ano");
            System.out.println("5. Remover Categoria");
            System.out.println("6. Remover Ramo (Subcategoria)");
            System.out.println("7. Exibir Planilha");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    planilha.adicionarAno(ano);
                    break;
                case 2:
                    planilha.adicionarCategoriaAoAno();
                    break;
                case 3:
                    planilha.adicionarRamo();
                    break;
                case 4:
                    System.out.print("Digite o ano a remover: ");
                    int anoRemover = scanner.nextInt();
                    if (planilha.removerAno(anoRemover)) {
                        System.out.println("Ano removido com sucesso!");
                    } else {
                        System.out.println("Ano não encontrado!");
                    }
                    break;
                case 5:
                    planilha.removerCategoriaDeAno();
                    break;
                case 6:
                    planilha.removerRamoDeCategoria();
                    break;
                case 7:
                    planilha.exibirPlanilha();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
