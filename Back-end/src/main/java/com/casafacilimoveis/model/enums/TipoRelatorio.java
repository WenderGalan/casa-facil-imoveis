package com.casafacilimoveis.model.enums;

public enum TipoRelatorio {

    PDF(0), HTML(1), XML(2), TXT(3), CSV(4), JSON(5), XLS(6);

    private int code;

    TipoRelatorio(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
