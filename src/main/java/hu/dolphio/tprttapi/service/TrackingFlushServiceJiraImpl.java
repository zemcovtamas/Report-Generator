///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hu.dolphio.tprttapi.service;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import hu.dolphio.tprttapi.model.jira.WorkLog;
//import hu.dolphio.tprttapi.model.jira.WorkLogParent;
//import hu.dolphio.tprttapi.model.rtt.ReportElementTO;
//import hu.dolphio.tprttapi.util.HttpResponseStatus;
//import hu.dolphio.tprttapi.util.PropertyReader;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLException;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocket;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.Credentials;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.AuthCache;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.conn.ssl.SSLContextBuilder;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.conn.ssl.X509HostnameVerifier;
//import org.apache.http.entity.InputStreamEntity;
//import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.impl.client.BasicAuthCache;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.joda.time.DateTime;
//import org.joda.time.Interval;
//
///**
// *
// * @author david
// */
//public class TrackingFlushServiceJiraImpl implements TrackingFlushService {
//
//    private final HttpHost targetHost;
//    private final HttpClientContext clientContext = HttpClientContext.create();
//    private final CredentialsProvider credsProvider;
//    private final String dateFrom;
//    private final String dateTo;
//    private final static Logger LOG = LogManager.getLogger(TrackingFlushServiceTpImpl.class.getName());
//    private final RequestConfig config;
//    private final PropertyReader propertyReader = PropertyReader.getInstance();
//    private final HttpClientBuilder httpClientBuilder;
//
//    public TrackingFlushServiceJiraImpl(String dateFrom, String dateTo) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        this.dateFrom = dateFrom;
//        this.dateTo = dateTo;
//
//        targetHost = new HttpHost(propertyReader.getJiraHost(), propertyReader.getJiraPort(), "https");
//        credsProvider = new BasicCredentialsProvider();
//        Credentials credentials = new UsernamePasswordCredentials(propertyReader.getJiraUserName(), propertyReader.getJiraPassword());
//        credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()), credentials);
//        config = RequestConfig.custom()
//                .setSocketTimeout(propertyReader.getConnectionTimeout())
//                .setConnectTimeout(propertyReader.getConnectionTimeout())
//                .build();
//
//        AuthCache authCache = new BasicAuthCache();
//
//        BasicScheme basicAuth = new BasicScheme();
//        authCache.put(targetHost, basicAuth);
//
//        clientContext.setAuthCache(authCache);
//        httpClientBuilder = createHttpClientBuilder();
//    }
//
//    @Override
//    public void deleteTrackings(Collection<ReportElementTO> trackings) throws IOException {
//        Map<String, Integer> uniqueProjects = new HashMap<>();
//        for (ReportElementTO tracking : trackings) {
//            if (tracking.getTicketId() == null || tracking.getTicketId().isEmpty() || uniqueProjects.containsKey(tracking.getTicketId())) {
//                continue;
//            }
//            uniqueProjects.put(tracking.getTicketId(), tracking.getProject().getId());
//        }
//
//        List<WorkLog> workLogsToDelete = new ArrayList<>();
//        for (String issue : uniqueProjects.keySet()) {
//            CloseableHttpClient httpclient = httpClientBuilder.build();
//
//            String getUri = "/rest/api/2/issue/" + issue + "/worklog";
//            HttpGet httpGet = new HttpGet(getUri);
//
//            CloseableHttpResponse execute = httpclient.execute(targetHost, httpGet, clientContext);
//            if (HttpResponseStatus.getStatusByCode(execute.getStatusLine().getStatusCode()) != HttpResponseStatus.SUCCESS) {
//                LOG.warn("[" + execute.getStatusLine().getStatusCode() + "] issue not found!");
//                continue;
//            }
//            String workLogsJson = IOUtils.toString(execute.getEntity().getContent(), "utf-8");
//            LOG.info("Search for TP times return: " + execute);
//            LOG.trace(workLogsJson);
//            httpclient.close();
//
//            WorkLogParent logs = new ObjectMapper().readValue(workLogsJson, new TypeReference<WorkLogParent>() {
//            });
//
//            for (WorkLog workLog : logs.getWorkLogs()) {
//                if (new Interval(new DateTime(dateFrom), new DateTime(dateTo).plusDays(1)).contains(workLog.getCreated().getTime())) {
//                    workLog.setIssueId(issue);
//                    workLogsToDelete.add(workLog);
//                }
//            }
//        }
//
//        for (WorkLog workLog : workLogsToDelete) {
//            CloseableHttpClient httpclient = httpClientBuilder.build();
//
//            try {
//                String postUri = "/rest/api/2/issue/" + workLog.getIssueId() + "/worklog/" + workLog.getId();
//                HttpDelete httpDelete = new HttpDelete(postUri);
//
//                CloseableHttpResponse execute = httpclient.execute(targetHost, httpDelete, clientContext);
//                LOG.info("Jira worklog delete return: " + execute);
//                httpclient.close();
//
//            } catch (IOException ex) {
//                LOG.warn(ex.getMessage(), ex);
//            }
//        }
//
//    }
//
//    @Override
//    public void insertTrackings(Collection<ReportElementTO> trackings) throws IOException {
//        for (ReportElementTO tracking : trackings) {
//
//            LOG.trace("Processing tracking: " + tracking.getTrackingId());
//            if (StringUtils.isBlank(tracking.getTicketId())) {
//                LOG.warn("Skipping tracking:" + tracking.getTrackingId() + " bc ticketId is not set: " + tracking.getTicketId());
//                continue;
//            }
//
//            CloseableHttpClient httpclient = httpClientBuilder.build();
//
//            try {
//                String postUri = "/rest/api/2/issue/" + tracking.getTicketId() + "/worklog?adjustEstimate=auto";
//                LOG.trace(postUri);
//                HttpPost httpPost = new HttpPost(postUri);
//                httpPost.setHeader("Content-Type", "application/json; charset=utf-8");
//
//                StringBuilder description = new StringBuilder()
//                        .append((tracking.getUser().getName() != null ? (tracking.getUser().getName()) : ""))
//                        .append((tracking.getRole() != null ? " @" + tracking.getRole().getAbbreviation() : ""))
//                        .append(tracking.getCategory() != null ? " +" + tracking.getCategory().getAbbreviation() : "")
//                        .append(" [")
//                        .append(tracking.getTrackingId())
//                        .append("] ");
//
//                Long trackingTime = tracking.getTime().getDiff() / 1000;
//                /*HttpEntity httpEntity = new InputStreamEntity(new ByteArrayInputStream(new ObjectMapper()
//                 .writerWithDefaultPrettyPrinter()
//                 .writeValueAsString(new WorkLog(new Date(tracking.getTime().getFrom()), trackingTime.intValue(), tracking.getComment()))
//                 .getBytes("UTF-8")));
//                 */
//                HttpEntity httpEntity = new InputStreamEntity(new ByteArrayInputStream(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(new Object() {
//                    public Integer timeSpentSeconds = trackingTime.intValue();
//                    public Date started = new Date(tracking.getTime().getFrom());
//                    public String comment = description.toString();
//                }).getBytes("UTF-8")));
//
//                httpPost.setEntity(httpEntity);
//                CloseableHttpResponse execute = httpclient.execute(targetHost, httpPost, clientContext);
//                LOG.info("TP insert return: " + execute);
//
//                if (HttpResponseStatus.getStatusByCode(execute.getStatusLine().getStatusCode()) != HttpResponseStatus.SUCCESS) {
//                    LOG.warn("[" + execute.getStatusLine().getStatusCode() + "] Tracking not persisted!");
//                }
//                httpclient.close();
//            } catch (IOException ex) {
//                LOG.warn(ex.getMessage(), ex);
//            }
//        }
//    }
//
//    private HttpClientBuilder createHttpClientBuilder() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
//
//        sslContextBuilder.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()),
//                new TrustSelfSignedStrategy() {
//                    @Override
//                    public boolean isTrusted(X509Certificate[] chain,
//                            String authType)
//                    throws CertificateException {
//                        return true;
//                    }
//                });
//
//        SSLContext sslContext = sslContextBuilder.build();
//        HttpClientBuilder clientBuilder = HttpClients
//                .custom()
//                .setSslcontext(sslContext)
//                .setDefaultCredentialsProvider(credsProvider)
//                .setDefaultRequestConfig(config)
//                .setHostnameVerifier(new X509HostnameVerifier() {
//                    @Override
//                    public void verify(String host, SSLSocket ssl) throws IOException {
//                    }
//
//                    @Override
//                    public void verify(String host, X509Certificate cert) throws SSLException {
//                    }
//
//                    @Override
//                    public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
//                    }
//
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                });
//        return clientBuilder;
//    }
//
//}
