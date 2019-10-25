package objectDetection

import com.google.cloud.vision.v1.AnnotateImageRequest
import com.google.cloud.vision.v1.Feature
import com.google.cloud.vision.v1.Feature.Type
import com.google.cloud.vision.v1.Image
import com.google.cloud.vision.v1.ImageAnnotatorClient
import com.google.protobuf.ByteString

class ObjectDetector {
    /**
     * Detects localized objects in the specified local image.
     *
     * @param filePath The path to the file to perform localized object detection on.
     * @throws Exception on errors while closing the client.
     * @throws IOException on Input/Output errors.
     * @return JSON - The object(s) in the image.
     */
    def getLocalizedObjectFromImageAsJson(filePath)
            throws Exception, IOException {

        def requests = new ArrayList<>()
        //TODO FILEPATH SHOULD NOT BE HARDCODED OR ABSOLUTE - this is just for testing
        def imgBytes = ByteString.readFrom(new FileInputStream("C:\\Users\\KylesPC\\Work\\JKSS\\src\\main\\groovy\\objectDetection\\TEST_IMAGES\\house.jpg"))

        def img = Image.newBuilder().setContent(imgBytes).build()
        def request =
                AnnotateImageRequest.newBuilder()
                        .addFeatures(Feature.newBuilder().setType(Type.OBJECT_LOCALIZATION))
                        .setImage(img)
                        .build()
        requests.add(request)

        // Perform the request
        def client = ImageAnnotatorClient.create()
        def response = client.batchAnnotateImages(requests)
        def responses = response.getResponsesList()
        def output = [:]
        // Put results in JSON
        for (def res : responses) {
            def list = res.getLocalizedObjectAnnotationsList()
            for (def i = 0; i < list.size(); i++) {
                if (!output.containsValue(list.get(i).getName())){
                    output.put("Item " + i, list.get(i).getName())
                }
            }
        }
        return output
    }
}
