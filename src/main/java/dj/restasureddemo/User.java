package dj.restasureddemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true) // w tescie zignoruje dzieki temu niezidentyfikowane pola
@Data
public class User {

    public String login;
    public long id;
    @JsonProperty("public_repos") // json nie uzywa camelcase tylko _ tego separatora
    public int publicRepos;


}
