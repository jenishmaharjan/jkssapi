package objectDetection

import com.google.cloud.vision.v1.Feature.Type
import com.google.cloud.vision.v1.*
import com.google.protobuf.ByteString
import javax.imageio.ImageIO
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import java.util.List

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
                if (!output.containsValue(list.get(i).getName())) {
                    output.put("Item " + i, list.get(i).getName())
                }
            }
        }
        return output
    }

    def detectObjectsFromImageUrl(String imageUrl) throws Exception, IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>()

        ImageSource imgSource = ImageSource.newBuilder()
                .setImageUri(imageUrl)
                .build()
        Image img = Image.newBuilder()
                .setSource(imgSource)
                .build()

        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(Feature.newBuilder()
                .setType(Feature.Type.OBJECT_LOCALIZATION))
                .setImage(img)
                .build()
        requests.add(request)

        ArrayList<String> objectNames = new ArrayList<>();

        // Perform the request
        ImageAnnotatorClient client = ImageAnnotatorClient.create()

        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests)
        List<AnnotateImageResponse> responses = response.getResponsesList()
        client.close()
        // Display the results
        for (AnnotateImageResponse res : responses) {
            for (LocalizedObjectAnnotation entity : res.getLocalizedObjectAnnotationsList()) {
                objectNames.add(entity.getName())
            }
        }


        return objectNames
    }
}
