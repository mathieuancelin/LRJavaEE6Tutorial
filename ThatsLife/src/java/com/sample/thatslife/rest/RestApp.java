package com.sample.thatslife.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Classe permettant d'activer l'API JAX-RS (services REST) sur l'URL
 * http://localhost:8080/ThatsLife/services
 *
 * @author mathieuancelin
 */
@ApplicationPath("services")
public class RestApp extends Application {

}
