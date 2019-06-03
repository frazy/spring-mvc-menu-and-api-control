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
 * Login.
 *
 * @author hzyinglei
 */
@ApiZone(ZoneEnum.OPEN)
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public void login() {
        log.info("/login");
    }

}
