public class Categoria {
    private String nome;
    private double valor;
    private Categoria[] filhos;
    private int numFilhos;

    public Categoria(String nome) {
        this.nome = nome;
        this.valor = 0;
        this.filhos = new Categoria[10];
        this.numFilhos = 0;
    }

    public void adicionarFilho(String nomeRamo, double valorRamo) {
        if (numFilhos < filhos.length) {
            Categoria ramo = new Categoria(nomeRamo);
            ramo.setValor(valorRamo);
            filhos[numFilhos++] = ramo;
            atualizarValor();
        } else {
            System.out.println("Limite de ramos atingido para esta categoria.");
        }
    }

    public void atualizarValor() {
        this.valor = 0;
        for (int i = 0; i < numFilhos; i++) {
            this.valor += filhos[i].getValor();
        }
    }

    public boolean removerFilho(String nomeRamo) {
        for (int i = 0; i < numFilhos; i++) {
            if (filhos[i].getNome().equalsIgnoreCase(nomeRamo)) {
                filhos[i] = filhos[--numFilhos];
                filhos[numFilhos] = null;
                atualizarValor();
                return true;
            }
        }
        return false;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria[] getFilhos() {
        return filhos;
    }

    public String getNome() {
        return nome;
    }
}
