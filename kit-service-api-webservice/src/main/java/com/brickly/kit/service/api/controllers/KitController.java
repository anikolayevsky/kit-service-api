package com.brickly.kit.service.api.controllers;

import com.brickly.kit.service.api.domain.Kit;
import com.brickly.kit.service.api.domain.KitOption;
import com.brickly.kit.service.api.domain.KitOptionItems;
import com.brickly.kit.service.api.model.KitDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brickly.kit.service.api.services.KitService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
@RestController
@RequestMapping(value = "/kit")
@Api(value = "Kit Service", description = "Kit REST Service")
public class KitController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected KitService kitService;

    @Autowired
    protected ObjectMapper objectMapper;


    @ApiOperation(value = "saveKit", nickname = "saveKit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = KitDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public KitDTO saveKit(@RequestBody KitDTO dto) {
        logger.info("Creating new kit");
        Kit kit = objectMapper.convertValue(dto, Kit.class);
        _setKitChildren(kit);
        kitService.save(kit);
        logger.info("Successfully created new kit with id {}", kit.getId());
        return objectMapper.convertValue(kit, KitDTO.class);
    }

    @ApiOperation(value = "getKit", nickname = "getKit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = KitDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public KitDTO getKit(@PathVariable Integer id) {
        logger.info("Finding kit with id {}.", id);
        Kit kit = kitService.findOne(id);
        if(kit != null && kit.getId() != null) {
            logger.info("Successfully found kit with id {}", kit.getId());
        }
        return objectMapper.convertValue(kit, KitDTO.class);
    }

    @ApiOperation(value = "findKits", nickname = "findKits")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = KitDTO[].class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public KitDTO[] findKits(@RequestParam(required = false) String sku) {
        //Make pageable
        Kit[] kits = null;
        if(sku == null) {
            logger.info("Finding all kits");
            kits = kitService.findAll();
        } else {
            logger.info("Finding all kits with sku {}", sku);
            kits = kitService.findBySku(sku);
        }
        return objectMapper.convertValue(kits, KitDTO[].class);
    }

    @ApiOperation(value = "updateKit", nickname = "updateKit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = KitDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public KitDTO updateKit(@PathVariable Integer id, @RequestBody KitDTO dto) throws Exception {
        logger.info("Updating kit with id {}", id);
        if(!id.equals(dto.getId())) {
            logger.warn("Id " + id + " does not match the requested id of " + dto.getId());
            throw new Exception("Id " + id + " does not match the requested id of " + dto.getId());
        }
        Kit kit = kitService.findOne(id);
        if (kit == null) {
            throw new EntityNotFoundException("Unable to find kit with id: " + id);
        }

        kit = objectMapper.convertValue(dto, Kit.class);
        _setKitChildren(kit);
        kit = kitService.save(kit);
        logger.info("Successfully updated new kit with id {}", kit.getId());
        return objectMapper.convertValue(kit, KitDTO.class);
    }


    protected void _setKitChildren(Kit kit) {
        for(KitOption kitOption : kit.getKitOptions()) {
            kitOption.setKit(kit);
            for(KitOptionItems kitOptionItem : kitOption.getKitOptionItems()) {
                kitOptionItem.setKitOption(kitOption);
            }
        }
    }

}
