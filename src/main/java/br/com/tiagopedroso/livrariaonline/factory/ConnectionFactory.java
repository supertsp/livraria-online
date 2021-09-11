package br.com.tiagopedroso.livrariaonline.factory;

import java.sql.Connection;
import java.sql.DriverManager;

import static br.com.tiagopedroso.livrariaonline.config.ConfigGeral.*;

public class ConnectionFactory {
	
	private ConnectionFactory() {}
	
	public static Connection get() {
		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
