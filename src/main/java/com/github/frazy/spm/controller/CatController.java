/**
 *
 */
package com.github.frazy.spm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.frazy.spm.annotation.ApiZone;
import com.github.frazy.spm.enums.ZoneEnum;

/**
 * Cat.
 *
 * @author hzyinglei
 */
@ApiZone(ZoneEnum.CAT)
@RestController
public class CatController {
    private static final Logger log = LoggerFactory.getLogger(CatController.class);

    @GetMapping("/cats")
    public void getCats() {
        log.info("/cats");
    }

    @ApiZone({ ZoneEnum.CAT, ZoneEnum.CODER })
    @GetMapping("/say/miao/to/dog")
    public void say() {
        log.info("/say/miao/to/dog");
    }

}
