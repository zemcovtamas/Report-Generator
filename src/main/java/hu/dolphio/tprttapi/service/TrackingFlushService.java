/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.service;

import hu.dolphio.tprttapi.model.rtt.ReportElementTO;
import hu.dolphio.tprttapi.model.tp.UserStory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author david
 */
public interface TrackingFlushService {

    public void deleteTrackings(Collection<ReportElementTO> trackings) throws IOException;

    public void insertTrackings(Collection<ReportElementTO> trackings) throws IOException;
    
    public ArrayList<UserStory> getUserStories() throws IOException;

}
