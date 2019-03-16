package com.rest.sirlang.controller;

import com.rest.sirlang.dto.CompileRequestDto;
import com.rest.sirlang.dto.CompileResponseDto;
import com.rest.sirlang.service.CompileService;
import com.sirlang.java.executor.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/compile")
public class CompileController {

    @Autowired
    private CompileService compileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CompileResponseDto> compileSirlangCode(@RequestBody CompileRequestDto dto) throws IOException, InterruptedException {
        log.info(dto.getSirlangCode());
        final ExecutionResult executionResult = compileService.compile(dto.getSirlangCode());
        return new ResponseEntity<>(new CompileResponseDto(executionResult.getConsoleOutput(), true), HttpStatus.OK);
    }

}
