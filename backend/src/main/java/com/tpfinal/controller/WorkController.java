package com.tpfinal.controller;

import com.tpfinal.domain.Work;
import com.tpfinal.dto.WorkDTO;
import com.tpfinal.service.UserService;
import com.tpfinal.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(WorkController.class);

    //Busqueda principal por nombre
    @RequestMapping(method = RequestMethod.GET, value = "/named/{workTyped}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Work> getWorks(@PathVariable String workTyped){
        logger.info("Busqueda principal");
        return workService.findByNameContaining(workTyped);
    }

    //Busqueda por categoria
    @RequestMapping(method = RequestMethod.GET, value = {"/category/{idCategory}"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Work> getWorksByCategory(@PathVariable Long idCategory){
        return workService.findByCategory(idCategory);
    }

    //Busqueda por ciudades
    @RequestMapping(method = RequestMethod.GET, value = {"/district/{idDistrict}"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Work> getWorksByAddress(@PathVariable Long idDistrict){
        return workService.findByDistrict(idDistrict);
    }

    @RequestMapping(method= RequestMethod.POST, value = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody WorkDTO workDTO ){
        logger.info("Creando trabajo: " + workDTO.getName());
        workService.createFromDTO(workDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateProfile(@RequestBody WorkDTO workDTO){
        logger.info("Actualizando work");
        workService.updateWork(workDTO);

    }
}
