import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClubeCRUD {

    public static void adicionarClube(Clube adicionarClube) {
        try (Connection connection = Conexao.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO tb_clubes (nome, dataFundacao) VALUES (?, ?)")) {
            ps.setString(1, adicionarClube.getNome());
            ps.setString(2, adicionarClube.getDataFundacao());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar clube", e);
        }
    }

    public static void atualizarClube(Clube atualizarClube, int idAtualizarClube) {
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE tb_clubes SET nome = ?, dataFundacao = ? WHERE idClube = ?")) {
            ps.setString(1, atualizarClube.getNome());
            ps.setString(2, atualizarClube.getDataFundacao());
            ps.setInt(3, idAtualizarClube);
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar clube", e);
        }
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
        } catch (Exception e) {
            throw new NoSuchElementException("Clube nao encontrado");
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
                        rs.getString("dataFundacao")));
                        /*TreinadorCRUD.buscarPorId(rs.getInt("treinador")),
                        JogadorCRUD.buscarPorClube(rs.getInt("idClube")),
                        ClubeLigaCRUD.buscarPorClube( rs.getInt("idClube"))));*/
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
            /*JogadorCRUD.excluirPorClube(idClubeExclusao);
            TreinadorCRUD.excluirPorClube(idClubeExclusao);
            ClubeLigaCRUD.excluirPorClube(idClubeExclusao);*/
            ps.setInt(1, idClubeExclusao);
            int linhasAfetadas = ps.executeUpdate();

            if ( linhasAfetadas == 0 ) {
                throw new NoSuchElementException("Clube nao encontrado");
            }
            System.out.println("Clube deletado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir clube", e);
        }

    }
}