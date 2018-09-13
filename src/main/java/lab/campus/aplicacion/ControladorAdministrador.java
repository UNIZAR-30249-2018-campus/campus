package lab.campus.aplicacion;

import com.google.gson.Gson;
import lab.campus.infraestructura.RepositorioAdministrador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
public class ControladorAdministrador {

    @Autowired
    RepositorioAdministrador repositorioAdministrador;

    Logger log = LoggerFactory.getLogger(ControladorAdministrador.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void accesoAdministrador(@RequestParam String username, @RequestParam String password,
                                      HttpServletResponse response,
                                      HttpSession session, RedirectAttributes redirectAttrs) {

        System.out.println("Aqui llego" + username + password);
        Optional<CuentaAdministrador> administrador = repositorioAdministrador.findByUsuario(username);
        System.out.println(administrador.toString());
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : byteData) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        String onepass = hexString.toString();
        System.out.println(onepass);
        if (administrador.isPresent() && administrador.get().loginCorrecto(onepass)) {
            session.setAttribute("ADMIN", administrador.get());
            try {
                response.sendRedirect("admin.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public  @ResponseBody
    String logoutAdministrador(HttpSession session) {
        System.out.println("Perooo");
        Gson gson = new Gson();
        if (session.getAttribute("ADMIN") != null) {
            session.removeAttribute("ADMIN");
            System.out.println("1");
            return gson.toJson("index.html");
        } else {
            System.out.println("2");
            return gson.toJson("index.html");
        }
    }

}