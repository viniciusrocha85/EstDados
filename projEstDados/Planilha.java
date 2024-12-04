public class Planilha {
    private Ano[] anos;
    private int numAnos;

    public Planilha() {
        this.anos = new Ano[10];
        this.numAnos = 0;
    }

    public void adicionarAno(int ano) {
        if (buscarAno(ano) == null) {
            if (numAnos < anos.length) {
                anos[numAnos++] = new Ano(ano);
            } else {
                System.out.println("Limite de anos atingido.");
            }
        } else {
            System.out.println("Ano já existe.");
        }
    }

    public boolean removerAno(int ano) {
        Ano anoEncontrado = buscarAno(ano);
        if (anoEncontrado != null) {
            for (int i = 0; i < numAnos; i++) {
                if (anos[i].getAno() == ano) {
                    anos[i] = anos[--numAnos];
                    anos[numAnos] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public Ano buscarAno(int ano) {
        for (int i = 0; i < numAnos; i++) {
            if (anos[i].getAno() == ano) {
                return anos[i];
            }
        }
        return null;
    }

    public Categoria buscarCategoria(Ano ano, String nomeCategoria) {
        if (ano != null) {
            for (Categoria c : ano.getCategorias()) {
                if (c != null && c.getNome().equalsIgnoreCase(nomeCategoria)) {
                    return c;
                }
            }
        }
        return null;
    }

    public boolean removerCategoria(Ano ano, String nomeCategoria) {
        return ano.removerCategoria(nomeCategoria);
    }

    public int getNumAnos() {
        return numAnos;
    }

    public Ano getAno(int index) {
        if (index >= 0 && index < anos.length) {
            return anos[index];
        }
        return null;
    }
}