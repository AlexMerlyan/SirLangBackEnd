package com.rest.sirlang.service;

import com.sirlang.Executor;
import com.sirlang.java.executor.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@Service
public class CompileServiceImpl implements CompileService {

    private static final String FILE_DELETE_LOG_TEMPLATE = "File {} was deleted: {}";

    private final Executor executor = Executor.getInstance();

    private File createSourceFile(final String fileName, final String content) throws IOException {
        final File sourceFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sourceFile))) {
            bufferedWriter.write(content);
        }
        return sourceFile;
    }

    @Override
    public ExecutionResult compile(String sourceCode) throws IOException, InterruptedException {
        final String fileName = System.currentTimeMillis() + ".sir";
        final File sourceFile = createSourceFile(fileName, sourceCode);
        ExecutionResult result = executor.execute(fileName);
        deleteExecutedFiles(sourceFile);
        return result;
    }

    private void deleteExecutedFiles(final File sirlangSourceFile) {
        boolean isFileDeleted = sirlangSourceFile.exists() && sirlangSourceFile.delete();
        log.info(FILE_DELETE_LOG_TEMPLATE, sirlangSourceFile.getName(), isFileDeleted);
        File byteCodeFile = new File("Main.class");
        isFileDeleted = byteCodeFile.exists() && byteCodeFile.delete();
        log.info(FILE_DELETE_LOG_TEMPLATE, sirlangSourceFile.getName(), isFileDeleted);
        File javaFile = new File("Main.java");
        isFileDeleted = javaFile.exists() && javaFile.delete();
        log.info(FILE_DELETE_LOG_TEMPLATE, javaFile.getName(), isFileDeleted);
    }

}
