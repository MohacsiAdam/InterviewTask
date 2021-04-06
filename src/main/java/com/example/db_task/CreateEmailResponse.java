package com.example.db_task;

import com.example.db_task.DBConnection.NameInput;
import com.example.db_task.Model.EmailTemplates;
import com.example.db_task.Repositories.*;
import com.example.db_task.Model.Cars;
import com.example.db_task.Model.PersonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CreateEmailResponse implements CommandLineRunner{

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(CreateEmailResponse.class);


    //Add JPA Repositories
    private final EmailCRUD emailCRUD;
    private final PersonCRUD personCRUD;
    private final CarCRUD carCRUD;
    private final CarsOfPeopleCRUD carsOfPeopleCRUD;

    public CreateEmailResponse(EmailCRUD emailCRUD, PersonCRUD personCRUD, CarCRUD carCRUD, CarsOfPeopleCRUD carsOfPeopleCRUD) {
        this.emailCRUD = emailCRUD;
        this.personCRUD = personCRUD;
        this.carCRUD = carCRUD;
        this.carsOfPeopleCRUD = carsOfPeopleCRUD;
    }

    /**
     * The main method responsible for handling the creation of the full letter response
     * and to output the response to console
     */
    @Override
    public void run(String... args){

        NameInput nameInput = new NameInput();
        String inputName = nameInput.getNameFromUser();

        //Get Person from name
        PersonData personData = personCRUD.getPersonDataByName(inputName);

        //Get Email text from language_id
        EmailTemplates responseEmail = emailCRUD.getTextByLanguageId(personData.getLanguageId());

        //Get List of Car ids from person id
        List<Integer> carIds = carsOfPeopleCRUD.getCarsByUserId(personData.getPersonId());

        if(carIds.isEmpty()) {
            logger.error("No cars found under this name!");
        }
        else {

            //Get Cars from car ids
            List<Cars> carList = carCRUD.getCarsByOwner(carIds);

            StringBuilder sb = new StringBuilder();

            //Regex to find message parts
            Pattern pattern = Pattern.compile("^(?<person>(Dear|Kedves)([\\s\\S])+)(?<car>(Brand|Márka)([\\s\\S]+)(Euro.\\s\\s))(?<end>([\\s\\S]+))$");
            Matcher matcher = pattern.matcher(responseEmail.getText());

            //Match with matcher to avoid IllegalStateException
            matcher.matches();

            //Compose person data part of message
            String personPart = matcher.group("person");
            sb.append(getPersonMessagePart(personPart, personData));

            //Compose car data part of message
            String carOriginal = matcher.group("car");
            sb.append(getCarMessagePart(carOriginal, carList));

            //Adding message ending
            sb.append(matcher.group("end"));

            logger.info("QUERY RESULT:\n"+sb.toString());

        }
    }

    /**
     *
     * @param carOriginal The part of the e-mail response template that lists cars
     * @param carList The list of cars owned by the requester
     * @return Returns the middle part of the email response with substituted car data for every car under the specified user
     */
    private String getCarMessagePart(String carOriginal, List<Cars> carList){

        StringBuilder sb = new StringBuilder();

        for(Cars car : carList){
            String current = carOriginal;
            current = current.replaceAll("<brand>",car.getBrand());
            current = current.replaceAll("<type>",car.getType());
            current = current.replaceAll("<plateNumber>",car.getPlateNumber());
            current = current.replaceAll("<yearOfManufacture>", String.valueOf(car.getYearOfManufacture()));
            current = current.replaceAll("<drivenDistance>", String.valueOf(car.getDrivenDistance()));
            current = current.replaceAll("<calculatedValue>", String.valueOf(car.getCalculatedValue()));


            sb.append(current);
        }

        return sb.toString();
    }

    /**
     *
     * @param personMessagePart The part of the e-mail response template that lists cars
     * @param personData The data of the requester
     * @return A string containing personMessagePart with the substituted information
     */
    private String getPersonMessagePart(String personMessagePart, PersonData personData) {

        personMessagePart = personMessagePart.replaceAll("<name>",personData.getName());
        personMessagePart = personMessagePart.replaceAll("<country>",personData.getCountry());
        personMessagePart = personMessagePart.replaceAll("<dateOfBirth>", String.valueOf(personData.getDataOfBirth()));

        return personMessagePart;
    }
}