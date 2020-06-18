package pe.com.upeu.registro.daoImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pe.com.upeu.registro.dao.AlumnoDao;
import pe.com.upeu.registro.entity.Alumno;
import pe.com.upeu.registro.util.Conexion;
public class AlumnoDaoImp implements AlumnoDao{
	private PreparedStatement ps;
    private ResultSet rs;
    private Connection cx = null;
	@Override
	public int create(Alumno u) {
		 int x = 0;
		 String sql = "INSERT INTO alumno(idalumno,idescuela,nombres,correo,celular) VALUES(NULL,?,?,?,?)";
		 try {
			 
			 cx = Conexion.getConexion();		 
			 ps = cx.prepareStatement(sql);
			 ps.setInt(1, u.getIdescuela());
			 ps.setString(2, u.getNombres());
			 ps.setString(3, u.getCorreo());
			 ps.setString(4, u.getCelular());	 
			 x = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		 
		 return x;
	}
	@Override
	public int update(Alumno u) {
		// TODO Auto-generated method stub
		int x = 0;
        String sql = "UPDATE alumno SET idescuela = ?, nombres = ?, correo = ?, celular = ? WHERE idalumno = ?";
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(sql);
            ps.setInt(1, u.getIdescuela());
            ps.setString(2, u.getNombres());
            ps.setString(3, u.getCorreo());
            ps.setString(4, u.getCelular());
            ps.setInt(5, u.getIdalumno());
            x = ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return x;
	}
	@Override
	public int delete(int id) {
		int x = 0;
        // TODO Auto-generated method stub
        String sql= "DELETE FROM alumno WHERE idalumno= ?";
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
	}
	@Override
	public List<Map<String, Object>> read(int id) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = new ArrayList<>();
        String sql = "select e.idescuela, e.nombre, "+
                     "a.idalumno, a.nombres, "+ 
                     "a.correo, a.celular from escuela as e,"+
                     " alumno as a where e.idescuela = a.idescuela and a.idalumno=?";
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("idescuela", rs.getInt("idescuela"));
                map.put("nombre", rs.getString("nombre"));
                map.put("idalumno", rs.getInt("idalumno"));
                map.put("nombres", rs.getString("nombres"));
                map.put("correo", rs.getString("correo"));
                map.put("celular", rs.getString("celular"));
                list.add(map);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
	}
	@Override
	public List<Map<String, Object>> readAll() {
		// TODO Auto-generated method stub
        List<Map<String,Object>> list = new ArrayList<>();
        String sql = "select e.idescuela, e.nombre, "+
                     "a.idalumno, a.nombres, "+ 
                     "a.correo, a.celular from alumno as a,"+
                     "escuela as e where e.idescuela = a.idescuela";
        try {
            cx = Conexion.getConexion();
            ps = cx.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("idescuela", rs.getInt("idescuela"));
                map.put("nombre", rs.getString("nombre"));
                map.put("idalumno", rs.getInt("idalumno"));
                map.put("nombres", rs.getString("nombres"));
                map.put("correo", rs.getString("correo"));
                map.put("celular", rs.getString("celular"));
                list.add(map);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
	}
}