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
        render getItemFromDatabase(1) as JSON

    }

    private static def getItemFromDatabase(itemId){
        return StoreItem.get(itemId)
    }

    static allowedMethods = [getInventory: "GET"]
}
