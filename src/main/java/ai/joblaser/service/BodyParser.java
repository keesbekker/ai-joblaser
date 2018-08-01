package ai.joblaser.service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class BodyParser {

    public String getBody(HttpServletRequest req) throws IOException {
        String body = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        bufferedReader = req.getReader();
        char[] charBuffer = new char[128];
        int bytesRead;
        while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
            sb.append(charBuffer, 0, bytesRead);
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        body = sb.toString();
        return body;
    }

}
