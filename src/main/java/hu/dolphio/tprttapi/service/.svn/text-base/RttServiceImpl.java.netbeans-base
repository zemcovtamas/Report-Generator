/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.dolphio.tprttapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dolphio.tprttapi.exception.ClientException;
import hu.dolphio.tprttapi.model.rtt.ReportElementTO;
import hu.dolphio.tprttapi.util.HttpResponseStatus;
import hu.dolphio.tprttapi.util.PropertyReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author david
 */
public class RttServiceImpl implements RttService {

    private final static Logger LOG = LogManager.getLogger(RttServiceImpl.class.getName());
    private final RequestConfig config;
    private final PropertyReader propertyReader = PropertyReader.getInstance();
    private final String dateFrom;
    private final String dateTo;
    private final Integer reportId;
    private final Integer projectId;

    public RttServiceImpl(Integer projectId, Integer reportId, String dateFrom, String dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.projectId = projectId;
        this.reportId = reportId;

        config = RequestConfig.custom()
                .setSocketTimeout(propertyReader.getConnectionTimeout())
                .setConnectTimeout(propertyReader.getConnectionTimeout())
                .build();
    }

    @Override
    public Collection<ReportElementTO> loadRttTrackingsFromReport() throws URISyntaxException, IOException, ClientException {

        if ((projectId == null && reportId == null) || (projectId != null && reportId != null)) {
            throw new ClientException("Project ID or Report ID must be set!");
        }

        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setDefaultRequestConfig(config)
                .build();

        HttpEntity httpEntity = new InputStreamEntity(new ByteArrayInputStream(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(new Object() {
            public String username = propertyReader.getRttUserName();
            public String password = propertyReader.getRttPassword();
        }).getBytes("UTF-8")));

        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI(propertyReader.getRttHost() + "/login"))
                .setEntity(httpEntity)
                .setHeader("Accept-Language", "sk,en-US;q=0.8,en;q=0.6,hu;q=0.4")
                .setHeader("Content-Type", "application/json;charset=utf-8")
                .build();
        CloseableHttpResponse loginResponse = httpclient.execute(login);
        LOG.debug("RTT login response: " + loginResponse);

        if (HttpResponseStatus.getStatusByCode(loginResponse.getStatusLine().getStatusCode()) != HttpResponseStatus.SUCCESS) {
            throw new ClientException("[" + loginResponse.getStatusLine().getStatusCode() + "] Login to RTT failed!");
        }

        EntityUtils.consume(loginResponse.getEntity());

        StringBuilder postUriBuilder = new StringBuilder()
                .append(propertyReader.getRttHost())
                .append(reportId == null ? propertyReader.getRttReportByProjectUrl() : propertyReader.getRttReportByReportUrl())
                .append(reportId == null ? projectId : reportId)
                .append("/json?startDate=")
                .append(dateFrom)
                .append("&endDate=")
                .append(dateTo);

        LOG.trace("RTT report query: "+postUriBuilder.toString());
        
        HttpGet get = new HttpGet(postUriBuilder.toString());
        CloseableHttpResponse rttResponse = httpclient.execute(get);

        if (HttpResponseStatus.getStatusByCode(rttResponse.getStatusLine().getStatusCode()) != HttpResponseStatus.SUCCESS) {
            throw new ClientException("[" + rttResponse.getStatusLine().getStatusCode() + "] Downloading tracking information from RTT failed!");
        }
        String trackingsJson = IOUtils.toString(rttResponse.getEntity().getContent(), "utf-8");

        Collection<ReportElementTO> fromJson = new ObjectMapper().readValue(trackingsJson, new TypeReference<Collection<ReportElementTO>>() {
        });

        return fromJson;
    }
}
