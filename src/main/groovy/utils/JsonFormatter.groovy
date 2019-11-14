package utils

import groovy.json.JsonSlurper

class JsonFormatter {
    private ArrayList<String> identifiedObjects

    JsonFormatter(identifiedObjects){
        this.identifiedObjects = identifiedObjects
    }

    def getJsonFromIdentifiedObjects(){
        def jsonString = getJsonFormatFromArrayListOfStrings(identifiedObjects.unique())
        return new JsonSlurper().parseText(jsonString)
    }

    private getJsonFormatFromArrayListOfStrings(arrayListOfObjects){
        def formattedJson = new StringBuilder()
        def identifiedObjectsKeyToValuePairs = new ArrayList()

        arrayListOfObjects.each {
            identifiedObjectsKeyToValuePairs.push("{\"name\": \"" + it + "\"}")
        }

        formattedJson.append("[")

        identifiedObjectsKeyToValuePairs.each {
            if (it != identifiedObjectsKeyToValuePairs.last()){
                formattedJson.append(it)
                formattedJson.append(",")
            }
            else{
                formattedJson.append(it)
            }
        }

        formattedJson.append("]")

        return formattedJson.toString()
    }

}
