public class Jogador {

    private int idJogador;
    private String nome;
    private int idade;
    private String posicao;
    private Clube clube;

    public Jogador() {
    }

    public Jogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public Jogador(String nome, int idade, String posicao, Clube clube) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.clube = clube;
    }

    public Jogador(int idJogador, String nome, int idade, String posicao, Clube clube) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.clube = clube;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "idJogador=" + idJogador +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", posicao='" + posicao + '\'' +
                ", clube=" + clube.getIdClube() +
                '}';
    }
}