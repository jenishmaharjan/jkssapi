package jkss


import grails.rest.*
import grails.converters.*
import jskkData.StoreItem
import objectDetection.ObjectDetector
import utils.JsonFormatter

class RestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RestController() {
        super(StoreItem)
    }

    @Override
    def show(){
        def results = identifyObjectsFromUrl(params.get("url"))
        def objectsInDatabaseThatMatchImage = new ArrayList()

        def currentItem
        results.each {
            currentItem = getItemFromDatabase(it)
            if (currentItem){
                objectsInDatabaseThatMatchImage.push(currentItem)
            }
        }

        def jsonFormatter = new JsonFormatter(objectsInDatabaseThatMatchImage)
        render jsonFormatter.getJsonFromIdentifiedObjects() as JSON
    }

    ///identifyObject/?url={url} to get object.
    def identifyObjectInImage(){
        def results = identifyObjectsFromUrl(params.get("url"))
        def jsonFormatter = new JsonFormatter(results)
        render jsonFormatter.getJsonFromIdentifiedObjects() as JSON
    }

    private static identifyObjectsFromUrl(url){
        def objectDetector = new ObjectDetector()
        return objectDetector.detectObjectsFromImageUrl(url)
    }

    private static getItemFromDatabase(itemName){
        return StoreItem.list().find{it.name.contains(itemName)}
    }
}
