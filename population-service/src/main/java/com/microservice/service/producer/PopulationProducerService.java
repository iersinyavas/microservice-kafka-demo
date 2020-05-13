package com.microservice.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.Age;
import com.microservice.domain.Population;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
public class PopulationProducerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    Random random = new Random();

    private static List<Population> populationList = new ArrayList<Population>();
    private static Map<Integer, Age> ageMap = new HashMap<>();
    private static int birth;
    private static int marriage=1;
    private static int singleMale;
    private static int singleFemale;
    private static int male=1;
    private static int female=1;
    private static int year=0;
    public PopulationProducerService(){
        Age age = new Age();
        age.setFemale(1);
        age.setMale(1);
        age.setMaleHealth(100);
        age.setFemaleHealth(100);
        age.setDeadMale(0);
        age.setDeadFemale(0);
        ageMap.put(18, age);
    }

    public void sendPopulation() throws JsonProcessingException {
        String population = objectMapper.writeValueAsString(createPopulation());
        ListenableFuture<SendResult<String, String>> populationSend = kafkaTemplate.send("population", population);

        populationSend.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Population data : {} ", result.toString());
            }
        });
    }

    private Population createPopulation(){
        Population population = new Population();

        int lessGreaterFemale = getLessGreaterFemale(17, 41);
        if (marriage > 0) {
            birth = random.nextInt(random.nextInt(random.nextInt((marriage < lessGreaterFemale ? marriage : lessGreaterFemale)+1)+2)+1);
        }

        population.setAge(new Age());
        population.setBirth(birth);
        male = getLessGreaterMale(17, 41);
        female = getLessGreaterFemale(17, 41);
        if (birth > 0){
            population.getAge().setMale(random.nextInt(birth+1));
            population.getAge().setFemale(birth-population.getAge().getMale());
            population.getAge().setMaleHealth(100);
            population.getAge().setFemaleHealth(100);
            population.getAge().setDeadMale(0);
            population.getAge().setDeadFemale(0);
        }

        if (male > 0) {
            singleMale = male - marriage;
        }
        if (female > 0) {
            singleFemale = female - marriage;
        }

        if (singleMale > 0 && singleFemale > 0) {
            int marriagePop = random.nextInt((singleMale < singleFemale ? singleMale : singleFemale) + 1);
            population.setMarriage(marriagePop);
            marriage += marriagePop;
        }else{
            population.setMarriage(0);
        }

        Map<Integer, Age> ageMap1 = new HashMap<>();
        /*ageMap.entrySet().stream().filter(integerAgeEntry -> integerAgeEntry.getValue().getMale()>0 || integerAgeEntry.getValue().getFemale()>0).forEach(*/
        ageMap.entrySet().stream().limit(90).forEach(
                integerAgeEntry -> {
                    ageMap1.put(integerAgeEntry.getKey()+1, integerAgeEntry.getValue());
                    integerAgeEntry.getValue().setMaleHealth(integerAgeEntry.getValue().getMaleHealth() - random.nextInt(4));
                    integerAgeEntry.getValue().setFemaleHealth(integerAgeEntry.getValue().getFemaleHealth() - random.nextInt(4));
                }
        );

        ageMap1.put(0, population.getAge());
        ageMap = ageMap1;
        deadPopulation();

        male = getGreaterThanMale(-1);
        female = getGreaterThanFemale(-1);
        population.setCurrentMale(male);
        population.setCurrentFemale(female);
        population.setPopulation((long)(male + female));
        population.setYear(year);
        population.getAge().setDeadMale(getGreaterDeadMale(-1));
        population.getAge().setDeadFemale(getGreaterDeadFemale(-1));
        year++;
        System.out.println(ageMap);

        return population;
    }

    private Map<Integer, Age> deadPopulation(){
        ageMap.entrySet().stream().filter(integerAgeEntry -> integerAgeEntry.getValue().getMaleHealth() <= 0).forEach(
                integerAgeEntry -> {
                    integerAgeEntry.getValue().setMaleHealth(0);
                    integerAgeEntry.getValue().setDeadMale(random.nextInt(integerAgeEntry.getValue().getMale()+1));
                    integerAgeEntry.getValue().setMale(integerAgeEntry.getValue().getMale()-integerAgeEntry.getValue().getDeadMale());
                }
        );

        ageMap.entrySet().stream().filter(integerAgeEntry -> integerAgeEntry.getValue().getFemaleHealth() <= 0).forEach(
                integerAgeEntry -> {
                    integerAgeEntry.getValue().setFemaleHealth(0);
                    integerAgeEntry.getValue().setDeadFemale(random.nextInt(integerAgeEntry.getValue().getFemale()+1));
                    integerAgeEntry.getValue().setFemale(integerAgeEntry.getValue().getFemale()-integerAgeEntry.getValue().getDeadFemale());
                }
        );
        return ageMap;
    }

    private int getGreaterDeadMale(int age){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > age)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getDeadMale()).reduce(0, (a, b) -> a+b);
    }

    private int getGreaterDeadFemale(int age){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > age)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getDeadFemale()).reduce(0, (a, b) -> a+b);
    }

    private int getGreaterThanMale(int age){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > age)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getMale()).reduce(0, (a, b) -> a+b);
    }

    private int getGreaterThanFemale(int age){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > age)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getFemale()).reduce(0, (a, b) -> a+b);
    }

    private int getLessGreaterMale(int ageLess, int ageGreater){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > ageLess && integerAgeEntry.getKey() < ageGreater)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getMale()).reduce(0, (a, b) -> a+b);
    }

    private int getLessGreaterFemale(int ageLess, int ageGreater){
        return ageMap.entrySet().stream()
                .filter(integerAgeEntry -> integerAgeEntry.getKey() > ageLess && integerAgeEntry.getKey() < ageGreater)
                .map(integerAgeEntry -> integerAgeEntry.getValue().getFemale()).reduce(0, (a, b) -> a+b);
    }

}
