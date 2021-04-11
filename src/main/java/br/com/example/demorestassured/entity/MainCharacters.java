package br.com.example.demorestassured.entity;

import br.com.example.demorestassured.entity.enums.GenderEnum;
import lombok.*;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainCharacters {

    private String name;
    private Integer nvPower;
    private GenderEnum gender;

}
