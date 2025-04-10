import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubeCRUD {

    public static Clube adicionarClube(Clube adicionarClube) {
        try (Connection connection = Conexao.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_clubes (nome, dataFundacao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, adicionarClube.getNome());
            ps.setString(2, adicionarClube.getDataFundacao());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                adicionarClube.setIdClube(rs.getInt("id"));
                return adicionarClube;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Clube atualizarClube(Clube atualizarClube, int idAtualizarClube) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE tb_clubes SET nome = ?, dataFundacao = ? WHERE idClube = ?")) {
            ps.setString(1, atualizarClube.getNome());
            ps.setString(2, atualizarClube.getDataFundacao());
            ps.setInt(3, idAtualizarClube);
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return new Clube(rs.getInt("id"),
                        rs.getString(2),
                        rs.getString(3),
                        TreinadorCRUD.buscarPorClube(rs.getInt("idClube")),
                        JogadorCRUD.buscarPorClube(rs.getInt("idClube")),
                        ClubeLigaCRUD.buscarPorClube( rs.getInt("idClube")));
            }
        } catch (SQLException e) {
            System.out.println("Da classe de clube");
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static Clube buscarPorId (int idClubeBusca) {
        try (Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clubes WHERE idClube = ?")){
            ps.setInt(1, idClubeBusca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                try {

                    return new Clube(rs.getInt("idClube"),
                            rs.getString("nome"),
                            rs.getString("dataFundacao"));
                            /*TreinadorCRUD.buscarPorId(rs.getInt("treinador")),
                            JogadorCRUD.buscarPorClube(rs.getInt("idClube")),
                            ClubeLigaCRUD.buscarPorClube( rs.getInt("idClube")));*/

                } catch ( Exception e ){

                    return new Clube(rs.getInt("idClube"),
                            rs.getString("nome"),
                            rs.getString("dataFundacao"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // Certo
    public static List<Clube> buscarTodos () {
        List<Clube> clubes = new ArrayList<>();
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM tb_clubes")){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clubes.add(new Clube(rs.getInt("idClube"),
                        rs.getString("nome"),
                        rs.getString("dataFundacao"),
                        TreinadorCRUD.buscarPorId(rs.getInt("treinador")),
                        JogadorCRUD.buscarPorClube(rs.getInt("idClube")),
                        ClubeLigaCRUD.buscarPorClube( rs.getInt("idClube"))));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return clubes;
    }


    public static void excluirPorId (int idClubeExclusao) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM tb_clubes WHERE idClube = ?")){
            JogadorCRUD.excluirPorClube(idClubeExclusao);
            TreinadorCRUD.excluirPorClube(idClubeExclusao);
            ClubeLigaCRUD.excluirPorClube(idClubeExclusao);
            ps.setInt(1, idClubeExclusao);
            ps.execute();
            System.out.println("Clube deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}