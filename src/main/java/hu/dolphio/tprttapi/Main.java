/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi;

import hu.dolphio.tprttapi.exception.ClientException;
import hu.dolphio.tprttapi.util.PropertyReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author david
 */
public class Main {

    public static Integer projectId;
    public static Integer reportId;
    public static String dateFrom;
    public static String dateTo;

    public final static Logger LOG = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        
        try {
/*
            for (int i = 0; i < args.length - 1; i += 2) {
                String param = args[i];
                switch (param) {
                    case "-p":
                        projectId = Integer.parseInt(args[i + 1]);
                        break;
                    case "-r":
                        reportId = Integer.parseInt(args[i + 1]);
                        break;
                    case "-date":
                        dateFrom = args[i + 1];
                        dateTo = args[i + 1];
                        break;
                    case "-dateFrom":
                        dateFrom = args[i + 1];
                        break;
                    case "-dateTo":
                        dateTo = args[i + 1];
                        break;
                    case "-settings":
                        PropertyReader.propertyFile = args[i + 1];
                        break;
                    default:
                        System.err.println("Unrecognized parameter " + param);
                        break;
                }
            }

            if ((projectId == null && reportId == null) || (projectId != null && reportId != null)) {
                throw new ClientException("Project ID or Report ID is not set(arg0)!");
            }

            if (dateFrom == null || dateTo == null) {
                throw new ClientException("Date interval is not set(arg1-2)!");
            }*/

            RttTpConnector connector = new RttTpConnector(projectId, reportId, dateFrom, dateTo);
            connector.connect();
        } catch (URISyntaxException | IOException | ClientException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException ex) {
            LOG.error(ex.getMessage(), ex);
        }

    }
}
