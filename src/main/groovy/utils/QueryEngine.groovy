package utils

class QueryEngine {
    private static QueryEngine ourInstance

    static QueryEngine getInstance() {
        if (ourInstance == null) {
            ourInstance = new QueryEngine()
        }
        return ourInstance
    }

    private QueryEngine() {
    }

    def getItemFromDatabase(domainClass, itemName) {
        return domainClass.list().find { it.name.contains(itemName) }
    }
}
