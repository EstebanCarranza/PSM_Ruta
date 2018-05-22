package com.twicky.estebancarranza.reparto.webservice;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class staticData {

    //static final String SERVER_PATH_GET_ADDRESS = "http://reparto.twicky.com.mx/synchronizeClients.php";
    static final String HOST_REMOTO = "http://reparto.twicky.com.mx/";
    static final String HOST_LOCAL = "http://192.168.0.13:8081/psm/";
    static final String HOST = HOST_REMOTO;
    static final String SERVER_PATH_LOGIN = HOST + "login.php";
    static final String SERVER_PATH_GET_ADDRESS = HOST + "getAddress.php";
    static final String SERRVER_PATH_SYNCHRONIZE_CLIENTS = HOST + "synchronizeClients.php";
    static final String SERVER_PATH_INSERT_SYNCHRONIZE_CLIENTS = HOST + "getSynchronizeClients.php";
    static final String SERRVER_PATH_CREATE_CLIENTS = HOST + "createClients.php";
    static final String SERVER_PATH = "http://maps.googleapis.com/maps/api/geocode/json?latlng=25.725563,-100.3153316&Key=AIzaSyBgq1EU6PKHIVpX8p2CBy-NA2G6Ngrjku4";
    static final int TIMEOUT = 3000;
}
