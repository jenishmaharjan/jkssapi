package jskkData

class StoreItem {
    Integer id
    String name

    static constraints = {
    }

    static mapping = {
        table 'INVENTORY'
        id column: 'id'
        name column: 'itemName'
    }
}
