package pe.com.upeu.registro.test;
import com.google.gson.Gson;
import pe.com.upeu.registro.dao.AlumnoDao;
import pe.com.upeu.registro.dao.EscuelaDao;
import pe.com.upeu.registro.daoImp.AlumnoDaoImp;
import pe.com.upeu.registro.daoImp.EscuelaDaoImp;
import pe.com.upeu.registro.util.Conexion;
public class Test {
	private static AlumnoDao pd = new AlumnoDaoImp();
    private static Gson g = new Gson();
    private static EscuelaDao escu = new EscuelaDaoImp();
    private static AlumnoDao alum = new AlumnoDaoImp();
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //listarP();
        conex();
        listares();
        listaralu();
    }
    static void listarP() {
          System.out.println(g.toJson(pd.readAll()));
     }
     static void conex() {
          if(Conexion.getConexion() != null) {
              System.out.println("Conectado");
          }else {
              System.out.println("Desconectado");
          }


      }
     static void listares() {
    	 System.out.println(g.toJson(escu.readAll()));
    	 
    	 
     }
     static void listaralu() {
    	 System.out.println(g.toJson(alum.readAll()));
     }
     
}