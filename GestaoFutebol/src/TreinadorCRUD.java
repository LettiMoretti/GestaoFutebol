import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadorCRUD {

    public static Treinador salvarTreinador(Treinador salvarTreinador) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_treinadores (nome, experiencia, clube) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, salvarTreinador.getNome());
            ps.setInt(2, salvarTreinador.getExperiencia());
            ps.setInt(3, salvarTreinador.getClube().getIdClube());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                salvarTreinador.setIdTreinador(rs.getInt("idTreinador"));
                return salvarTreinador;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Treinador atualizarTreinador(Treinador atualizarTreinador, int idAtualizarTreinador) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE tb_treinadores SET nome = ?, experiencia = ?, clube = ? WHERE idTreinador = ?")) {
            ps.setString(1, atualizarTreinador.getNome());
            ps.setInt(2, atualizarTreinador.getExperiencia());
            ps.setInt(3, atualizarTreinador.getClube().getIdClube());
            ps.setInt(4, idAtualizarTreinador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Treinador(rs.getInt("idTreinador"),
                        rs.getString("nome"),
                        rs.getInt("experiencia"),
                        ClubeCRUD.buscarPorId(rs.getInt("idClube")));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Treinador buscarPorId (int idTreinadorBusca) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinadores WHERE idTreinador = ?")){
            ps.setInt(1, idTreinadorBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Treinador(rs.getInt("idTreinador"),
                        rs.getString("nome"),
                        rs.getInt("experiencia"),
                        ClubeCRUD.buscarPorId(rs.getInt("clube")));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Treinador buscarPorClube (int idClubeBusca) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinadores WHERE clube = ?")){
            ps.setInt(1, idClubeBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Treinador(rs.getInt("idTreinador"),
                        rs.getString("nome"),
                        rs.getInt("experiencia"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // Certo
    public static List<Treinador> buscarTodos () {
        List<Treinador> treinadores = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_treinadores")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                treinadores.add(new Treinador(rs.getInt("idTreinador"),
                        rs.getString("nome"),
                        rs.getInt("experiencia"),
                        ClubeCRUD.buscarPorId(rs.getInt("clube"))));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return treinadores;
    }

    public static void excluirPorId (int idTreinadorExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_treinadores WHERE idTreinador = ?")){
            ps.setInt(1, idTreinadorExclusao);
            ps.execute();
            System.out.println("Treinador deletado com sucesso.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void excluirPorClube (int idClubeExcusao) {
        try (Connection connection = Conexao.getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_treinadores WHERE clube = ?")) {
            ps.setInt(1, idClubeExcusao);
            ps.execute();
            System.out.println("Treinador excluido com sucesso.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
