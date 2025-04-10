import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigaCRUD {

    public static Liga salvarLiga(Liga salvarLiga) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_ligas (nome, anoFundacao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, salvarLiga.getNome());
            ps.setInt(2, salvarLiga.getAnoFundacao());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                salvarLiga.setIdLiga(rs.getInt("idLiga"));
                return salvarLiga;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Liga atualizarLiga(Liga atualizarLiga, int idAtualizarLiga) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE tb_ligas SET nome = ?, anoFundacao = ? WHERE idLiga = ?")) {
            ps.setString(1, atualizarLiga.getNome());
            ps.setInt(2, atualizarLiga.getAnoFundacao());
            ps.setInt(3, idAtualizarLiga);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Liga(rs.getInt("idLiga"),
                        rs.getString("nome"),
                        rs.getInt("anoFundacao"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Liga buscarPorId (int idLigaBusca) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_ligas WHERE idLiga = ?")){
            ps.setInt(1, idLigaBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Liga(rs.getInt("idLiga"),
                        rs.getString("nome"),
                        rs.getInt("anoFundacao"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static List<Liga> buscarTodos () {
        List<Liga> ligas = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_ligas")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ligas.add(new Liga(rs.getInt("idLiga"),
                        rs.getString("nome"),
                        rs.getInt("anoFundacao")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ligas;
    }

    public static void excluirPorId (int idLigaExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_ligas WHERE idLiga = ?")){
            ClubeLigaCRUD.excluirPorLiga(idLigaExclusao); //Certo
            ps.setInt(1, idLigaExclusao);
            ps.execute();
            System.out.println("Liga deletada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}