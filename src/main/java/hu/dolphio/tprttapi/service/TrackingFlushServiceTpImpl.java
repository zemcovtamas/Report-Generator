/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dolphio.tprttapi.model.tp.Assignable;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.dolphio.tprttapi.model.rtt.ReportElementTO;
import hu.dolphio.tprttapi.model.tp.UserStory;
import hu.dolphio.tprttapi.util.HttpResponseStatus;
import hu.dolphio.tprttapi.util.PropertyReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author david
 */
public class TrackingFlushServiceTpImpl implements TrackingFlushService {

    private final HttpHost targetHost;
    private final HttpClientContext clientContext = HttpClientContext.create();
    private final CredentialsProvider credsProvider;
    private final String dateFrom;
    private final String dateTo;
    private final Map<Integer, Assignable> assignableMap = new HashMap<>();
    private final static Logger LOG = LogManager.getLogger(TrackingFlushServiceTpImpl.class.getName());
    private final RequestConfig config;
    private final PropertyReader propertyReader = PropertyReader.getInstance();

    public TrackingFlushServiceTpImpl(String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;

        targetHost = new HttpHost(propertyReader.getTpHost(), 80, "http");
        credsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials(propertyReader.getTpUserName(), propertyReader.getTpPassword());
        credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), credentials);
        config = RequestConfig.custom()
                .setSocketTimeout(propertyReader.getConnectionTimeout())
                .setConnectTimeout(propertyReader.getConnectionTimeout())
                .build();

        AuthCache authCache = new BasicAuthCache();

        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        clientContext.setAuthCache(authCache);
    }

    @Override
    public void deleteTrackings(Collection<ReportElementTO> trackings) throws IOException {
//        Map<String, Integer> uniqueProjects = new HashMap<>();
//        for (ReportElementTO tracking : trackings) {
//            if (tracking.getTicketId() == null || tracking.getTicketId().isEmpty() || uniqueProjects.containsKey(tracking.getTicketId())) {
//                continue;
//            }
//            uniqueProjects.put(tracking.getTicketId(), tracking.getProject().getId());
//        }
//
//        List<Time> timesToDelete = new ArrayList<>();
//        for (String assignable : uniqueProjects.keySet()) {
//            CloseableHttpClient httpclient = HttpClients.custom()
//                    .setDefaultRequestConfig(config)
//                    .setDefaultCredentialsProvider(credsProvider).build();
//
//            try {
//                StringBuilder urlBuilder = new StringBuilder()
//                        .append("/api/v1/Times?include=[Id]&format=json&where=(Date%20gte%20%27")
//                        .append(dateFrom)
//                        .append("%27)%20and%20(Date%20lte%20%27")
//                        .append(dateTo)
//                        .append("%27)%20and%20(Assignable.Id%20eq%20%27")
//                        .append(assignable)
//                        .append("%27)");
//                
//                HttpGet httpGet = new HttpGet(urlBuilder.toString());
//
//                CloseableHttpResponse execute = httpclient.execute(targetHost, httpGet, clientContext);
//                String timesJson = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
//                LOG.info("Search for TP times return: " + execute);
//                httpclient.close();
//
//                Map<String, Collection<Time>> times = new ObjectMapper().readValue(timesJson, new TypeReference<Map<String, Collection<Time>>>() {
//                });
//                timesToDelete.addAll(times.get("Items"));
//            } catch (IOException ex) {
//                LOG.warn(ex.getMessage(), ex);
//            }
//        }
//
//        for (Time time : timesToDelete) {
//            CloseableHttpClient httpclient = HttpClients.custom()
//                    .setDefaultRequestConfig(config)
//                    .setDefaultCredentialsProvider(credsProvider).build();
//
//            try {
//                String postUri = "/api/v1/Times/" + time.getTimeID();
//                HttpDelete httpDelete = new HttpDelete(postUri);
//
//                CloseableHttpResponse execute = httpclient.execute(targetHost, httpDelete, clientContext);
//                LOG.info("TP time delete return: " + execute);
//                httpclient.close();
//
//            } catch (IOException ex) {
//                LOG.warn(ex.getMessage(), ex);
//            }
//        }

    }

    @Override
    public void insertTrackings(Collection<ReportElementTO> trackings) throws IOException {
//        for (ReportElementTO tracking : trackings) {
//            LOG.trace("Processing tracking: " + tracking.getTrackingId());
//            if (StringUtils.isBlank(tracking.getTicketId()) || !NumberUtils.isNumber(tracking.getTicketId())) {
//                LOG.warn("Skipping tracking:" + tracking.getTrackingId() + " bc ticketId is not a valid number: " + tracking.getTicketId());
//                continue;
//            }
//            CloseableHttpClient httpclient = HttpClients.custom()
//                    .setDefaultRequestConfig(config)
//                    .setDefaultCredentialsProvider(credsProvider).build();
//
//            try {
//                String postUri = "/api/v1/Times?resultFormat=json&resultInclude=[Description,Project,Assignable,Spent]";
//                HttpPost httpPost = new HttpPost(postUri);
//                httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
//
//                Assignable assignable = getAssignableFromTp(Integer.parseInt(tracking.getTicketId()));
//                if (assignable == null) {
//                    continue;
//                }
//                double remainTime = assignable.getEffort() - assignable.getSpentTime() - tracking.getTime().getHours();
//                assignable.setEffort(remainTime < 0 ? 0 : remainTime);
//
//                StringBuilder description = new StringBuilder()
//                        .append((tracking.getUser().getName() != null ? (tracking.getUser().getName()) : ""))
//                        .append((tracking.getRole() != null ? " @" + tracking.getRole().getAbbreviation() : ""))
//                        .append(tracking.getCategory() != null ? " +" + tracking.getCategory().getAbbreviation() : "")
//                        .append(" [")
//                        .append(tracking.getTrackingId())
//                        .append("] ");
//                
//                Time time = new Time(description.toString(), assignable.getProject().getId(), Integer.parseInt(tracking.getTicketId()), tracking.getTime().getHours(), assignable.getEffort(), tracking.getTime().getDate());
//
//                if (time.getProjectId() == null) {
//                    continue;
//                }
//                LOG.trace(time);
//
//                httpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(time.toString().getBytes("UTF-8"))));
//                CloseableHttpResponse execute = httpclient.execute(targetHost, httpPost, clientContext);
//                LOG.info("TP insert return: " + execute);
//                httpclient.close();
//
//            } catch (IOException ex) {
//                LOG.warn(ex.getMessage(), ex);
//            }
//        }
    }

    private Assignable getAssignableFromTp(Integer assignableId) throws IOException {
        if (assignableMap.containsKey(assignableId)) {
            return assignableMap.get(assignableId);
        }
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .setDefaultCredentialsProvider(credsProvider).build();

        LOG.info("Trying to get assignable: " + assignableId);
        String getUri = "/api/v1/Assignables/" + assignableId + "?include=[Id,TimeSpent,Effort,Project[Id,Name]]&format=json";
        HttpGet httpGet = new HttpGet(getUri);
        CloseableHttpResponse execute = httpclient.execute(targetHost, httpGet, clientContext);
        LOG.info("TP assignable download response: " + execute);
        if (HttpResponseStatus.getStatusByCode(execute.getStatusLine().getStatusCode()) != HttpResponseStatus.SUCCESS) {
            LOG.warn("[" + execute.getStatusLine().getStatusCode() + "] Downloading Assignable information from TargetProcess failed!");
            return null;
        }
        String projectsJson = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
        httpclient.close();

        Assignable assignable = new ObjectMapper().readValue(projectsJson, Assignable.class);

        if (!assignableMap.containsKey(assignable.getAssignableId())) {
            assignableMap.put(assignable.getAssignableId(), assignable);
            return assignable;
        }
        return null;
    }

    @Override
    public ArrayList<UserStory> getUserStories() throws IOException {
        
        ArrayList<UserStory> userStories = new ArrayList<UserStory>();
         
        
        
        CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultRequestConfig(config)
                    .setDefaultCredentialsProvider(credsProvider).build();

            try {
                StringBuilder urlBuilder = new StringBuilder()
                        .append("/api/v1/Context/?ids=1184");
                        
                
                HttpGet httpGet = new HttpGet(urlBuilder.toString());

                CloseableHttpResponse execute = httpclient.execute(targetHost, httpGet, clientContext);
                String acid = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
                LOG.info("Search for TP times return: " + execute);
                
              
                
                acid = acid.substring(acid.indexOf("\"")+1);
                acid = acid.substring(0,acid.indexOf("\""));
                
                
                //userstories
                urlBuilder = new StringBuilder()
                        .append("/api/v1/Userstories/?acid=")
                        .append(acid);
                    
                httpGet = new HttpGet(urlBuilder.toString());

                execute = httpclient.execute(targetHost, httpGet, clientContext);
                String userStoriesXml = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
                LOG.info("Search for TP times return: " + execute);
                
                
                //features
               
                urlBuilder = new StringBuilder()
                        .append("/api/v1/Features/?acid=")
                        .append(acid);
                
                        
                
                httpGet = new HttpGet(urlBuilder.toString());

                execute = httpclient.execute(targetHost, httpGet, clientContext);
                String featuresXml = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
                LOG.info("Search for TP times return: " + execute);
                
                
               
                
                
                
//                
//                File fileObj = new File("C:\\Users\\zemcov.tamas\\Desktop\\test\\us2.xml");
//
//                PrintWriter writer = null;
//                try {
//                writer = new PrintWriter(fileObj);
//               }  catch (FileNotFoundException e) {
//                 e.printStackTrace();
//                 }
//    
//    
//
//               writer.println(userStoriesXml);
//             writer.close(); 
//                
               Writer out = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("C:\\Users\\zemcov.tamas\\Desktop\\test\\us.xml"), "UTF-8"));
                try {
                out.write(userStoriesXml);
                } finally {
                   out.close();
               }
                
                
                //
             
                //
             
              try {
        //String parsableUserStories = new String("<?xml version=\"1.0\"?>\n").concat(userStoriesXml);

        File fXmlFile = new File("C:\\Users\\zemcov.tamas\\Desktop\\test\\us.xml");          
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //Document doc = dBuilder.parse(UserStoriesUtf8);
        Document doc = dBuilder.parse(fXmlFile);
 
	NodeList nList = doc.getElementsByTagName("UserStory");

	for (int temp = 0; temp < nList.getLength(); temp++) {
		
            Node nNode = nList.item(temp);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			UserStory us = new UserStory();
                        
                        

                        us.setName(eElement.getAttribute("Name"));
                        if (eElement.getElementsByTagName("Team").item(0).getAttributes().getNamedItem("Name") != null){
                        us.setResponsible(eElement.getElementsByTagName("Team").item(0).getAttributes().getNamedItem("Name").getTextContent());
                        }
                        else us.setResponsible(null);
                        us.setStatus(eElement.getElementsByTagName("Progress").item(0).getTextContent());
                        us.setDueDate(eElement.getElementsByTagName("PlannedEndDate").item(0).getTextContent());
                        us.setEntityState(eElement.getElementsByTagName("EntityState").item(0).getAttributes().getNamedItem("Name").getTextContent());
                        
                        
                        userStories.add(us);
                        
//                        System.out.println("Name : " + eElement.getAttribute("Name"));
//                        if (eElement.getElementsByTagName("Team").item(0).getAttributes().getNamedItem("Name") != null){
//                        System.out.println("responsible : " + eElement.getElementsByTagName("Team").item(0).getAttributes().getNamedItem("Name").getTextContent());
//                        }
//                        System.out.println("status : " + eElement.getElementsByTagName("Progress").item(0).getTextContent());
//			System.out.println("duedate : " + eElement.getElementsByTagName("PlannedEndDate").item(0).getTextContent());
//			System.out.println("entity state : " + eElement.getElementsByTagName("EntityState").item(0).getAttributes().getNamedItem("Name").getTextContent());

                        }
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                     }
             
             
             
                //
                httpclient.close();
            } catch (IOException ex) {
                LOG.warn(ex.getMessage(), ex);
            }
        
        return userStories;
    }
    
    
    
}
