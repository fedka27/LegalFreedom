package legalFreedom.java.model.data.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TestException extends RuntimeException {
    public TestException(String errorMessage) {
        super(errorMessage);
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
    }
}
