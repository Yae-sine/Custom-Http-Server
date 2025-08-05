package org.HttpServer.http;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP= 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;


    public HttpRequest parseHttpParser(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream , StandardCharsets.US_ASCII);
        HttpRequest httpRequest = new HttpRequest();

        try {
            parseRequestLine(reader , httpRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseHeaders(reader , httpRequest);
        parseBody(reader , httpRequest);
        return httpRequest;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest httpRequest) throws IOException, HttpParsingException {
        StringBuilder processingDataBuffer = new StringBuilder();


        int _byte;

        boolean methodParsed = false;
        boolean requestTargetParsed = false;
        while( (_byte = reader.read()) >= 0){
            if(_byte==CR){
                _byte=reader.read();
                if(_byte == LF ){
                    LOGGER.debug("Request line VERSION to process : {}" , processingDataBuffer.toString());
                    if (!methodParsed || !requestTargetParsed){
                        throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                }
            }
            if (_byte == SP){
                if (!methodParsed){
                    methodParsed = true;
                    LOGGER.debug("Request line to METHOD process : {}" , processingDataBuffer.toString());
                    httpRequest.setMethod(processingDataBuffer.toString());
                }
                else if (!requestTargetParsed){
                    methodParsed = true;
                    LOGGER.debug("Request line to REQ TARGET process : {}" , processingDataBuffer.toString());

                }
                else{
                    throw new HttpParsingException(HttpStatusCodes.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processingDataBuffer.delete(0 , processingDataBuffer.length());
            }else{
                processingDataBuffer.append((char)_byte);
                if (!methodParsed){
                    if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH){
                        throw new HttpParsingException(HttpStatusCodes.SERVER_ERROR_501_NOT_IMPLEMENTED);
                    }
                }

            }
        }
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest httpRequest) {
    }

    private void parseBody(InputStreamReader reader, HttpRequest httpRequest) {
    }


}
