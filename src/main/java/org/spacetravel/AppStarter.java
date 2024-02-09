package org.spacetravel;

import org.spacetravel.constance.Constance;
import org.spacetravel.migration.FlywayMigration;
import org.spacetravel.util.PropertiesUtil;

public class AppStarter {
    private FlywayMigration flywayMigration;
    public AppStarter(){
        flywayMigration = new FlywayMigration( PropertiesUtil.get(Constance.DB_URL),
                PropertiesUtil.get(Constance.DB_USER_NAME),
                PropertiesUtil.get(Constance.DB_PASSWORD));
    }
}
