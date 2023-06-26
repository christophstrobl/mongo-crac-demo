package com.example.crac.mongocracdemo;

import java.util.List;

import com.mongodb.ClientSessionOptions;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.connection.ClusterDescription;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.log4j2.ColorConverter;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoCracDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoCracDemoApplication.class, args);
	}

	@Bean
	MongoClient mongoClient(MongoClientSettings settings) {
		return new MongoClientWrapper(settings);
	}

	@Bean
	MongoClientSettings settings() {
//		return MongoClientSettings.builder().build();
		return MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://host.docker.internal:27017")).build();
	}

	static class MongoClientWrapper implements MongoClient, SmartLifecycle {

		MongoClientSettings settings;
		MongoClient delegate;

		public MongoClientWrapper(MongoClientSettings settings) {
			System.out.println("init MongoClientWrapper");
			this.settings = settings;
		}

		@Override
		public MongoDatabase getDatabase(String databaseName) {
			return delegate.getDatabase(databaseName);
		}

		@Override
		public ClientSession startSession() {
			return delegate.startSession();
		}

		@Override
		public ClientSession startSession(ClientSessionOptions options) {
			return delegate.startSession(options);
		}

		@Override
		public void close() {
			if(delegate != null) {
				delegate.close();
			}
		}

		@Override
		public MongoIterable<String> listDatabaseNames() {
			return delegate.listDatabaseNames();
		}

		@Override
		public MongoIterable<String> listDatabaseNames(ClientSession clientSession) {
			return delegate.listDatabaseNames(clientSession);
		}

		@Override
		public ListDatabasesIterable<Document> listDatabases() {
			return delegate.listDatabases();
		}

		@Override
		public ListDatabasesIterable<Document> listDatabases(ClientSession clientSession) {
			return delegate.listDatabases(clientSession);
		}

		@Override
		public <TResult> ListDatabasesIterable<TResult> listDatabases(Class<TResult> tResultClass) {
			return delegate.listDatabases(tResultClass);
		}

		@Override
		public <TResult> ListDatabasesIterable<TResult> listDatabases(ClientSession clientSession, Class<TResult> tResultClass) {
			return delegate.listDatabases(clientSession, tResultClass);
		}

		@Override
		public ChangeStreamIterable<Document> watch() {
			return delegate.watch();
		}

		@Override
		public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
			return delegate.watch(tResultClass);
		}

		@Override
		public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
			return delegate.watch(pipeline);
		}

		@Override
		public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
			return delegate.watch(pipeline, tResultClass);
		}

		@Override
		public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
			return delegate.watch(clientSession);
		}

		@Override
		public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> tResultClass) {
			return delegate.watch(clientSession, tResultClass);
		}

		@Override
		public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
			return delegate.watch(clientSession, pipeline);
		}

		@Override
		public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
			return delegate.watch(clientSession, pipeline, tResultClass);
		}

		@Override
		public ClusterDescription getClusterDescription() {
			return delegate.getClusterDescription();
		}

		@Override
		public void start() {
			System.out.println("start MongoClientWrapper");
			this.delegate = MongoClients.create(settings);
		}

		@Override
		public void stop() {
			System.out.println("stop MongoClientWrapper");
			this.delegate.close();
			this.delegate = null;
		}

		@Override
		public boolean isRunning() {
			return delegate != null;
		}
	}
}
