package objectDetection

class ObjectDetectorHandler {

    static enum ObjectDetectionMethods {GOOGLE_VISION}

    private objectDetectionMethod

    ObjectDetectorHandler(objectDetectionMethod){
        this.objectDetectionMethod = objectDetectionMethod
    }

    def detectObjectsFromImageUrl(url){
        switch(objectDetectionMethod){
            case ObjectDetectionMethods.GOOGLE_VISION:
                return new GoogleVisionObjectDetector().detectObjectsFromImageUrl(url)
            break
        }
    }
}
