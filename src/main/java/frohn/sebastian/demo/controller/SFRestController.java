package frohn.sebastian.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SFRestController {

    private final static String URL = "https://api.fda.gov/device/recall.json";

    public static final String TEXT_CSV = "text/csv";

    @GetMapping("/")
    public @ResponseBody ResponseEntity<InputStream> getCsvFile(@RequestParam Map<String, String> reqParam
    ) {
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(URL, String.class, reqParam);

        ObjectMapper mapper = new ObjectMapper();
        HashMap map;

        try {
            map = mapper.readValue(result, HashMap.class);
        } catch ( Exception e) {
            return new ResponseEntity<>(new ByteArrayInputStream(e.toString().getBytes(StandardCharsets.UTF_8)), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        StringBuilder sb = new StringBuilder();

        List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("results");

        if (results.size() > 0) {
            sb.append(results.get(0).keySet().stream().collect(Collectors.joining(";")));
            sb.append("\n");
        }

        sb.append(
                results.stream().map(
                        m -> m.entrySet()
                                .stream()
                                .map(
                                        e -> e.getValue()
                                )
                                .map(e -> {
                                            if (e instanceof List) {
                                                List l = (List<String>) e;
                                                return l.stream().collect(Collectors.joining("|"));
                                            } else {
                                                return e;
                                            }
                                        }
                                ).map(e -> e.toString())
                                .collect(Collectors.joining(";")))
                                .collect(Collectors.joining("\n")));


                      return new  ResponseEntity(new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8)), HttpStatus.OK);
    }
}
