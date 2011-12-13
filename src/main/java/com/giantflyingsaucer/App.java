package com.giantflyingsaucer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

    static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Bootstrapping MongoDemo application");

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");

        PersonRepository personRepository = context.getBean(PersonRepository.class);

        // cleanup person collection before insertion
        personRepository.dropPersonCollection();

        //create person collection
        personRepository.createPersonCollection();

        for (int i = 0; i < 20; i++) {
            personRepository.insertPersonWithNameJohnAndRandomAge();
        }

        personRepository.logAllPersons();
        logger.info("Finished MongoDemo application");
    }
}
