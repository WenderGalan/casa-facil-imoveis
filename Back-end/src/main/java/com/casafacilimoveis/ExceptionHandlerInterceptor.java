package com.casafacilimoveis;

import com.casafacilimoveis.model.entities.interceptor.Crashlytics;
import com.casafacilimoveis.model.entities.interceptor.Header;
import com.casafacilimoveis.repository.CrashlyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ExceptionHandlerInterceptor.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@ControllerAdvice
public class ExceptionHandlerInterceptor extends ResponseEntityExceptionHandler {

    @Autowired
    private CrashlyticsRepository crashlyticsRepository;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> getException(Exception ex, WebRequest request) {
        Crashlytics crashlytics = new Crashlytics();

        crashlytics.setClassError(ex.getStackTrace()[0].getClassName());
        crashlytics.setMethodName(ex.getStackTrace()[0].getMethodName());
        crashlytics.setFileName(ex.getStackTrace()[0].getFileName());
        crashlytics.setLineNumber(String.valueOf(ex.getStackTrace()[0].getLineNumber()));

        /**Converte a exception em string para salvar no banco**/
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        //Informações sobre a exception
        crashlytics.setClassName(ex.getClass().getName());
        crashlytics.setExceptionStack(sw.toString());

        //Informações sobre a request
        crashlytics.setUrl(request.getDescription(false).split("uri=")[1]);
        crashlytics.setMethodUrl(((ServletWebRequest) request).getHttpMethod().name());
        crashlytics.setLanguage(request.getLocale().toString());

        //Map<String, String[]> parametrosUrl = request.getParameterMap();

        crashlyticsRepository.save(crashlytics);

        /**Seta os headers da request**/
        Iterator<String> names = request.getHeaderNames();
        List<Header> headers = new ArrayList<>();
        while (names.hasNext()){
            String name = names.next();
            String value = request.getHeader(name);
            Header header = new Header(name, value, crashlytics);
            headers.add(header);
        }

        crashlytics.setHeaders(headers);

        crashlyticsRepository.save(crashlytics);

        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
