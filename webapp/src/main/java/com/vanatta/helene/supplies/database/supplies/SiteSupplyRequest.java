package com.vanatta.helene.supplies.database.supplies;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteSupplyRequest {
  @Builder.Default List<String> sites = new ArrayList<>();
  @Builder.Default List<String> items = new ArrayList<>();
  @Builder.Default List<String> counties = new ArrayList<>();
  @Builder.Default List<String> itemStatus = new ArrayList<>();
  @Builder.Default Boolean acceptingDonations = true;
  @Builder.Default Boolean notAcceptingDonations = true;
}
