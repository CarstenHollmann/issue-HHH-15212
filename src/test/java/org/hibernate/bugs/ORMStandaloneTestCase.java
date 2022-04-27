package org.hibernate.bugs;

import java.io.File;
import java.net.URISyntaxException;
import java.util.EnumSet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a standalone test case for
 * Hibernate ORM. Although this is perfectly acceptable as a reproducer, usage
 * of ORMUnitTestCase is preferred!
 */
public class ORMStandaloneTestCase {

    private SessionFactory sf;
    private Metadata metadata;

    @Before
    public void setup() throws URISyntaxException {
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
                // Add in any settings that are specific to your test. See
                // resources/hibernate.properties for the defaults.
                .applySetting("hibernate.show_sql", "true").applySetting("hibernate.format_sql", "true")
                .applySetting("hibernate.hbm2ddl.auto", "update");
                
        // set default schema to 'test'
        srb.applySetting(AvailableSettings.DEFAULT_SCHEMA, "test");

        metadata = new MetadataSources(srb.build())
                .addFile(new File(ORMStandaloneTestCase.class.getResource("/hbm/test.hbm.xml").toURI()))
                .buildMetadata();

        sf = metadata.buildSessionFactory();
    }

    // Add your tests, using standard JUnit.

    @Test
    public void hhh123Test() throws Exception {
        SchemaExport schemaExport = new SchemaExport();
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE);
        schemaExport.setDelimiter(";").setFormat(true).setHaltOnError(false);
        schemaExport.execute(targetTypes, SchemaExport.Action.CREATE, metadata);
        schemaExport.execute(targetTypes, SchemaExport.Action.DROP, metadata);
    }
}
