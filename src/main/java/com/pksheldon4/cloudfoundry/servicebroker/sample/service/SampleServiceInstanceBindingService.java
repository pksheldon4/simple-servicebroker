package com.pksheldon4.cloudfoundry.servicebroker.sample.service;

import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class SampleServiceInstanceBindingService implements ServiceInstanceBindingService {

    @Override
    public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {

        String bindingId = request.getBindingId();
        String serviceInstanceId = request.getServiceInstanceId();


        String password = "password";

        Map<String, Object> credentials = Collections.singletonMap("uri", String.format("%s, %s, %s",serviceInstanceId, bindingId, password));
        return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
    }

    @Override
    public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
        return;
    }
}
