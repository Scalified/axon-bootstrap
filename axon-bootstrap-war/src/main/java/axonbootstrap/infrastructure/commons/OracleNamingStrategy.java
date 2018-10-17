package axonbootstrap.infrastructure.commons;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class OracleNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private static final ImprovedNamingStrategy IMPROVED_NAMING_STRATEGY = new ImprovedNamingStrategy();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String entityName = name.getText();
        String tableName = IMPROVED_NAMING_STRATEGY.classToTableName(entityName)
                .toUpperCase();
        return super.toPhysicalTableName(new Identifier(tableName, true), context);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String entityName = name.getText();
        String columnName = IMPROVED_NAMING_STRATEGY.columnName(entityName)
                .toUpperCase();
        return super.toPhysicalColumnName(new Identifier(columnName, true), context);
    }

}
