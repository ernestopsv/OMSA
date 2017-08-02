package com.proyectoFinal.OMSA;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import com.proyectoFinal.OMSA.Services.AutobusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 04/07/17.
 */
@Controller()
@RequestMapping("/autobus")
public class AutobusController {
    @Autowired
    private AutobusServices autobusServices;

    @RequestMapping("/")
    public String index(Model model){
        List<Autobus> autobuses = autobusServices.buscarTodoLosAutobus();
        model.addAttribute("autobuses", autobuses);
        return "";
    }

    /**
     *
     * Modificar un autobus
     * @param id
     * @return
     */
    @RequestMapping("/editar")
    public String editarAutobus(Model model, @RequestParam("id")Long id){
        Autobus autobus =autobusServices.buscarUnAutobus(id);
        model.addAttribute(autobus);
        return "/editar";
    }

    @PostMapping("/editar")
    public String guardarAutobusEditado(@ModelAttribute Autobus autobus){
        autobusServices.guardarAutobus(autobus);
        return "redirect:/autobus/";
    }

    @RequestMapping("/crear")
    public String crearAutobus(Model model){
        model.addAttribute("autobus", new Autobus());
        return "/crear";
    }
    @PostMapping("/crear")
    @Transactional
    public String guardarAutobusCreado(@ModelAttribute Autobus autobus){
        autobusServices.guardarAutobus(autobus);
        return "redirect:/autobus/";
    }
    /**
     *
     * Eliminar un autobus
     * @param id
     * @return
     */
    @RequestMapping(value = "/eliminar")
    public String eliminarAutobus(@RequestParam("id") Long id){
        Autobus autobus = autobusServices.buscarUnAutobus(id);
        autobusServices.eliminarAutobusporId(id);
        return "redirect:/autobus/";
    }
}
