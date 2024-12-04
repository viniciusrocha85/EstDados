public class Ano {
    private int ano;
    private Categoria[] categorias;
    private int numCategorias;


    public Ano(int ano) {
        this.ano = ano;
        this.categorias = new Categoria[10];
        this.numCategorias = 0;
    }

    public void adicionarCategoria(Categoria categoria) {
        if (numCategorias < categorias.length) {
            categorias[numCategorias++] = categoria;
        } else {
            System.out.println("Limite de categorias atingido para este ano.");
        }
    }

    public boolean removerCategoria(String nomeCategoria) {
        for (int i = 0; i < numCategorias; i++) {
            if (categorias[i].getNome().equalsIgnoreCase(nomeCategoria)) {
                categorias[i] = categorias[--numCategorias];
                categorias[numCategorias] = null;
                return true;
            }
        }
        return false;
    }

    public Categoria buscarCategoria(String nomeCategoria) {
        for (int i = 0; i < numCategorias; i++) {
            if (categorias[i].getNome().equalsIgnoreCase(nomeCategoria)) {
                return categorias[i];
            }
        }
        return null;
    }

    public int getAno() {
        return ano;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }
}