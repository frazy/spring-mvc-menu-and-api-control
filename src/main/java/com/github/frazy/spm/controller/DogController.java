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
 * Dog.
 *
 * @author hzyinglei
 */
@ApiZone(ZoneEnum.DOG)
@RestController
public class DogController {
    private static final Logger log = LoggerFactory.getLogger(DogController.class);

    @GetMapping("/dogs")
    public void getCats() {
        log.info("/dogs");
    }

    @ApiZone(ZoneEnum.LOGIN)
    @GetMapping("/dog/bite")
    public void bite() {
        log.info("/dog/bite");
    }

}
