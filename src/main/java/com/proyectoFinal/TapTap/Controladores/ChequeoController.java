package com.proyectoFinal.TapTap.Controladores;

import com.proyectoFinal.TapTap.Services.ChequeoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chequeo")
public class ChequeoController {
    @Autowired
    ChequeoServices chequeoServices;


}
