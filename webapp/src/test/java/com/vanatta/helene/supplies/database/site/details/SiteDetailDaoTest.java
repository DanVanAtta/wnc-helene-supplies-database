package com.vanatta.helene.supplies.database.site.details;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.vanatta.helene.supplies.database.TestConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SiteDetailDaoTest {

  @BeforeAll
  static void setUp() {
    TestConfiguration.setupDatabase();
  }

  @Test
  void lookupSite() {
    long idToLookup =
        TestConfiguration.jdbiTest.withHandle(
            handle ->
                handle
                    .createQuery("select id from site where name = 'site1'")
                    .mapTo(Long.class)
                    .one());

    var result = SiteDetailDao.lookupSiteById(TestConfiguration.jdbiTest, idToLookup);

    // Check results against this insert statement (copied from testConfiguration setup)
    //  insert into site(name, address, city, county_id, state) values(
    //      'site1', 'address1', 'city1', (select id from county where name = 'Watauga'), 'NC'
    //      );
    assertThat(result.getSiteName()).isEqualTo("site1");
    //    assertThat(result.getContactNumber()).isEqualTo("site1");
    assertThat(result.getAddress()).isEqualTo("address1");
    assertThat(result.getCity()).isEqualTo("city1");
    assertThat(result.getState()).isEqualTo("NC");
  }

  @Test
  void nonExistentSiteThrows() {
    org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> SiteDetailDao.lookupSiteById(TestConfiguration.jdbiTest, -1));
  }
}
