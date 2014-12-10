/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi;

import hu.dolphio.tprttapi.docgenerate.DocGenerator;
import hu.dolphio.tprttapi.docgenerate.DocxGenerator;
import hu.dolphio.tprttapi.service.RttService;
import hu.dolphio.tprttapi.service.RttServiceImpl;
import hu.dolphio.tprttapi.exception.ClientException;
import hu.dolphio.tprttapi.model.rtt.ReportElementTO;
import hu.dolphio.tprttapi.model.tp.UserStory;
import hu.dolphio.tprttapi.service.TrackingFlushService;
import hu.dolphio.tprttapi.service.TrackingFlushServiceTpImpl;
import hu.dolphio.tprttapi.util.PropertyReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author david
 */
public class RttTpConnector {

    private final TrackingFlushService trackingFlushService;
    private final RttService rttService;
    private final PropertyReader propertyReader = PropertyReader.getInstance();

    public RttTpConnector(Integer projectId, Integer reportId, String dateFrom, String dateTo) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        switch (propertyReader.getFlushSystem()) {
            case "tp":
                trackingFlushService = new TrackingFlushServiceTpImpl(dateFrom, dateTo);
                break;
//            case "jira":
//                trackingFlushService = new TrackingFlushServiceJiraImpl(dateFrom, dateTo);
//                break;
            default:
                throw new UnsupportedOperationException("No or invalid tracking flush system is set!");
        }
        rttService = new RttServiceImpl(projectId, reportId, dateFrom, dateTo);
    }

    public void connect() throws IOException, URISyntaxException, ClientException {

        //Collection<ReportElementTO> trackings = rttService.loadRttTrackingsFromReport();
        
        
          ArrayList<UserStory> userStories = trackingFlushService.getUserStories();
          DocxGenerator.generateDocFromTp(userStories);
        
        

        //trackingFlushService.deleteTrackings(trackings);
        //trackingFlushService.insertTrackings(trackings);

    }
}
