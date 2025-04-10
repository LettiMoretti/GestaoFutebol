import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorCRUD {

    public static Jogador salvarJogador(Jogador salvarJogador) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_jogadores (nome, idade, posicao, idClube) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, salvarJogador.getNome());
            ps.setInt(2, salvarJogador.getIdade());
            ps.setString(3, salvarJogador.getPosicao());
            ps.setInt(4, salvarJogador.getClube().getIdClube());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                salvarJogador.setIdJogador(rs.getInt("idJogador"));
                return salvarJogador;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Jogador atualizarJogador(Jogador atualizarJogador, int idAtualizarJogador) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE tb_jogadores SET nome = ?, idade = ?, posicao = ?, idClube = ? WHERE idJogador = ?")) {
            ps.setString(1, atualizarJogador.getNome());
            ps.setInt(2, atualizarJogador.getIdade());
            ps.setString(3, atualizarJogador.getPosicao());
            ps.setInt(4, atualizarJogador.getClube().getIdClube());
            ps.setInt(5, idAtualizarJogador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Jogador(rs.getInt("idJogador"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        ClubeCRUD.buscarPorId(rs.getInt("idClube")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Jogador buscarPorId (int idJogadorBusca) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogadores WHERE idJogador = ?")){
            ps.setInt(1, idJogadorBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Jogador(rs.getInt("idJogador"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        ClubeCRUD.buscarPorId(rs.getInt("idClube")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static List<Jogador> buscarPorClube (int idClubeBusca) {
        List<Jogador> jogadoresDoClube = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogadores WHERE clube = ?")){
            ps.setInt(1, idClubeBusca); // é idClube, não clube
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jogadoresDoClube.add(new Jogador(rs.getInt("idJogador"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        ClubeCRUD.buscarPorId(rs.getInt("idClube"))));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jogadoresDoClube;
    }

    public static List<Jogador> buscarTodos () {
        List<Jogador> jogadores = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_jogadores")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jogadores.add(new Jogador(rs.getInt("idJogador"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("posicao"),
                        ClubeCRUD.buscarPorId(rs.getInt("clube"))));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jogadores;
    }

    public static void excluirPorId (int idJogadorExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_jogadores WHERE idJogador = ?")){
            ps.setInt(1, idJogadorExclusao);
            ps.execute();
            System.out.println("Jogador deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void excluirPorClube (int idClubeExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_jogadores WHERE clube = ?")){
            ps.setInt(1, idClubeExclusao);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
