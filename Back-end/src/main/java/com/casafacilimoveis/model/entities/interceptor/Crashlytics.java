package com.casafacilimoveis.model.entities.interceptor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Crashlytics.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "crashlytics", schema = "public")
public class Crashlytics implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_crashlytics")
    @SequenceGenerator(name = "seq_crashlytics", sequenceName = "crashlytics_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @Column(name = "class_error", length = 500)
    String classError;

    @Column(name = "method_name", length = 500)
    String methodName;

    @Column(name = "file_name", length = 100)
    String fileName;

    @Column(name = "line_number", length = 10)
    String lineNumber;

    @Column(name = "class_name", length = 100)
    String className;

    @Column(name = "exception_stack", length = 30000)
    String exceptionStack;

    @Column(name = "url", length = 500)
    String url;

    @Column(name = "method_url", length = 10)
    String methodUrl;

    @Column(name = "language", length = 10)
    String language;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "crashlytics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Header> headers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassError() {
        return classError;
    }

    public void setClassError(String classError) {
        this.classError = classError;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExceptionStack() {
        return exceptionStack;
    }

    public void setExceptionStack(String exceptionStack) {
        this.exceptionStack = exceptionStack;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodUrl() {
        return methodUrl;
    }

    public void setMethodUrl(String methodUrl) {
        this.methodUrl = methodUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
}
