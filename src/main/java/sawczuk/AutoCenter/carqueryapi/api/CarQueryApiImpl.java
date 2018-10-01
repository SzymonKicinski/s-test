package sawczuk.AutoCenter.carqueryapi.api;

import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import sawczuk.AutoCenter.carqueryapi.model.*;

import java.util.Arrays;
import java.util.List;

@Repository
public class CarQueryApiImpl implements CarQueryApi {

    @Override
    public Year getYears() {
        return getYearsExternalApi();
    }

    @Override
    public List<Make> getMakes(Integer year) {
        String parameters = "";
        if (year != null)
            parameters += "&year=" + year;
        return getMakesExternalApi(parameters);
    }


    @Override
    public List<Model> getModels(String make, Integer year) {
        String parameters = "";
        parameters += "&make=" + make;
        if (year != null)
            parameters += "&year=" + year;
        return getModelsExternalApi(parameters);
    }

    @Override
    public List<Trim> getTrims(String make, String model, Integer year) {
        String parameters = "";
        if (!StringUtils.isEmpty(make))
            parameters += "&make=" + make;
        if (!StringUtils.isEmpty(model))
            parameters += "&model=" + model;
        if (year != null)
            parameters += "&year=" + year;
        return getTrimsExternalApi(parameters);
    }

    @Override
    public Trim getTrim(Long id) {
        return getTrimExternalApi(id);
    }


    private Year getYearsExternalApi() {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getYears";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Years> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), Years.class);
        return respEntity.getBody().getYear();
    }

    private List<Make> getMakesExternalApi(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getMakes";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Makes> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), Makes.class);
        return respEntity.getBody().getMakeList();
    }

    private List<Model> getModelsExternalApi(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getModels";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Models> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), Models.class);
        return respEntity.getBody().getModelList();
    }

    private List<Trim> getTrimsExternalApi(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getTrims";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Trims> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), Trims.class);
        return respEntity.getBody().getTrimList();
    }

    private Trim getTrimExternalApi(Long id) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getModel&model=" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Trim[]> respEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), Trim[].class);
        return respEntity.getBody()[0];
    }


    private HttpEntity<String> httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);
        return httpEntity;
    }
}
