package jkss

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/getStoreInventory"(controller: 'rest', action: 'show')
        "/identifyObject/?url=${url}?"(controller: 'rest', action: 'identifyObjectInImage')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
