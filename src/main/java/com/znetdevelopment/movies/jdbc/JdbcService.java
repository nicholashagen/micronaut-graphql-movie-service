package com.znetdevelopment.movies.jdbc;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.hsqldb.cmdline.SqlFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class JdbcService {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcService.class);

    @Inject private DataSource dataSource;

    private Scheduler scheduler = Schedulers.io();

    @PostConstruct
    public void initialize() throws Exception {
        LOG.info("Setting up database...");
        try (InputStream inputStream = getClass().getResourceAsStream("/dbsetup.sql")) {
            SqlFile sqlFile = new SqlFile(new InputStreamReader(inputStream), "init", System.out, "UTF-8", false, new File("."));
            sqlFile.setConnection(dataSource.getConnection());
            sqlFile.execute();
        }
    }

    public void execute(String query, Object... params) {
        try {
            CallableStatement statement = dataSource.getConnection().prepareCall(query);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            statement.execute();
        } catch (Exception exception) {
            throw new IllegalStateException("unable to execute query '" + query + "'", exception);
        }
    }

    public SingleQuery findOne(String query, Object... params) {
        return new SingleQuery(query, params);
    }

    public ListQuery findAll(String query, Object... params) {
        return new ListQuery(query, params);
    }

    protected Single<ResultSet> executeQuery(String query, Object[] params) {
        return Single.defer(() -> {
            try {
                CallableStatement statement = dataSource.getConnection().prepareCall(query);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }

                return Single.just(statement.executeQuery());
            } catch (Exception exception) {
                throw new IllegalStateException("unable to execute query '" + query + "'", exception);
            }
        }).subscribeOn(scheduler);
    }

    public class SingleQuery {
        private String query;
        private Object[] params;

        public SingleQuery(String query, Object[] params) {
            this.query = query;
            this.params = params;
        }

        public <T> Single<T> transform(JdbcFunction<ResultSet, T> transformer) {
            return executeQuery(query, params).map(results -> {
                T item = null;
                if (results.next()) {
                    item = transformer.apply(results);
                }

                return item;
            });
        }
    }

    public class ListQuery {
        private String query;
        private Object[] params;

        public ListQuery(String query, Object[] params) {
            this.query = query;
            this.params = params;
        }

        public <T> Single<List<T>> transform(JdbcFunction<ResultSet, T> transformer) {
            return executeQuery(query, params).map(results -> {
                List<T> list = new ArrayList<>();
                while (results.next()) {
                    list.add(transformer.apply(results));
                }

                return list;
            });
        }
    }

    @FunctionalInterface
    public static interface JdbcFunction<T, R> {
        R apply(T value) throws SQLException;
    }
}
