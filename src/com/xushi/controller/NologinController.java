package com.xushi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/nologin/*")
public class NologinController extends BaseController {

}
