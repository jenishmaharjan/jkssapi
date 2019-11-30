package objectDetection

import com.google.cloud.vision.v1.*

class ObjectDetector {
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
