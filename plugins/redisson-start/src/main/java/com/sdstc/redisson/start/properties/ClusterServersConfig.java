package com.sdstc.redisson.start.properties;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
@Data
public class ClusterServersConfig extends BaseMasterSlaveServersConfig{
    private List<String> nodeAddresses = new ArrayList<>();
    private int scanInterval = 5000;
    private boolean checkSlotsCoverage = true;
}
