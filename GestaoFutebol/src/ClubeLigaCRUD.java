import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubeLigaCRUD {

    public static void adicionarClubeLiga(int idClubeAdicionar, int idLigaAdicionar) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_clubeliga (idClube, idliga) VALUES (?, ?)")) {
            ps.setInt(1, idClubeAdicionar);
            ps.setInt(2, idLigaAdicionar);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static List<Clube> buscarPorLiga (int idLigaBusca) {
        List<Clube> clubes = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clubeliga WHERE clube = ?")){
            ps.setInt(1, idLigaBusca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clubes.add(ClubeCRUD.buscarPorId(rs.getInt("idClube")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return clubes;
    }

    public static List<Liga> buscarPorClube (int idClubeBusca) {
        List<Liga> ligas = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clubeliga WHERE idLiga = ?")){
            ps.setInt(1, idClubeBusca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ligas.add( LigaCRUD.buscarPorId( rs.getInt("idLiga")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ligas;
    }

    public static void excluirPorClube (int idClubeExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clubeliga WHERE idClube = ?")){
            ps.setInt(1, idClubeExclusao);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void excluirPorLiga (int idLigaExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clubeliga WHERE idLiga = ?")){
            ps.setInt(1, idLigaExclusao);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}