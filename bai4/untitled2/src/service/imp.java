package service;
import entity.entity.Phim;
import util.MySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class imp implements CategoryService{
    @Override
    public List<Phim> findAll() {
        List<Phim> result = new ArrayList<>();
        Connection con = null;
        try {
            con = MySQL.open();
            String query = "SELECT * FROM phim";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("PhimID");
                String name = rs.getString("Ten_Phim");
                String category = rs.getString("Loai_Phim");
                int time = rs.getInt("Thoi_Gian");
                result.add(new Phim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MySQL.close(con);
        }
        return result;
    }

    @Override
    public Phim insert(Phim phim) {
        try {
            Connection con = MySQL.open();
            String query = "INSERT INTO phim (PhimID,Ten_Phim, Loai_Phim, Thoi_Gian) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, phim.getId());
            ps.setString(2, phim.getName());
            ps.setString(3, phim.getCategory());
            ps.setInt(4, phim.getTime());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }

    @Override
    public Phim delete(Phim phim) {
        try {
            Connection con = MySQL.open();
            String query = "DELETE FROM phim WHERE PhimID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, phim.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }

    @Override
    public Phim update(Phim phim) {
        try {
            Connection con = MySQL.open();
            String query = "UPDATE phim SET Ten_Phim = ?, Loai_Phim = ?, Thoi_Gian = ? WHERE PhimID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phim.getName());
            ps.setString(2, phim.getCategory());
            ps.setInt(3, phim.getTime());
            ps.setInt(4, phim.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
    }

    @Override
    public Phim findById(int id) {
        try {
            Connection con = MySQL.open();
            String query = "SELECT * FROM phim WHERE PhimID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Ten_Phim");
                String category = rs.getString("Loai_Phim");
                int time = rs.getInt("Thoi_Gian");
                return new Phim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
