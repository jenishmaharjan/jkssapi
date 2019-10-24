package jkss


import grails.rest.*
import grails.converters.*
import jskkData.StoreItem

class RestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    RestController() {
        super(StoreItem)
    }

    @Override
    def show(){
        //params.get("imageUrl") will return image url passed in GET request.
        render getItemFromDatabase("Test") as JSON

    }

    private static def getItemFromDatabase(itemName){
        return StoreItem.list().find{it.name == itemName}
    }
}
