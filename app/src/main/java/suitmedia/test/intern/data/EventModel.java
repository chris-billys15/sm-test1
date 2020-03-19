package suitmedia.test.intern.data;

public class EventModel {
    public String eventName;
    public String image;
    public String eventDate;
    public String eventDesc;
    public double latitude;
    public double longitude;

    public EventModel(){

    }

    public EventModel(String eventName, String image, String eventDate, String eventDesc, double latitude, double longitude){
        this.eventName = eventName;
        this.image = image;
        this.eventDate = eventDate;
        this.eventDesc= eventDesc;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
