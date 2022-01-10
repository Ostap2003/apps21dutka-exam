package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    private boolean checkPassed(Integer mark) {
        return mark > 2;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObjectOut = new JsonObject();
        jsonObjectOut.add(new JsonPair("name", new JsonString(name)));
        jsonObjectOut.add(new JsonPair("surname", new JsonString(surname)));
        jsonObjectOut.add(new JsonPair("year", new JsonNumber(year)));

        JsonObject[] innerJsonObjects = new JsonObject[exams.length];
        int id = 0;

        for (Tuple<String, Integer> exam : exams) {
            JsonObject innerJsonObject = new JsonObject();
            innerJsonObject.add(new JsonPair("course", new JsonString(exam.key)));
            innerJsonObject.add(new JsonPair("mark", new JsonNumber(exam.value)));
            innerJsonObject.add(new JsonPair("passed", new JsonBoolean(checkPassed(exam.value))));
            innerJsonObjects[id] = innerJsonObject;
            id++;
        }

        jsonObjectOut.add(new JsonPair("exams", new JsonArray(innerJsonObjects)));

        return jsonObjectOut;
    }
}