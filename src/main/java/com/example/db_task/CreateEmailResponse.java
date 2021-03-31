package com.example.db_task;

import com.example.db_task.DBConnection.NameInput;
import com.example.db_task.Repositories.EmailTemplateRepositoryImpl;
import com.example.db_task.Repositories.EmailTemplatesRepository;
import com.example.db_task.Repositories.PersonRepository;
import com.example.db_task.Repositories.PersonRepositoryImpl;
import com.example.db_task.TableClasses.Cars;
import com.example.db_task.TableClasses.PersonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEmailResponse {

    /*Initialize class error logger*/
    Logger logger = LoggerFactory.getLogger(CreateEmailResponse.class);

    /**
     * The main method responsible for handling the creation of the full letter response
     * and to output the response to console
     */
    public void buildEmailResponse(){

        NameInput nameInput = new NameInput();
        String inputName = nameInput.getNameFromUser();

        try{
            //Get language_id from name
            PersonRepository personRepository = new PersonRepositoryImpl();
            int language_id = personRepository.getLanguageIdByName(inputName);

            //Get mail template text from language_id
            EmailTemplatesRepository emailTemplatesRepository = new EmailTemplateRepositoryImpl();
            String responseEmail = emailTemplatesRepository.getTextById(language_id);

            //GetPersonData
            PersonData personData = personRepository.getPersonByName(inputName);

            //GetPerson's cars
            List<Cars> carList = personRepository.getCarsByName(personData.getName());

            StringBuilder sb = new StringBuilder();

            //Regex to find message parts
            Pattern pattern = Pattern.compile("^(?<person>(Dear|Kedves)([\\s\\S])+)(?<car>(Brand|Márka)([\\s\\S]+)(Euro.\\s\\s))(?<end>([\\s\\S]+))$");
            Matcher matcher = pattern.matcher(responseEmail);

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
        } catch (NoResultException e){
            logger.error("No result found for query!");
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