package org.spacetravel.migration;

import org.flywaydb.core.Flyway;
public class FlywayMigration {
    private Flyway flyway;


    public FlywayMigration(String url, String user, String password) {
        flyway = Flyway.configure().dataSource(
                        url,
                        user,
                        password)
                .load();
        flyway.migrate();
    }
}
