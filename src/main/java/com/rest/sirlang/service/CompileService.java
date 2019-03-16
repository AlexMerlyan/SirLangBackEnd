package com.rest.sirlang.service;

import com.sirlang.java.executor.ExecutionResult;

import java.io.IOException;

public interface CompileService {

    ExecutionResult compile(final String sourceCode) throws IOException, InterruptedException;

}
