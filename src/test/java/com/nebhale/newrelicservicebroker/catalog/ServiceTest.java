/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.newrelicservicebroker.catalog;


import com.nebhale.newrelicservicebroker.AbstractSerializationTest;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class ServiceTest extends AbstractSerializationTest<Service> {

    @Override
    protected void assertContents(Map m) throws IOException {
        assertEquals(getId().toString(), m.get("id"));
        assertEquals("test-name", m.get("name"));
        assertEquals("test-description", m.get("description"));
        assertTrue((Boolean) m.get("bindable"));
        assertEquals(getTags(), m.get("tags"));
        assertEquals(roundTrip(getServiceMetadata()), m.get("metadata"));
        assertEquals(getRequires(), m.get("requires"));
        assertEquals(getPlans(), m.get("plans"));
        assertEquals(roundTrip(getDashboardClient()), m.get("dashboard_client"));
    }

    @Override
    protected Service getInstance() {
        return new Service(null)
                .id(getId())
                .name("test-name")
                .description("test-description")
                .bindable(true)
                .dashboardClient()
                .id("test-id")
                .secret("test-secret")
                .redirectUri(URI.create("http://test.redirect.uri"))
                .and();
    }

    public UUID getId() {
        return UUID.nameUUIDFromBytes(new byte[0]);
    }

    public List<String> getTags() {
        return Arrays.asList();
    }

    public ServiceMetadata getServiceMetadata() {
        return new ServiceMetadata(null);
    }

    public List<String> getRequires() {
        return Arrays.asList();
    }

    public List<Plan> getPlans() {
        return Arrays.asList();
    }

    public DashboardClient getDashboardClient() {
        return new DashboardClient(null)
                .id("test-id")
                .secret("test-secret")
                .redirectUri(URI.create("http://test.redirect.uri"));
    }

}