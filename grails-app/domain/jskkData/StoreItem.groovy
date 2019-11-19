package jskkData

class StoreItem {
    Integer id
    String name
    String description
    String quantityInStock
    String dateOfExpectedDelivery
    String vendor
    String location
    Integer price

    static constraints = {
    }

    static mapping = {
        table 'INVENTORY'
        id column: 'id'
        name column: 'item_name'
        description column: 'description'
        quantityInStock column: 'quantity_in_stock'
        dateOfExpectedDelivery column: 'date_of_expected_delivery'
        vendor column: 'vendor'
        location column: 'location'
        price column: 'price'
    }
}
