package jkss


import grails.rest.*
import grails.converters.*
import jskkData.StoreItem
import objectDetection.ObjectDetector
import utils.JsonFormatter
import utils.QueryEngine

class RestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RestController() {
        super(StoreItem)
    }

    @Override
    def show() {
        def results = identifyObjectsFromUrl(params.get("url"))
        def objectsInDatabaseThatMatchImage = new ArrayList()

        def currentItem
        results.each {
            currentItem = QueryEngine.getInstance().getItemFromDatabase(StoreItem.class, it)
            if (currentItem) {
                objectsInDatabaseThatMatchImage.push(currentItem)
            }
        }

        JSON.use('deep') {
            render objectsInDatabaseThatMatchImage as JSON
        }
    }

    ///identifyObject/?url={url} to get object.
    def identifyObjectInImage() {
        def results = identifyObjectsFromUrl(params.get("url"))
        def jsonFormatter = new JsonFormatter(results)
        render jsonFormatter.getJsonFromIdentifiedObjects() as JSON
    }

    private static identifyObjectsFromUrl(url) {
        def objectDetector = new ObjectDetector()
        return objectDetector.detectObjectsFromImageUrl(url)
    }
}
