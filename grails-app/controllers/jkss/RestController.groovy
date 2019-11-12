package jkss


import grails.rest.*
import grails.converters.*
import jskkData.StoreItem
import objectDetection.ObjectDetector

class RestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RestController() {
        super(StoreItem)
    }

    @Override
    def show(){
        //params.get("imageUrl") will return image url passed in GET request.
        //render getItemFromDatabase("Test") as JSON

        def objectDetector = new ObjectDetector()
        def results = objectDetector.getLocalizedObjectFromImageAsJson("This is being ignored")
        render results as JSON
    }

    ///identifyObject/?url=INSERT_URL_HERE to get object.
    def identifyObjectInImage(){
        def objectDetector = new ObjectDetector()
        def results = objectDetector.detectObjectsFromImageUrl(params.get("url"))
        //JSON converter will be used here to put objects into better format. Just using first result as POC.
        def jsonObject = [:]
        jsonObject.put("Object", results.get(0))
        render jsonObject as JSON
    }

    private static def getItemFromDatabase(itemName){
        return StoreItem.list().find{it.name == itemName}
    }
}
