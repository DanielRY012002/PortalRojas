package pe.com.upeu.registro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import pe.com.upeu.registro.dao.AlumnoDao;
import pe.com.upeu.registro.dao.EscuelaDao;
import pe.com.upeu.registro.daoImp.AlumnoDaoImp;
import pe.com.upeu.registro.daoImp.EscuelaDaoImp;
import pe.com.upeu.registro.entity.Alumno;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/hc")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlumnoDao ad1 = new AlumnoDaoImp();
	private EscuelaDao ed = new EscuelaDaoImp();
	private Gson g = new Gson();
	int ida, idescuela;
	String nombres, correo, celular;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int op = Integer.parseInt(request.getParameter("opc"));
		switch (op) {
		case 1:
			out.print(g.toJson(ed.readAll()));
			break;
		case 2:
			out.print(g.toJson(ad1.readAll()));
			break;
		case 3: // registrar alumno
			ad1.create(new Alumno(0, Integer.parseInt(request.getParameter("escuela")), request.getParameter("nombres"),
					request.getParameter("correo"), request.getParameter("celular")));
			out.println(g.toJson("Registro guardado correctamente"));
			break;
		case 4: // Buscar produto por ID
			out.println(g.toJson(ad1.read(Integer.parseInt(request.getParameter("id")))));
			break;
		case 5:int x = ad1.delete(Integer.parseInt(request.getParameter("id")));
        out.println(g.toJson(x));
			break;
		case 6:
			ida = Integer.parseInt(request.getParameter("ida"));
            idescuela = Integer.parseInt(request.getParameter("idescuela"));
            nombres = request.getParameter("nombres");
            correo = request.getParameter("correo");
            celular = request.getParameter("celular");
            out.println(g.toJson(ad1.update(new Alumno(ida, idescuela, nombres, correo, celular))));
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
