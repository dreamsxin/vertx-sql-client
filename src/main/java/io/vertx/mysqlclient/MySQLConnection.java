package io.vertx.mysqlclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.impl.MySQLConnectionImpl;
import io.vertx.sqlclient.PreparedQuery;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;

import java.util.stream.Collector;

public interface MySQLConnection extends SqlConnection {
  static void connect(Vertx vertx, MySQLConnectOptions options, Handler<AsyncResult<MySQLConnection>> handler) {
    MySQLConnectionImpl.connect(vertx, options, handler);
  }

  @Override
  MySQLConnection prepare(String sql, Handler<AsyncResult<PreparedQuery>> handler);

  @Override
  MySQLConnection exceptionHandler(Handler<Throwable> handler);

  @Override
  MySQLConnection closeHandler(Handler<Void> handler);

  @Override
  MySQLConnection preparedQuery(String sql, Handler<AsyncResult<RowSet>> handler);

  @Override
  <R> MySQLConnection preparedQuery(String sql, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  @Override
  MySQLConnection query(String sql, Handler<AsyncResult<RowSet>> handler);

  @Override
  <R> MySQLConnection query(String sql, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  @Override
  MySQLConnection preparedQuery(String sql, Tuple arguments, Handler<AsyncResult<RowSet>> handler);

  @Override
  <R> MySQLConnection preparedQuery(String sql, Tuple arguments, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  MySQLConnection ping(Handler<AsyncResult<Void>> handler);
}