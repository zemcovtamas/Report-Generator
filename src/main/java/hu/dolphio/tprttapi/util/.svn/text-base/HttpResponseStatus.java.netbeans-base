/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.util;

/**
 *
 * @author David
 */
public enum HttpResponseStatus {

    INFORMATIONAL(100), SUCCESS(200), REDIRECT(300), CLIENT_ERROR(400), SERVER_ERROR(500), UNKNOWN_STATUS(600);

    private final int code;

    private HttpResponseStatus(int code) {
        this.code = code;
    }

    public static HttpResponseStatus getStatusByCode(int code) {
        HttpResponseStatus current = INFORMATIONAL;
        if (current.code > code) {
            return HttpResponseStatus.UNKNOWN_STATUS;
        }
        for (HttpResponseStatus response : values()) {
            if (response.code <= code) {
                current = response;
            }
        }

        return current;
    }
}
