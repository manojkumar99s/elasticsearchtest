package com.testproject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;


public class App 
{
    public static void main( String[] args ) throws UnknownHostException
    {
    	Settings settings = Settings.settingsBuilder()
    			.put("client.transport.sniff", true)
    	        .put("cluster.name", "test-cluster").build();
    	Client client = TransportClient.builder().settings(settings).build()
    			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
    	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    	System.out.print(client.getClass().toString());
    	
    	//CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate("testdb");
    	//createIndexRequestBuilder.execute().actionGet();
    	
    	//Add documents
    	//IndexRequestBuilder indexRequestBuilder = client.prepareIndex(testdb, documentType, documentId);
    	
    	//Get document
    	
    	GetRequestBuilder getRequestBuilder = client.prepareGet("testdb", "employee", "1");
    	
    	getRequestBuilder.setFields(new String[]{"firstName"});
    	
    	GetResponse response = getRequestBuilder.execute().actionGet();
    	
    	String name = response.getField("firstName").getValue().toString();
    	System.out.print(name);
    }
}
