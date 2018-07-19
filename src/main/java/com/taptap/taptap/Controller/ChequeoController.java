package com.taptap.taptap.Controller;

import com.taptap.taptap.Service.ChequeoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chequeo")
public class ChequeoController {
    @Autowired
    ChequeoServices chequeoServices;


}
