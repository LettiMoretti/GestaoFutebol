public class Treinador {

    private int idTreinador;
    private String nome;
    private int experiencia;
    private Clube clube;

    public Treinador() {
    }

    public Treinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public Treinador(int idTreinador, String nome, int experiencia, Clube clube) {
        this.idTreinador = idTreinador;
        this.nome = nome;
        this.experiencia = experiencia;
        this.clube = clube;
    }

    public Treinador(int idTreinador, String nome, int experiencia) {
        this.idTreinador = idTreinador;
        this.nome = nome;
        this.experiencia = experiencia;
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "idTreinador=" + idTreinador +
                ", nome='" + nome + '\'' +
                ", experiencia=" + experiencia +
                ", clube=" + clube.getIdClube() +
                '}';
    }
}
